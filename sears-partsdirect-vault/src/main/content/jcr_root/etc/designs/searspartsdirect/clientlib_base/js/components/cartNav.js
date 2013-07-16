/*global window:true, $:true, Class:true, mainSitePath:true */
var cartNav = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton cartNav
		 * Singleton class for the cartNav component
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			// Properties
			this.dropdown = $('#cartShop .dropdown-menu');
			// Initialize events
			this.bindEvents();
			this.toggleAction();
			console.log('cartNav initialized');
		},
		/**
		 * Toggle data-toggle action
		 * @return {void}
		 */
		toggleAction: function () {
			var self = this,
				isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();

			if (isMobileBreakpoint === true) {
				$('#cartShop [data-toggle]').data('toggle', 'false');
				self.dropdown.removeClass('dropdown-menu')
				.addClass('cart-canvas');
			} else {
				$('#cartShop [data-toggle]').data('toggle', 'dropdown');
				self.dropdown.removeClass('cart-canvas')
				.addClass('dropdown-menu');
			}
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;
			
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.toggleAction();
			});
		}
	}
}());