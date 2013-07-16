/*global $:true, window:true, document:true, Class:true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
		/**
		 * Input help text listeners
		 */
		$('[data-inputhelp]').each(function () {
			// Check in case the breakpoint is different
			window.SPDUtils.checkInput($(this));
		});
		/**
		 * Link help text listeners
		 */
		$('[data-texthelp]').each(function () {
			// Check in case the breakpoint is different
			window.SPDUtils.checkLink($(this));
		});
	});
}(window));