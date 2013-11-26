/*global window:true, $:true, Class:true */
var responsiveCollapse = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes responsiveCollapse class
		 * Mobile: accordion
		 * Tablet/desktop: visible content
		 * @param {object} el Target element
		 * @param {string} cl Class for buttons to bind
		 */
		init: function (el, cl) {
			// Parameters
			this.el = $(el);
			this.cl = cl;
			// Events
			this.bindEvents();
		},
		/**
		 * Toggle functionality based on breakpoint
		 * @return {void}
		 */
		toggleActive: function () {
			var self = this,
				isMobile = window.SPDUtils.isMobileBreakpoint();

			if (isMobile === true) {
				// Set CSS classes
				self.el.removeClass('active');
			}
		},
		/**
		 * When active, toggles display
		 */
		togglePanel: function () {
			var self = this;

			self.el.toggleClass('active');
		},
		/**
		 * Bind the window resize events
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;

			// Ensure panel closes on breakpoint change
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.toggleActive();
			});
			// Set link to toggle display
			$('.' + self.cl.replace('.', ''), self.el).bind('click', function () {
				self.togglePanel();
			});
		}
	};
}());