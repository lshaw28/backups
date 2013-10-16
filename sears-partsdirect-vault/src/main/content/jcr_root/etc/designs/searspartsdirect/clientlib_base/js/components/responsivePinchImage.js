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
			this.transformPrefix = null;
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
			// Set vendor prefix for CSS transforms
			self.setPrefix();
		},
		/**
		 * Determines the vendor prefix to use for CSS transformations
		 * If none is supported, retain the null value for future logic
		 * @returns {void}
		 */
		setPrefix: function () {
			var self = this,
				div = $('<div />'),
				i,
				list = ['transform', 'msTransform', 'MozTransform', 'WebkitTransform', 'OTransform'];

			// Determine which prefix to use
			for (i = 0; i < list.length; ++i) {
				if (typeof div[0].style[list[i]] !== 'undefined') {
					self.transformPrefix = list[i];
					return;
				}
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
				if (self.transformPrefix !== null) {
					self.image[0].style[self.transformPrefix] = '';
				}
				// Reset initial CSS
				self.image.css({
					'height': 'auto',
					'left': '50%',
					'top': '50%',
					'width': '100%'
				});
				// Set image load CSS
				self.image.bind('load', function () {
					self.imageWidth = self.image.width();
					self.imageHeight = self.image.height();
					
					self.image.css({
						'height': self.imageHeight,
						'margin-left': 0 - self.imageWidth / 2,
						'margin-top': 0 - self.imageHeight / 2
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
				self.image.hammer().on('pinchin', function(event) {
					event.preventDefault();
					self.handleSizing(event);
				}).on('pinchout', function(event) {
					event.preventDefault();
					self.handleSizing(event);
				});
			}
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
			
			// Scale the image using transforms or regular old math
			if (self.transformPrefix !== null) {
				self.image[0].style[self.transformPrefix] = 'scale3d(' + scale + ', ' + scale + ', 1)';
			} else {
				self.image.css({
					'height': self.imageHeight,
					'margin-left': 0 - self.imageWidth / 2,
					'margin-top': 0 - self.imageHeight / 2,
					'width': self.imageWidth
				});
			}
		}
	};
}());