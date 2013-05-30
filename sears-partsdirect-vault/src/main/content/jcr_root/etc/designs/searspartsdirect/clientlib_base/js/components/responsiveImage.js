/*global $:true, window:true, Class:true */
(function (window) {
	/**
	 * @class responsiveImage
	 * responsiveImage component, retrieves information and handles events
	 * Can be extended with discreet functionality overrides
	 */
	"use strict";
	window.responsiveImage = Class.extend({
		/**
		 * Initializes responsiveImage class
		 * @param {object} el Target element
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 * @param {number} iq Optional forced image quality for Scene 7
		 */
		init: function (el, fw, fh, iq) {
			// Parameters
			this.el = $(el);
			// Properties
			this.fw = 0;
			this.fh = 0;
			this.desktopImage = '';
			this.tabletImage = '';
			this.mobileImage = '';
			this.imageQuality = 80;
			this.useParentDims = true;
			this.isBound = false;
			// Retrieve data
			this.setProperties(fw, fh, iq);
			// Render
			this.render();
		},
		/**
		 * Retrieves data from attributes and parameters
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 * @param {number} iq Optional forced image quality for Scene 7
		 * @return {void}
		 */
		setProperties: function (fw, fh, iq) {
			var self = this;
			// Image URLs
			self.desktopImage = self.el.data('desktopimage');
			self.tabletImage = self.el.data('tabletimage');
			self.mobileImage = self.el.data('mobileimage');

			// Acquire parent dimensions
			self.getParentDims();

			// Override width
			if (window.SPDUtils.validNumber(self.el.data('width')) > 0) {
				self.fw = parseInt(self.el.data('width'), 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			} else if (window.SPDUtils.validNumber(fw) > 0) {
				self.fw = parseInt(fw, 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			}

			// Override height
			if (window.SPDUtils.validNumber(self.el.data('height')) > 0) {
				self.fh = parseInt(self.el.data('height'), 10);
			} else if (window.SPDUtils.validNumber(fh) > 0) {
				self.fh = parseInt(fh, 10);
			}

			// Override image quality
			if (window.SPDUtils.validNumber(self.el.data('imagequality')) > 0) {
				self.iq = parseInt(self.el.data('imagequality'), 10);
			} else if (window.SPDUtils.validNumber(iq) > 0) {
				self.iq = parseInt(iq, 10);
			}
		},
		/**
		 * Sets dimension values to parent dimensions
		 * @return {void}
		 */
		getParentDims: function () {
			var self = this;
			self.fw = self.el.width();
			self.fh = self.el.height();
		},
		/**
		 * Determines the correct rendering method
		 * @return {void}
		 */
		render: function () {
			var self = this;

			/* @TODO: Correct check for Scene 7 */
			if (self.desktopImage.indexOf('scene7') > -1) {
				self.renderGenerated();
			} else {
				self.renderResponsive();
			}
		},
		/**
		 * Renders a responsive image and calls event binding
		 * @return {void}
		 */
		renderResponsive: function () {
			var self = this,
				imageURL = self.getResponsiveURL();

			// Retrieve parent's current dimensions
			if (self.useParentDims === true) {
				self.getParentDims();
			}

			// Remove previously rendered image
			$('.responsiveImage_js', self.el).remove();

			// Generate image
			var img = $('<img />');
			img.attr('src', imageURL)
				.addClass('responsiveImage_js')
				.css({
					'width': self.fw,
					'height': self.fh
				});
			self.el.append(img);

			// Bind event
			if (self.isBound === false) {
				self.bindEvent();
			}
		},
		/**
		 * Renders a Scene7 image and calls event binding
		 * @return {void}
		 */
		renderGenerated: function () {
			var self = this,
				imageURL = self.getGeneratedURL();

			// Remove previously rendered image
			$('.responsiveImage_js', self.el).remove();

			// Generate image
			var img = $('<img />');
			img.attr('src', imageURL)
				.addClass('responsiveImage_js');
			self.el.append(img);

			// Bind event
			if (self.isBound === false) {
				self.bindEvent();
			}
		},
		/**
		 * Determine which responsive image to use and at which dimensions
		 * @return {string} Image URL
		 */
		getResponsiveURL: function () {
			var self = this,
				isMobile = window.SPDUtils.isMobileBreakpoint(),
				isTablet = window.SPDUtils.isTabletBreakpoint();

			// Determine which image to display
			if (isMobile) {
				return self.mobileImage;
			} else if (isTablet) {
				return self.tabletImage;
			} else {
				return self.desktopImage;
			}
		},
		/**
		 * Generate a URL for an image with the appropriate query string parameters
		 * @return {string} Image URL
		 */
		getGeneratedURL: function () {
			var self = this,
				baseURL = self.desktopImage.split('?')[0];

			/* @TODO: Retrieve correct parameter names
			 * and confirm pre-? substring contains image name */
			return baseURL
				+ '?width=' + self.fw
				+ '&height=' + self.fh
				+ '&quality=' + self.iq;
		},
		/**
		 * Bind the window resize event
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			// Window resize and orientation change
			$(window).resize(function () {
				self.render();
			})
			.bind('orientationchange', function () {
				self.render();
			});

			self.isBound = true;
		}
	});
}(window));