/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window.SPDUtils = Class.extend({
		/**
		 * @class SPDUtils
		 * Global utilities and helper methods
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			try {
				console.log('SPDUtils available');
			} catch (e) {
			}
		},
		/**
		 * Check that an object resolves to a valid string
		 * @param {Object} obj Object to validate
		 */
		validString: function (obj) {
			// Type checking ensures faster validation
			if (typeof obj === 'string') {
				return obj;
			} else {
				return '';
			}
		},
		/**
		 * Check that an object resolves to a valid number
		 * @param {Object} obj Object to validate
		 */
		validNumber: function (obj) {
			// Type checking ensures faster validation
			// but also allow string representations through
			if (typeof obj === 'integer') {
				return obj;
			} else if (parseInt(obj) !== null && parseInt(obj) !== 'undefined' && isNaN(parseInt(obj)) === false) {
				return parseInt(obj);
			} else {
				return '';
			}
		},
		/**
		 * Check if the screen is currently sized at a mobile breakpoint
		 * @return {Boolean} Check result
		 */
		isMobileBreakpoint: function () {
			var breakpoints = [],
				currentWidth = $(window).width();
			
			if (breakpoints.indexOf(currentWidth) > -1) {
				return true;
			} else {
				return false;
			}
		},
		/**
		 * Handles responsive input help text
		 * @param {Object} el jQuery element to check
		 */
		checkInput: function (el) {
			var self = window.SPDUtils,
				value = el.value(),
				helpText = el.data('inputhelp'),
				helpTextMobile = el.data('inputhelpmobile');
			
			// If there is no user-entered information, use the appropriate data attribute value
			if (self.validString(value) === '') {
				if (self.validString(helpTextMobile) !== '' && self.isMobileBreakpoint()) {
					el.value(helpTextMobile);
				} else if (self.validString(helpText) !== '') {
					el.value(helpText);
				}
			}
		}
	});
}(window));