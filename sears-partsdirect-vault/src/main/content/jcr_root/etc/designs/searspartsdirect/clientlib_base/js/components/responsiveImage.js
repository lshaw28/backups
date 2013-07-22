/*global window:true, $:true, Class:true */
var responsiveImage = Class.extend(function () {
	"use strict";

	return {
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
			this.targetEl = $(el);
			// Properties
			this.fw = 0;
			this.fh = 0;
			this.desktopImage = '';
			this.tabletImage = '';
			this.mobileImage = '';
			this.linkAlt = '';
			this.linkURL = '';
			this.linkTarget = '';
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
			var self = this,
				su = window.SPDUtils;

			// Image URLs
			self.desktopImage = self.el.data('desktopimage');
			self.tabletImage = self.el.data('tabletimage');
			self.mobileImage = self.el.data('mobileimage');

			// Hyperlink
			self.linkAlt = su.validString(self.el.data('linkalt'));
			self.linkURL = su.validString(self.el.data('linkurl'));
			self.linkTarget = su.validString(self.el.data('linktarget'));

			if (self.linkURL !== '') {
				self.targetEl = $('<a />');
				self.targetEl.attr('href', self.linkURL);
				self.targetEl.attr('target', self.linkTarget);
				self.el.append(self.targetEl);
			}

			// Acquire parent dimensions
			self.getParentDims();

			// Override width
			if (su.validNumber(self.el.data('width')) > 0) {
				self.fw = parseInt(self.el.data('width'), 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			} else if (su.validNumber(fw) > 0) {
				self.fw = parseInt(fw, 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			}

			// Override height
			if (su.validNumber(self.el.data('height')) > 0) {
				self.fh = parseInt(self.el.data('height'), 10);
			} else if (su.validNumber(fh) > 0) {
				self.fh = parseInt(fh, 10);
			}

			// Override image quality
			if (su.validNumber(self.el.data('imagequality')) > 0) {
				self.iq = parseInt(self.el.data('imagequality'), 10);
			} else if (su.validNumber(iq) > 0) {
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

			console.log(self.fw);
			console.log(self.fh);
		},
		/**
		 * Determines the correct rendering method
		 * @return {void}
		 */
		render: function () {
			var self = this;

			if (self.desktopImage.indexOf('s.sears.com') > -1) {
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
				imageNode = $('.responsiveImage_js', self.targetEl),
				imageURL = self.getResponsiveURL();

			if (imageURL !== imageNode.attr('src')) {
				// Retrieve parent's current dimensions
				if (self.useParentDims === true) {
					self.getParentDims();
				}

				// Remove previously rendered image
				imageNode.remove();

				// Generate image
				var img = $('<img />');
				img.attr('src', imageURL)
					.attr('alt', self.linkAlt)
					.addClass('responsiveImage_js')
					.css('max-width', '100%');
				self.targetEl.append(img);
			}

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
			$('.responsiveImage_js', self.targetEl).remove();

			// Generate image
			var img = $('<img />');
			img.attr('src', imageURL)
				.attr('alt', self.linkAlt)
				.addClass('responsiveImage_js');
			self.targetEl.append(img);

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

			return baseURL + '?wid=' + self.fw + '&hei=' + self.fh + '&op_sharpen=1' + '&qlt=' + self.iq;
		},
		/**
		 * Bind the window resize event
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.render();
			});

			this.isBound = true;
		}
	};
}());