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
		}
	};
}());

$(document).ready(function () {
	SPDAuthorUtils.init();
});