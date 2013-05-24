/*global $:true, window:true, document:true, Class:true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	$(window).resize(function () {
		/**
		 * Input help text listeners
		 */
		$('[data-inputhelp]').each(function () {
			// Check in case the breakpoint is different
			window.SPDUtils.checkInput($(this));
		});
	});
}(window));