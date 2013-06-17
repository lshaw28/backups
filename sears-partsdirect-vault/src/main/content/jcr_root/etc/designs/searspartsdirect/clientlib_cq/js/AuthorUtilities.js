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

			$(window).resize(function () {
				console.log('Window width', $(window).width());
			}).bind('onorientationchange', function () {
				console.log('Window width', $(window).width());
			});
		}
	};
}());

SPDAuthorUtils.init();