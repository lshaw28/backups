/*global $:true, window:true, Class:true */
var SPDAuthorUtils = (function () {
	"use strict";

	return {
		/**
		 * @namespace SPDAuthorUtils
		 * Global utilities and helper methods for Author environment
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			var self = this;

			shc.pd.base.util.ViewChange.getInstance().onResponsive(function (args) {
				console.log('Application Width: ', args[0]);
			});
		},
		/**
		 * Refresh responsiveDropdown on edit
		 */
		editResponsiveDropdown: function () {
			// Update markup
            this.refreshSelf();
            // Invoke responsiveDropdown class
			$('[data-toggle="responsive-dropdown"]', $(this.el.dom)).each(function () {
				var newResponsiveDropdown = new responsiveDropdown($(this));
			});
		},
		/**
		 * Refresh responsiveImage on edit
		 */
		editResponsiveImage: function () {
			// Update markup
            this.refreshSelf();
            // Invoke responsiveDropdown class
			$('.responsiveImage div[data-desktopimage]', $(this.el.dom)).each(function () {
				var newResponsiveImage = new responsiveImage($(this));
			});
		}
	};
}());

$(document).ready(function () {
	SPDAuthorUtils.init();
});