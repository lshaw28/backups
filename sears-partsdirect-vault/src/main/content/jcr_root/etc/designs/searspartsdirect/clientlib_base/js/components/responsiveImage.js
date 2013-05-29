/*global $:true, window:true, Class:true */
(function (window) {
	/**
	 * @class responsiveImage
	 * responsiveImage component, retrieves information and handles events
	 */
	"use strict";
	window.responsiveImage = Class.extend({
		/**
		 * Initializes responsiveImage class
		 * @param {object} el Target element
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 */
		init: function (el, fw, fh, iq) {
			// Parameters
			this.el = $(el);
			// Properties
			this.fw = 0;
			this.fh = 0;
			this.renderType = 'responsive';
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
		 * Retrieves data from attributes
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 * @return {void}
		 */
		setProperties: function (fw, fh, iq) {
			var self = this;
			// Image URLs
			self.desktopImage = self.el.data('desktopimage');
			self.tabletImage = self.el.data('tabletImage');
			self.mobileImage = self.el.data('mobileImage');
			// Image dimensions
			// Respect element dimensions < attribute < init parameter
			self.fw = self.el.width();
			if (window.SPDUtils.validNumber(self.el.data('width')) === true) {
				self.fw = parseInt(self.el.data('width'), 10);
				self.useParentDims = false;
			}
			if (window.SPDUtils.validNumber(fw) === true) {
				self.fw = parseInt(fw, 10);
				self.useParentDims = false;
			}
			self.fh = self.el.height();
			if (window.SPDUtils.validNumber(self.el.data('height')) === true) {
				self.fh = parseInt(self.el.data('height'), 10);
			}
			if (window.SPDUtils.validNumber(fh) === true) {
				self.fh = parseInt(fh, 10);
			}
			if (window.SPDUtils.validNumber(iq) === true) {
				self.iq = parseInt(iq, 10);
			}
		},
		/**
		 * Gets parent dimensions on re-render
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
			// @TODO: Logic to add query string parameters to generated URL based on breakpoint logic
		},
		/**
		 * Bind the window resize event
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			$(window).resize(function () {
				self.render();
			});

			self.isBound = true;
		}
	});
}(window));