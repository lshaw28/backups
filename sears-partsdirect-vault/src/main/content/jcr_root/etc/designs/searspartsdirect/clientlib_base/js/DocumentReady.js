/*global $:true, window:true, document:true, Class:true */
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
	});
}(window));