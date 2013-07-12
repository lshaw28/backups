/*global window:true, $:true, Class:true */
var modelNumberSearch = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes modelNumberSearch class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Properties
			this.inputField = null;
			this.inputHelp = '';
			this.inputHelpMobile = '';
			this.messageArea = null;
			this.button = null;
			// Find elements
			this.setProperties();
			// Bind events
			this.bindEvents();
		},
		/**
		 * Finds related elements
		 * @return {void}
		 */
		setProperties: function () {
			var self = this,
				su = window.SPDUtils;

			// Input field
			self.inputField = $('input[type="text"]', self.el);
			self.inputHelp = self.inputField.data('inputhelp');
			self.inputHelpMobile = self.inputField.data('inputhelpmobile');
			// Message area
			self.messageArea = $('.display-message', self.el);
			// Button
			self.button = $('button', self.el);
		},
		/**
		 * Checks input value and starts AJAX call
		 * @return {void}
		 */
		search: function () {
			var self = this,
				su = window.SPDUtils,
				searchTerm = su.validString(self.inputField.attr('value'));

			// Check the input value
			if (searchTerm !== '' && searchTerm !== self.inputHelp && searchTerm !== self.inputHelpMobile) {
				// Clear any existing error message
				self.displayMessage('', '');
				// Make an AJAX call
				$.ajax({
					'url': 'http://www.someurl.com/',
					'crossDomain': true,
					'dataType': 'JSON',
					'headers': {
						'modelNumber': searchTerm
					}
				}).done(function (data) {
					self.searchResponse(data);
				});
			} else {
				// Display an error message
				self.displayMessage('Please provide a model number.', 'error');
			}
		},
		/**
		 * Handles search results
		 * @param {object} resp Response from AJAX call
		 * @return {void}
		 */
		searchResponse: function (resp) {
			console.log(resp);
			var self = this;

			// Test redirect logic
			self.redirect(resp);
		},
		/**
		 * Handles a redirect to the single result router
		 * @param {object} resp Response from AJAX call
		 * @return {void}
		 */
		redirect: function (resp) {
			console.log(resp);
			var self = this,
				su = window.SPDUtils,
				query = '',
				brand = '',
				category = '',
				model = '',
				link = '';

			query += '?brand=' + brand;
			query += '&category=' + category;
			query += '&model=' + model;
			query += '&link=' + link;

			//document.location.href = su.getLocationDetails() + modelSearchServletPath + query;
			console.log(su.getLocationDetails() + modelSearchServletPath + query);
		},
		/**
		 * Displays a message to the user
		 * @param {string} msg Message HTML
		 * @param {type} type Class to use
		 * @return {void}
		 */
		displayMessage: function (msg, type) {
			var self = this;

			// Set the message
			self.messageArea.html(msg);
			// Set the classes; faster than using toggles
			self.messageArea.attr('class', 'display-message ' + type);
		},
		/**
		 * Bind form events
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this,
				key = -1;

			// Bind input events
			self.inputField.bind('keyup', function () {
				return false;
			})
			.bind('keyup', function (e) {
				// Determine which key was pressed
				if (e.keyCode) {
					key = e.keyCode;
				} else if (e.which) {
					key = e.which;
				}

				// Search if the user hit enter
				if (key === 13) {
					self.search();
				}
			});
			// Bind button
			self.button.bind('click', function (e) {
				e.preventDefault();
				self.search();
			});
		}
	};
}());