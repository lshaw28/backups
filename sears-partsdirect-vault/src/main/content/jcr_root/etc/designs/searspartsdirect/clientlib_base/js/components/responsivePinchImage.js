/*global window:true, $:true, Class:true */
var responsivePinchImage = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes responsivePinchImage class
		 * @param {object} el Target element
		 */
		init: function (el) {
			var self = this;
			// Elements
			this.el = el;
			this.container = $('[data-toggle="pinch-image"]', this.el);
			this.fullscreen = $('[data-toggle="pinch-fullscreen"]', this.el);
			this.print = $('[data-toggle="pinch-print"]', this.el);
			this.image = null;
			this.plusButton = null;
			this.minusButton = null;
			// Properties
			this.desktopImage = '';
			this.tabletImage = '';
			this.mobileImage = '';
			this.hasImage = false;
			this.offset = this.container.offset();
			this.imageWidth = 0;
			this.imageHeight = 0;
			this.posX = 0;
			this.posY = 0;
			this.scale = 1;
			this.lastScale = 1;
			this.lastPosX = 0;
			this.lastPosY = 0;
			this.maxX = 0;
			this.maxY = 0;
			// Perform setup
			this.getProperties();
			if (this.hasImage === true) {
				this.renderImage();
				this.renderControls();
				this.bindEvents();
			}

			//Bound event here as events aren't bounded if there isn't already image (must call change event when adding an image path with javascript)
			this.container.bind('change', function () {
				self.getProperties();
				self.renderImage();
				self.renderControls();
				self.bindEvents();
			});
		},
		/**
		 * Retrieves image paths
		 * @return {void}
		 */
		getProperties: function () {
			var self = this,
				su = window.SPDUtils;

			// Retrieve images
			self.desktopImage = su.validString(self.container.attr('data-desktopimage'));
			self.tabletImage = su.validString(self.container.attr('data-tabletimage'));
			self.mobileImage = su.validString(self.container.attr('data-mobileimage'));
			if (self.desktopImage !== '' || self.tabletImage !== '' || self.mobileImage !== '') {
				self.hasImage = true;
			}
		},
		/**
		 * Renders an image for the current breakpoint
		 * @return {void}
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
					'width': '100%'
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
				.addClass('icon-plus');
			// Render minus button
			self.minusButton = $('<a />');
			self.minusButton.addClass('control')
				.addClass('icon-minus');
			// Display buttons
			self.container.parent().append(self.plusButton)
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
		 * Binds events for scaling and displaying the image
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;

			// Gestures - on the container to avoid oddness
			self.container.hammer({
				prevent_default: true,
				prevent_mouseevents: false
			}).on('touch', function(ev) {
				self.handleGesture(ev);
			}).on('transform', function(ev) {
				self.handleGesture(ev);
			}).on('drag', function(ev) {
				self.handleGesture(ev);
			}).on('dragend', function(ev) {
				self.handleGesture(ev);
			});

			// Button clicks
			self.plusButton.bind('click', function (e) {
				e.preventDefault();
				self.lastScale = (self.lastScale <= 2.8 ? self.lastScale + 0.2 : 3.0);

				self.handleGesture({
					'type': 'transform',
					'gesture': {
						'scale': self.lastScale
					}
				});
			});
			self.minusButton.bind('click', function (e) {
				e.preventDefault();
				self.lastScale = (self.lastScale >= 0.4 ? self.lastScale - 0.2 : 0.2);

				self.handleGesture({
					'type': 'transform',
					'gesture': {
						'scale': self.lastScale
					}
				});
			});
			self.fullscreen.bind('click', function () {
				self.openImage(false);
			});
			self.print.bind('click', function () {
				self.openImage(true);
			});
			// Window resize
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.renderImage();
			});
		},
		/**
		 * Handles gestures
		 * @param {object} ev Event fired
		 * @returns {void}
		 */
		handleGesture: function (ev) {
			var self = this,
				transform = "translate3d(0, 0, 0) " + "scale3d(1, 1, 0) ";

			// Depending on the event, calculate or store scale and transform values
			switch(ev.type) {
				case 'touch':
					self.lastScale = self.scale;
					break;
				case 'drag':
					if (self.scale != 1) {
						self.posX = self.lastPosX + ev.gesture.deltaX;
						self.posY = self.lastPosY + ev.gesture.deltaY;
						if (self.posX > self.maxX) {
							self.posX = self.maxX;
						}
						if (self.posX < -self.maxX) {
							self.posX = -self.maxX;
						}
						if (self.posY > self.maxY) {
							self.posY = self.maxY;
						}
						if (self.posY < -self.maxY) {
							self.posY = -self.maxY;
						}
					} else {
						self.posX = 0;
						self.posY = 0;
						self.lastPosX = 0;
						self.lastPosY = 0;
					}
					break;
				case 'transform':
					self.scale = Math.max(1, Math.min(self.lastScale * ev.gesture.scale, 10));
					self.maxX = Math.ceil((self.scale - 1) * self.image[0].clientWidth / 2);
					self.maxY = Math.ceil((self.scale - 1) * self.image[0].clientHeight / 2);
					if (self.posX > self.maxX) {
						self.posX = self.maxX;
					}
					if (self.posX < -self.maxX) {
						self.posX = -self.maxX;
					}
					if (self.posY > self.maxY) {
						self.posY = self.maxY;
					}
					if (self.posY < -self.maxY) {
						self.posY = -self.maxY;
					}
					break;
				case 'dragend':
					self.lastPosX = self.posX < self.maxX ? self.posX: self.maxX;
					self.lastPosY = self.posY < self.maxY ? self.posY: self.maxY;
					break;
			}

			// Handle transforms, falling back to normal CSS for IE <= 9
			if (self.scale != 1) {
				transform = "translate3d(" + self.posX + "px," + self.posY + "px, 0) " + "scale3d(" + self.scale + "," + self.scale + ", 0) ";
			}
			if (!$('body').hasClass('lt-ie10')) {
				self.image[0].style[window['CSSTransform']] = transform;
			} else {
				self.image.css({
					'height': self.imageHeight * self.scale,
					'left': self.posX,
					'top': self.posY,
					'width': self.imageWidth * self.scale
				});
			}
		},
		/**
		 * Opens the image in a new window with optional printing
		 * @params {boolean} print Optional image printing
		 */
		openImage: function (print) {
			var
				self = this,
				image = self.chooseImage(self.desktopImage, self.tabletImage, self.mobileImage),
                //newWindow = window.open('', '', 'width=100%, height=100%');
                newWindow = window.open('', '');
                newWindow.document.write("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"utf-8\" />\n<title>${part.description}</title>\n</head>\n<body>\n<img src=\""+image+"\" width=\"804\" height=\"auto\" />\n</body>\n</html>");
                newWindow.document.close();


            /*var preview=getDiagramPagePreview();
             // Render the image
             newWindow.document.write('<img src="' + image + '" />');*/

			// Resize Window
			if (print === true) {
				newWindow.focus();
				newWindow.print();
			}
		}

	};
}());