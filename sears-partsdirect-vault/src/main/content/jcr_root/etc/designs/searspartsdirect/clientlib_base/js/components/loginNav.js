/*global window:true, $:true, Class:true, mainSitePath:true */
var loginNav = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton loginNav
		 * Singleton class for the loginNav trigger
		 *
		 * init: On page load events to fire
		 * @param {object} el jQuery element
		 */
		init: function (el) {
			// Properties
			this.el = el;
			// Initialize events
			this.bindEvents();
			console.log('loginNav initialized');
		},
		bindEvents: function () {
			var self = this;

			// Bind trigger events
			$('.trigger').bind('click', function () {
				self.el.toggleClass('active');
			})
			.bind('tap', function () {
				self.el.toggleClass('active');
			});
		}
	}
}());