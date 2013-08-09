/*global window:true, $:true, Class:true */
var skyscraperAd = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes skyscraperAd class
		 * @param {object} el Target element
		 * @param {string} im Image URL
		 * @param {boolean} ds Enable for desktop
		 * @param {boolean} tb Enable for tablet
		 * @param {boolean} mb Enable for mobile
		 */
		init: function (el, im, ds, tb, mb) {
			// Parameters
			this.el = $(el);
			this.im = im;
			this.ds = ds;
			this.tb = tb;
			this.mb = mb;
			// Properties
			this.rendered = false;
			// Render
			this.render();
			// Bind events
			this.bindEvent();
		},

		/**
		 * Renders an image if the current breakpoint matches those allowed
		 * @return {void}
		 */
		render: function () {
			var self = this,
				su = window.SPDUtils;

			// If we've already rendered the image, don't do it again
			if (self.rendered === false) {
				// Check breakpoints
				if (self.checkBreakpoint() === true) {
					// Generate image
					var img = $('<img />');
					img.attr('src', self.im)
						.attr('alt', self.alt)
						.css('max-width', '100%');
					self.el.append(img);
					// Remember that you've rendered
					self.rendered = false;
				}
			}
		},
		/**
		 * Determine if you should render based on breakpoint matches
		 * @return {boolean}
		 */
		checkBreakpoints: function () {
			var self = this,
				su = window.SPDUtils,
				breakpointMet = false;

			// Mobile breakpoint, mobile allowed
			if (su.isMobileBreakpoint() === true && self.mb === true) {
				breakpointMet = true;
			}
			// Tablet breakpoint, tablet allowed
			if (su.isTabletBreakpoint() === true && self.tb === true) {
				breakpointMet = true;
			}
			// Desktop breakpoint, desktop allowed
			if (su.isMobileBreakpoint() === false && su.isTabletBreakpoint() === false && self.ds === true) {
				breakpointMet = true;
			}

			return breakpointMet;
		},
		/**
		 * Bind the window resize events
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.render();
			});
		}
	};
}());