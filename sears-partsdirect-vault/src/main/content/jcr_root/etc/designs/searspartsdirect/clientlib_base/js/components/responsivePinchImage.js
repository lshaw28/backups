/*global window:true, $:true, Class:true */
var responsivePinchImage = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes responsivePinchImage class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Elements
			this.el = el;
			this.container = $('[data-toggle="pinch-image"]', this.el);
			this.image = null;
			this.plusButton = null;
			this.minusButton = null;
			// Properties
			this.desktopImage = '';
			this.tabletImage = '';
			this.mobileImage = '';
			this.hasImage = false;
			this.imageWidth = 0;
			this.imageHeight = 0;
			// Last Event Scale
			this.lastScale = 1;
			// Perform setup
			this.getProperties();
			if (this.hasImage === true) {
				this.renderImage();
				this.renderControls();
				this.bindEvents();
			}
		},
		/**
		 * Retrieves data for
		 * @return {void}
		 */
		getProperties: function () {
			var self = this,
				su = window.SPDUtils;

			// Retrieve images
			self.desktopImage = su.validString(self.container.data('desktopimage'));
			self.tabletImage = su.validString(self.container.data('tabletimage'));
			self.mobileImage = su.validString(self.container.data('mobileimage'));
			if (self.desktopImage !== '' || self.tabletImage !== '' || self.mobileImage !== '') {
				self.hasImage = true;
			}
		},
		/**
		 * Renders an image for the current breakpoint
		 */
		renderImage: function () {
			var self = this,
				su = window.SPDUtils,
				isMobile = su.isMobileBreakpoint(),
				isTablet = su.isTabletBreakpoint(),
				currentImage = '',
				newImage = self.mobileImage;

			// Choose the best image
			if (isMobile) {
				newImage = self.chooseImage(self.mobileImage, self.tabletImage, self.desktopImage);
			} else if (isTablet) {
				newImage = self.chooseImage(self.tabletImage, self.mobileImage, self.desktopImage);
			} else {
				newImage = self.chooseImage(self.desktopImage, self.tabletImage, self.mobileImage);
			}
			// If there is no image already, create it
			if (self.image === null) {
				self.image = $('<img />');
				self.container.append(self.image);
			} else {
				currentImage = su.validString(self.image.attr('src'));
			}
			// New image or new src, update
			if (currentImage !== newImage) {
				// Set new image source
				self.image.attr('src', newImage);
				// Reset image scale
				self.lastScale = 1;
				// Reset initial CSS
				self.image.css({
					'height': 'auto',
					'left': '50%',
					'top': '50%',
					'width': 'auto'
				});
				// Set image load CSS
				// Force pixel sizes and negative margins for centering
				self.image.bind('load', function () {
					self.imageWidth = self.image.width();
					self.imageHeight = self.image.height();
					
					self.image.css({
						'height': self.imageHeight,
						'margin-left': 0 - self.imageWidth / 2,
						'margin-top': 0 - self.imageHeight / 2,
						'width': self.imageWidth
					});
				});
			}
		},
		/**
		 * Renders the controls
		 * @return {void}
		 */
		renderControls: function () {
			var self = this;

			// Render plus button
			self.plusButton = $('<a />');
			self.plusButton.addClass('control')
				.addClass('plus')
				.text('+');
			// Render minus button
			self.minusButton = $('<a />');
			self.minusButton.addClass('control')
				.addClass('minus')
				.text('-');
			// Display buttons
			self.container.append(self.plusButton)
				.append(self.minusButton);
		},
		/**
		 * Will cascade through three image options to find the best
		 * @param {string} best Preferred image
		 * @param {string} nextBest Next best image
		 * @param {string} worst Worst image but we'll take it
		 * @return {string} Chosen image URL
		 */
		chooseImage: function (best, nextBest, worst) {
			if (best !== '') {
				return best;
			} else if (nextBest !== '') {
				return nextBest;
			} else {
				return worst;
			}
		},
		/**
		 * Binds events for scaling the image
		 */
		bindEvents: function () {
			var self = this,
				isMobile = window.SPDUtils.isMobileBrowser();

			// Bind pinch events based on the availability of touchstart
			if (isMobile === true) {
				self.image.hammer({
					'prevent_default': true
				}).on('pinchin', function(event) {
					event.gesture.preventDefault();
					self.handleSizing(event);
				}).on('pinchout', function(event) {
					event.gesture.preventDefault();
					self.handleSizing(event);
				});
			}
			self.image.hammer({
				'prevent_default': true
			}).on('drag', function (event) {
				event.gesture.preventDefault();
				self.handleDrag(event);
			});
			// Bind button clicks
			self.plusButton.bind('click', function () {
				var clientPos = self.getCenter();

				self.handleSizing({
					'clientX': clientPos.x,
					'clientY': clientPos.y,
					'gesture': {
						'scale': (self.lastScale <= 2.8 ? self.lastScale + 0.2 : 3.0)
					}
				});
			});
			self.minusButton.bind('click', function () {
				var clientPos = self.getCenter();

				self.handleSizing({
					'clientX': clientPos.x,
					'clientY': clientPos.y,
					'gesture': {
						'scale': (self.lastScale >= 0.4 ? self.lastScale - 0.2 : 0.2)
					}
				});
			});
			// Bind window resize
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.renderImage();
			});
		},
		/**
		 * Gets center position for the image object
		 * @returns {object}
		 */
		getCenter: function () {
			var self = this,
				retval = {};

			retval.x = parseInt(self.image.offset().left + (self.imageWidth / 2), 10);
			retval.y = parseInt(self.image.offset().top + (self.imageHeight / 2), 10);

			return retval;
		},
		/**
		 * Handles a size change event
		 * @param {object} event Event fired by user or JavaScript
		 * @return {void}
		 */
		handleSizing: function (event) {
			var self = this,
				offset = self.container.offset(),
				newX = 0,
				newY = 0,
				x = event.clientX,
				y = event.clientY,
				scale = event.gesture.scale,
				newWidth = self.imageWidth * scale,
				newHeight = self.imageHeight * scale;

			// Determine the new offset
			x -= offset.left + newX;
			y -= offset.top + newY;
			newX += -x * (newWidth - self.imageWidth) / newWidth;
			newY += -y * (newHeight - self.imageHeight) / newHeight;

			// Set properties
			self.lastScale = scale;
			self.imageWidth = newWidth;
			self.imageHeight = newHeight;
			
			// Scale the image
			self.image.stop().animate({
				'height': self.imageHeight,
				'margin-left': 0 - self.imageWidth / 2,
				'margin-top': 0 - self.imageHeight / 2,
				'width': self.imageWidth
			}, 50);
		},
		/**
		 * Handles a drag event
		 * @param {object} event Event fired by user or JavaScript
		 */
		handleDrag: function (event) {
			var self = this,
				isMobileBrowser = window.SPDUtils.isMobileBrowser(),
				moveX = event.gesture.deltaX,
				moveY = event.gesture.deltaY,
				containerWidth = self.container.width(),
				containerHeight = self.container.height(),
				marginLeft = parseInt(self.image.css('margin-left').replace('px', ''), 10),
				marginTop = parseInt(self.image.css('margin-top').replace('px', ''), 10);

			// Handle different drag directions
			moveX = moveX / 10;
			moveY = moveY / 10;
			marginLeft = marginLeft + moveX;
			marginTop = marginTop + moveY;
			
			// Sanitise
			if (marginLeft + self.imageWidth <= 0) {
				marginLeft += 10;
			} else if (marginLeft + self.imageWidth >= containerWidth) {
				marginLeft -= 10;
			}
			if (marginTop + self.imageHeight <= 0) {
				marginTop += 10;
			} else if (marginTop + self.imageHeight >= containerHeight) {
				marginTop -= 10;
			}

			// Set new margins
			self.image.stop().animate({
				'margin-left': marginLeft,
				'margin-top': marginTop
			}, 50);
		}
	};
}());