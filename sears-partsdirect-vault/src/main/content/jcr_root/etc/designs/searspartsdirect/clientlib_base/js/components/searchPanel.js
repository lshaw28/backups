/*global window:true, $:true, Class:true, mainSitePath:true */
var searchPanel = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton searchPanel
		 * Singleton class for the searchPanel component
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			// Initialize events
			this.findItems();
			this.bindEvents();
			console.log('searchPanel initialized');
		},
		/**
		 * Finds dropdown items
		 * @return {void}
		 */
		findItems: function () {
			var self = this;

			// Bind an event to each drop-down selection
			$('#searchContent .dropdown-menu li a').bind('click', function (e) {
				e.preventDefault();
				self.selectType($(this));
			});
		},
		/**
		 * Handles type selection
		 * @param {object} el jQuery element
		 * @return {void}
		 */
		selectType: function (el) {
			var self = this,
				value = $('#searchBarField').attr('value'),
				action = mainSitePath + '/partsdirect/' + el.data('postpath') + '/',
				modelNumber = '',
				partNumber = '';

			// Update selection status
			$('#searchContent .dropdown-menu li').removeClass('selected');
			el.parent().addClass('selected');
			// Make sure the value isn't the help text
			if (value === $('#searchBarField').data('inputhelp') || value === $('#searchBarField').data('inputhelpmobile')) {
				value = '';
			}
			// Update hidden fields
			if (el.data('pathtaken') === 'modelSearch') {
				modelNumber = value;
			} else {
				partNumber = value;
			}
			$('#shdMod').attr('value', modelNumber);
			$('#shdPart').attr('value', partNumber);
			$('#pathTaken').attr('value', el.data('pathtaken'));
			// Update form action
			$('#searchBarForm').attr('action', action + encodeURIComponent(value));
			// Activate or deactivate the button
			if (value === '') {
				$('#searchModelsParts').attr('disabled', true);
			} else {
				$('#searchModelsParts').attr('disabled', false);
			}
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this,
				selectStatement = '#searchContent .dropdown-menu li.selected a';

			// Set initial values
			self.selectType($(selectStatement));

			// Bind events on search field
			$('#searchBarField').bind('blur', function () {
				self.selectType($(selectStatement));
			})
			.bind('change', function () {
				self.selectType($(selectStatement));
			});
		}
	}
}());