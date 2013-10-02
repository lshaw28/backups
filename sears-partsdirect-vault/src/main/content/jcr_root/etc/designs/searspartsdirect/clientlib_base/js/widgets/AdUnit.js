NS('shc.pd.base.widgets').AdUnit = shc.pd.base.render.Breakpoint.extend(function () {
	'use strict';

	return {
		/**
		 * @constructor
		 * @param {object} el jQuery ad unit container element
		 */
		init: function (el) {
			this.el = el;
			this.adUnitEl = $('.adUnit_js', this.el);
			this.enablerClassName = 'adEnabled';
			this.disablerClassName = 'adDisabled';
			this.imageRendered = false;
		},
		/**
		 * Activation event
		 * @return {void}
		 */
		activate: function () {
			var self = this,
				foundImage = $('img', self.adUnitEl);

			// Account for the same ad unit being used for multiple breakpoints
			if (foundImage.length > 0) {
				self.imageRendered = true;
			}

			// Render if no image is present
			if (self.imageRendered === false) {
				self.renderImage();
			}

			// Set CSS classes
			self.el.addClass(self.enablerClassName);
			self.el.removeClass(self.disablerClassName);
		},
		/**
		 * Deactivation event
		 * @return {void}
		 */
		deactivate: function () {
			var self = this;

			// Set CSS classes
			self.el.addClass(self.disablerClassName);
			self.el.removeClass(self.enablerClassName);
		},
		/**
		 * Render image event
		 * @return {void}
		 */
		renderImage: function () {
			var self = this,
				img = $('<img />'),
				imageUrl = window.SPDUtils.validString(self.adUnitEl.data('imageurl')),
				imageAlt = window.SPDUtils.validString(self.adUnitEl.data('imagealt')),
				linkUrl = window.SPDUtils.validString(self.adUnitEl.data('linkurl')),
				linkTarget = window.SPDUtils.validString(self.adUnitEl.data('linktarget'), '_self'),
				insertTarget = self.adUnitEl;

			// If there is a valid image URL, render
			if (imageUrl !== '') {
				// If there is a valid hyperlink, create an href
				if (linkUrl !== '') {
					insertTarget = $('<a />');
					insertTarget.attr('href', linkUrl)
						.attr('target', linkTarget);
					self.adUnitEl.append(insertTarget);
				}

				// Render the image itself
				img.attr('src', imageUrl)
					.attr('alt', imageAlt)
					.css('max-width', '100%');
				insertTarget.append(img);
				self.imageRendered = true;
			}
		}
	};
}());