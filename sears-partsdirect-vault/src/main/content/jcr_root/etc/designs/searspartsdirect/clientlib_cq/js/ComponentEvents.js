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
		},
		/**
		 * Rerender responsive image on edit
		 * @param {object} el ExtJS caller
		 */
		onResponsiveImageEdit: function () {
			this.refreshSelf();
			var target = $('[data-desktopimage]', this.el.dom),
				newResponsiveImage = new responsiveImage(target);

		},
		/**
		 * Rerender responsive pinch image on edit
		 * @param {object} el ExtJS caller
		 */
		onResponsivePinchImageEdit: function () {
			this.refreshSelf();
			var target = $(this.el.dom),
				newResponsivePinchImage = new responsivePinchImage(target);
		},
		/**
		 * Rerender responsive pinch image within a partList component on edit
		 * @param {object} el ExtJS caller
		 */
		onPartListEdit: function () {
			this.refreshSelf();
			var target = $('.responsiveImage', this.el.dom),
				newResponsivePinchImage = new responsivePinchImage(target);
		},
		/**
		 * Rerender video on edit
		 * @param {object} el ExtJS caller
		 */
		onVideoEdit: function () {
			this.refreshSelf();
			var target = $('[data-youtubeid]', this.el.dom),
				newVideo = new video(target);

		}
	};
	window.SPDComponentEvents.init();
}(window));