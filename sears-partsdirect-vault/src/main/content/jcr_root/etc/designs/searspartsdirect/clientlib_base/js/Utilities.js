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
			window.SPDUtils.checkConsole();
			console.log('SPDUtils available');
		},
		/*
		 * Creates a console stub for unsupported browsers
		 */
		checkConsole: function () {
			if (!console) {
				window.console = {
					log: function () {
					}
				};
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
			if (typeof obj === 'number') {
				return obj;
			} else if (isNaN(parseInt(obj, 10)) === false) {
				return parseInt(obj, 10);
			} else {
				return 0;
			}
		},
		/**
		 * Check if the screen is currently sized at an internally-defined mobile breakpoint
		 * @return {Boolean} Check result
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
		 * @return {Boolean} Check result
		 */
		isTabletBreakpoint: function () {
			var currentWidth = parseInt($(window).width(), 10);

			if (currentWidth > 767 && currentWidth < 1025) {
				return true;
			} else {
				return false;
			}
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
		}
	};
	window.SPDUtils.init();
}(window));