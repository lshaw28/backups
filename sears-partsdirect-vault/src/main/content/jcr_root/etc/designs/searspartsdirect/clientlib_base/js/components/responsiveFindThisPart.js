/*global window:true, $:true, Class:true */
var responsiveFindThisPart = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes responsiveFindThisPart class
		 * Mobile: accordion
		 * Tablet/desktop: visible content
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Events
			this.bindEvent();
			this.toggleAccordion();
		},
		/**
		 * Toggle accordion functionality based on breakpoint
		 * @return {void}
		 */
		toggleAccordion: function () {
			var self = this,
				isMobile = window.SPDUtils.isMobileBreakpoint();

			if (isMobile === true) {
				// Set CSS classes
				// Add data toggle
			} else {
				// Set CSS classes
				// Remove data toggle
			}
		},
		/**
		 * Bind the window resize events
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.toggleAccordion();
			});
		}
	};
}());