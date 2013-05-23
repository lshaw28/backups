/*global $:true, window:true, document:true, Class:true, responsiveImage: true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	$(document).ready(function () {
		/**
		 * Input help text listeners
		 */
		$('[data-inputhelp]').each(function () {
			$(this).bind('blur', function () {
			})
			.bind('focus', function () {
			})
			.bind('change', function () {
			});
		});
		/**
		 * responsiveImage component setup
		 */
		$('.responsiveImage div[data-desktopimage]').each(function () {
			var newResponsiveImage = new ResponsiveImage($(this));
		});
	});
}(window));