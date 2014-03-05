/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window['SPDUtils'] = {
		/**
		 * @namespace SPDUtils
		 * Global utilities and helper methods
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			var self = this;

			self.getGlobalVariables();
			self.getQueryParameters();
			self.setVendorCSS();
		},
		/**
		 * Retrieve and set global variables
		 * @return {void}
		 */
		getGlobalVariables: function () {
			var self = this;

			// Head metadata
			$('meta[name^="global-"]').each(function () {
				var newName = $(this).attr('name').replace('global-', ''),
					newContent = $(this).attr('content');

				window[newName] = newContent;
			});
			// Template name for tracking
			window['templateName'] = $('[data-templatename]').data('templatename');
			// API path protocol fix
			if (self.validString(window['apiPath']) !== '' && self.validString(window['apiPathSecure']) !== '') {
				if (document.location.href.indexOf('https') > -1) {
					window['apiPath'] = window['apiPathSecure'];
				}
			}
			// AJAX site path protocol fix
			window['ajaxSitePath'] = mainSitePath;
			if (self.validString(window['mainSitePath']) !== '' && self.validString(window['mainSitePathSecure']) !== '') {
				if (document.location.href.indexOf('https') > -1) {
					window['ajaxSitePath'] = window['mainSitePathSecure'];
				}
			}
		},
		/**
		 * Creates objects required by AddThis
		 */
		prepareAddThis: function () {
			var self = this,
				addThisUrl = '//s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4f903dd609463926',
				script = null;

			// Set global variables
			window['addthis_share'] = {
				'url': document.location.href
			};
			window['addthis_config'] = {
				'data_track_addressbar': false
			};
			// Append script to the body
			script = $('<script />');
			script.attr('src', self.getLocationDetails().protocol + addThisUrl)
				.attr('type', 'text/javascript')
				.attr('language', 'javascript');
			$('body').append(script);
		},
		/**
		 * Get query string parameters
		 * @return {void}
		 */
		getQueryParameters: function () {
			var returnObj = {},
				query = window.location.search.substring(1),
				spaceReg = /\+/g,
				queryReg = /([^&=]+)=?([^&]*)/g,
				match,
				name = '',
				value = '';

			/* Parse values */
			while (match = queryReg.exec(query)) {
				name = decodeURIComponent(match[1].replace(spaceReg, ' '));
				value = decodeURIComponent(match[2].replace(spaceReg, ' '));
				returnObj[name] = value;
			}

			/* Create object */
			window['queryParams'] = returnObj;
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
		 * Check that an object resolves to a valid boolean
		 * @param {object} obj Object to validate
		 * @param {boolean} retval Optional return value
		 */
		validBoolean: function (obj, retval) {
			// Type checking ensures faster validation
			if (typeof obj === 'boolean') {
				return obj;
			} else if (typeof obj === 'string' && (obj === 'true')) {
				return true;
			} else if (typeof obj === 'string' && (obj === 'false')) {
				return false;
			} else if (typeof obj === 'number' && (obj === 1)) {
				return true;
			} else if (typeof obj === 'number' && (obj === 0)) {
				return false;
			} else if (typeof retval === 'boolean') {
				return retval;
			} else {
				return false;
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
            return ('ontouchstart' in document.documentElement);
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
		},
		/**
		 * Gets the value of a cookie
		 * @param {string} name Name of the cookie to retrieve
		 * @param {string} retval Optional default return value
		 * @return {string} Value of cookie
		 */
		getCookie: function (name, retval) {
			var su = this,
				allCookies = document.cookie.split(';'),
				i = 0,
				cookieName = '',
				cookieValue = '';

			// Set initial return value
			retval = su.validString(retval, '');

			// Parse cookie items
			for (i = 0; i < allCookies.length; i = i + 1) {
				if (typeof allCookies[i] === 'string') {
					try {
						cookieName = allCookies[i].split('=')[0].trim();
					} catch (e) {
						cookieName = su.validString(allCookies[i].split('=')[0]);
					}
					try {
						cookieValue = allCookies[i].split('=')[1].trim();
					} catch (e) {
						cookieValue = su.validString(allCookies[i].split('=')[1]);
					}

					if (cookieName.toLowerCase() === name.toLowerCase()) {
						retval = cookieValue;
						break;
					}
				}
			}

			return retval;
		},
		/**
		 * Tokenize a string into an array of user-defined objects
		 * @param {string} input The original string to split
		 * @param {object} template Template object to return
		 * @param {string} delimiter The delimit to split against
		 */
		tokenize: function (input, template, delimiter) {
			var su = this,
				obj = {},
				items = input.split(delimiter),
				output = new Array(),
				length = template.length,
				modulus = 0,
				i = 0;

			if (items.length >= length) {
				for (i = 0; i < items.length; i = i + 1) {
					modulus = i % length;

					// New instance
					if (i % length === 0) {
						obj = {};
					}
					// Update properties
					obj[template[modulus]] = items[i];
					// Push instance
					if ((modulus === (length - 1)) || ((i + 1) === items.length)) {
						output.push(obj);
					}
				}
			}

			return output;
		},
		/**
		 * Attempt tracking call
		 * @param {object} params Parameters to pass to the CQ record method if it exists
		 * @param {string} componentName The name of the component to track
		 * @return {void}
		 */
		trackEvent: function (params, componentName) {
			var self = this;
			// Grab the page title
			params.values.pageTitle = $('title').text();

			// Component name is complicated
			componentName = self.validString(componentName);
			if (componentName !== '' && self.validString(window['templateName']) !== '') {
				componentName = componentName.replace('#templateName', templateName);
			}
			params.values.componentName = componentName;
			console.log('should call omniture here');

			// Check tracking is available
			if (typeof CQ_Analytics.record === 'function') {
				CQ_Analytics.record(params);
			}
		},
		/**
		 * Sets vendor-specific CSS properties for CSS3 techniques
		 * @return {void}
		 */
		setVendorCSS: function () {
			var div = $('<div />'),
				i = 0,
				transforms = ['transform', 'msTransform', 'MozTransform', 'WebkitTransform', 'OTransform'];
		
			for (i = 0; i < transforms.length; ++i) {
				if (typeof div[0].style[transforms[i]] !== 'undefined') {
					window['CSSTransform'] = transforms[i];
				}
			}
		}
	};
	window.SPDUtils.init();
}(window));