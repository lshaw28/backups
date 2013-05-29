/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window.SPDComponentEvents = {
		/**
		 * @namespace SPDComponentEvents
		 * Helpers for specific component events
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			console.log('SPDComponentEvents available');
		},
		/**
		 * Rerender responsive image on edit
		 * @param {object} el ExtJS caller
		 */
		onResponsiveImageEdit: function () {
			var target = $(this.el.dom),
				newResponsiveImage = new responsiveImage(target);

		}
	};
	window.SPDComponentEvents.init();
}(window));