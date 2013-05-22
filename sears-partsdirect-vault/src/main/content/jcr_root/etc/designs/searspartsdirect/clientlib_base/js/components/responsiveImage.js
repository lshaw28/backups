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
		init: function (el, fw, fh) {
			// Parameters
			this.el = $(el);
			// Properties
			this.fw = 0;
			this.fh = 0;
			this.renderType = 'responsive';
			this.desktopImage = '';
			this.tabletImage = '';
			this.mobileImage = '';
			// Retrieve data
			this.setProperties(fw, fh);
			// Render
			this.render();
		},
		/**
		 * Retrieves data from attributes
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 * @return void
		 */
		setProperties: function (fw, fh) {
			var self = this;
			// Image URLs
			self.desktopImage = self.el.data('desktopimage');
			self.tabletImage = self.el.data('tabletImage');
			self.mobileImage = self.el.data('mobileImage');
			// Image dimensions
			// Respect element dimensions < attribute < init parameter
			self.fw = self.el.width();
			if (window.SPDUtils.validNumber(self.el.data('width')) === true) {
				self.fw = parseInt(self.el.data('width'));
			}
			if (window.SPDUtils.validNumber(fw) === true) {
				self.fw = parseInt(fw);
			}
			self.fh = this.el.height();
			if (window.SPDUtils.validNumber(self.el.data('height')) === true) {
				self.fh = parseInt(self.el.data('height'));
			}
			if (window.SPDUtils.validNumber(fh) === true) {
				self.fh = parseInt(fh);
			}
		},
		/**
		 * Determines the correct rendering method
		 * @return void
		 */
		render: function () {
			var self = this;
			// @TODO: Logic to determine whether the image is from Scene7 or user uploaded
		},
		/**
		 * Renders a responsive image and calls event binding
		 * @return void
		 */
		renderResponsive: function () {
			var self = this,
				imageURL = self.getResponsiveURL();

			// Remove previously rendered image
			$('.responsiveImage_js', self.el).remove();
			// Generate image
		},
		/**
		 * Renders a Scene7 image and calls event binding
		 * @return void
		 */
		renderGenerated: function () {
			var self = this,
				imageURL = self.getGeneratedURL();
			
			// Remove previously rendered image
			$('.responsiveImage_js', self.el).remove();
			// Generate image
		},
		/**
		 * Determine which responsive image to use and at which dimensions
		 * @return {string} Image URL
		 */
		getResponsiveURL: function () {
			// @TODO: Logic to determine which image to display based on breakpoint logic
		},
		/**
		 * Generate a URL for an image with the appropriate query string parameters
		 * @return {string} Image URL
		 */
		getGeneratedURL: function () {
			// @TODO: Logic to add query string parameters to generated URL based on breakpoint logic
		}
	});
}(window));