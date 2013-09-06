/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window.SPDUtils = {
		/**
		 * @namespace SPDUtils
		 * Global utilities and helper methods
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			window.SPDUtils.getGlobalVariables();
			window.SPDUtils.setAddThisVariables();
		},
		/**
		 * Retrieve and set global variables
		 * @return {void}
		 */
		getGlobalVariables: function () {
			var self = this;

			$('meta[name^="global-"]').each(function () {
				var newName = $(this).attr('name').replace('global-', ''),
					newContent = $(this).attr('content');

				window[newName] = newContent;
			});
			// API path protocol fix
			if (self.validString(window['apiPath']) !== '' && self.validString(window['apiPathSecure']) !== '') {
				if (document.location.href.indexOf('https') > -1) {
					window['apiPath'] = window['apiPathSecure'];
				}
			}
		},
		/**
		 * Creates objects required by AddThis
		 */
		setAddThisVariables: function () {
			window['addthis_share'] = {
				'url': document.location.href
			};
			window['addthis_config'] = {
				'data_track_addressbar': false
			};
		},
		/**
		 * Check that an object resolves to a valid string
		 * @param {object} obj Object to validate
		 * @param {string} retval Optional return value
		 */
		validString: function (obj, retval) {
			// Type checking ensures faster validation
			if (typeof obj === 'string') {
				return obj;
			} else if (typeof retval === 'string') {
				return retval;
			} else {
				return '';
			}
		},
		/**
		 * Check that an object resolves to a valid number
		 * @param {object} obj Object to validate
		 * @param {number} retval Optional return value
		 */
		validNumber: function (obj, retval) {
			// Type checking ensures faster validation
			// but also allow string representations through
			if (typeof obj === 'number') {
				return obj;
			} else if (isNaN(parseInt(obj, 10)) === false) {
				return parseInt(obj, 10);
			} else if (typeof retval === 'number') {
				return retval;
			} else if (isNaN(parseInt(retval, 10)) === false) {
				return parseInt(retval, 10);
			} else {
				return 0;
			}
		},
		/**
		 * Check if the screen is currently sized at an internally-defined mobile breakpoint
		 * @return {boolean} Check result
		 */
		isMobileBreakpoint: function () {
			var currentWidth = parseInt($(window).width(), 10);

			if (currentWidth < 768) {
				return true;
			} else {
				return false;
			}
		},
		/**
		 * Check if the screen is currently sized at an internally-defined tablet breakpoint
		 * @return {boolean} Check result
		 */
		isTabletBreakpoint: function () {
			var currentWidth = parseInt($(window).width(), 10);

			if (currentWidth > 767 && currentWidth < 1025) {
				return true;
			} else {
				return false;
			}
		},
        isMobileBrowser: function() {
            return ("ontouchstart" in document.documentElement);
        },
		/**
		 * Retrieve the current protocol, host name and path
		 * @return {object}
		 */
		getLocationDetails: function () {
			var details = {
				protocol: document.location.protocol,
				hostName: document.location.hostname,
				port: document.location.port,
				fullAddress: document.location.protocol + '//' + document.location.hostname + (document.location.port !== '' ? ':' + document.location.port : '') + '/'
			};
			return details;
		},
		/**
		 * Handles responsive input help text
		 * @param {object} el jQuery element to check
		 * @param {boolean} edit Optional boolean denoting the user is about to edit
		 */
		checkInput: function (el, edit) {
			var self = window.SPDUtils,
				value = el.attr('value'),
				helpText = el.data('inputhelp'),
				helpTextMobile = el.data('inputhelpmobile'),
				newValue = '';

			// Check attribute values
			if (self.validString(helpTextMobile) !== '' && self.isMobileBreakpoint() === true) {
				newValue = helpTextMobile;
			} else if (self.validString(helpText) !== '') {
				newValue = helpText;
			}
			// Determine what to display
			if ((value === helpText || value === helpTextMobile) && edit === true) {
				// Edit mode, value is help text
				el.attr('value', '');
			} else if ((value === '' || value === helpText || value === helpTextMobile) && edit !== true) {
				// Not edit mode, value is empty
				el.attr('value', newValue);
			}
		},
		/**
		 * Handles responsive link text
		 * @param {object} el jQuery element to check
		 */
		checkLink: function (el) {
			var self = window.SPDUtils,
				value = el.text(),
				helpText = el.data('texthelp'),
				helpTextMobile = el.data('texthelpmobile'),
				newValue = value;

			// Check attribute values
			if (self.validString(helpTextMobile) !== '' && self.isMobileBreakpoint() === true) {
				newValue = helpTextMobile;
			} else if (self.validString(helpText) !== '') {
				newValue = helpText;
			}
			// Change if you need to
			if (value !== newValue) {
				el.text(newValue);
			}
		},
		/**
		 * Generates a GUID
		 * @return {string}
		 */
		s4: function () {
			return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
		},
		getGUID: function () {
			var su = window.SPDUtils;

			return su.s4() + su.s4() + su.s4() + su.s4() + su.s4() + su.s4() + su.s4() + su.s4();
		},
		/**
		 * Sets a domain-level cookie
		 * @param {string} name Name of cookie to create
		 * @param {string} val Value of cookie to create
		 * @param {number} days Optional number of days to expire in
		 */
		setCookie: function (name, value, days) {
			var self = this,
				expireDate = new Date(),
				cookieValue = '';

			// Set the expiration date
			if (self.validNumber(days, 0) === 0) {
				days = 1000;
			}
			expireDate.setDate(expireDate.getDate() + days);
			// Format the cookie value
			cookieValue = escape(value) + '; expires=' + expireDate.toGMTString() + '; path=/';
			// Store the cookie
			document.cookie = name + '=' + cookieValue;
		}
	};
	window.SPDUtils.init();
}(window));