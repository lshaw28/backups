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
			console.log('SPDAuthorUtils available');
			var self = this;
			
			// @TODO this is a dev tool, REMOVE ASAP
			try {
				console.log("%cAUTHOR UTILITIES IS ENABLED, DISABLE IN PRODUCTION", "background: #7a0000; color: white; font-size: x-large");
			} catch (e) {
				
			}
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function (args) {
				console.log('Application Width: ', args[0]);
			});
		}
	};
}());

$(document).ready(function () {
	SPDAuthorUtils.init();
});