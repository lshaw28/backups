/*global window:true, $:true, Class:true, mainSitePath:true */
var cartNav = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton cartNav
		 * Singleton class for the cartNav component
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			// Properties
			this.modelDropdown = $('#cartModels .dropdown-menu');
			this.guestEdit = $('#cartGuestEdit');
			this.guestControls = $('#cartGuestControls');
			this.editButton = $('#cartModels .edit_js');
			this.removeButton = $('#cartModels .remove_js');
			this.cancelButton = $('#cartModels .cancel_js');
			this.cartDropdown = $('#cartShop .dropdown-menu');
			// Initialize events
			this.bindEvents();
			this.toggleTray();
		},
		/**
		 * Toggle the guest edit mode
		 * @return {void}
		 */
		toggleGuestEdit: function () {
			var self = this;

			// Flip out the controls and checkboxes
			self.modelDropdown.toggleClass('guest-edit');
		},
		/**
		 * Determine whether or not the Remove button should appear actionable
		 * @return {void}
		 */
		setRemoveState: function () {
			var self = this,
				checkedItems = $('input[checked="checked"]', self.modelDropdown);

			if (checkedItems.length > 0) {
				// Set editable class
			} else {
				// Set ineditable class
			}
		},
		/**
		 * Handle the remove button click event
		 * @return {void}
		 */
		removeItems: function () {
			var self = this,
				checkedItems = $('input[checked="checked"]', self.modelDropdown);

			if (checkedItems.length > 0) {
				// Make AJAX call(s)
			} else {
				// Handle error
			}
		},
		/**
		 * Handle AJAX call
		 * @param {object} data AJAX response
		 * @return {void}
		 */
		handleResponse: function (data) {
			var self = this;

			// Check the data object for success paramters
			// If successful, find the parent node of the inputs and remove them
		},
		/**
		 * Toggle data-toggle action
		 * @return {void}
		 */
		toggleTray: function () {
			var self = this,
				isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();

			if (isMobileBreakpoint === true) {
				$('#cartShop [data-toggle]').data('toggle', 'false');
				self.cartDropdown.removeClass('dropdown-menu')
				.addClass('cart-canvas');
			} else {
				$('#cartShop [data-toggle]').data('toggle', 'dropdown');
				self.cartDropdown.removeClass('cart-canvas')
				.addClass('dropdown-menu');
			}
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;

			// My models buttons
			self.editButton.bind('click', function () {
				return false;
			})
			.bind('click', function (e) {
				e.preventDefault();
				self.toggleGuestEdit();
			});
			self.removeButton.bind('click', function () {
				return false;
			})
			.bind('click', function (e) {
				e.preventDefault();
				self.removeItems();
			});
			self.cancelButton.bind('click', function () {
				return false;
			})
			.bind('click', function (e) {
				e.preventDefault();
				self.toggleGuestEdit();
			});
			// Responsive handling of cart tray
			shc.pd.base.util.ViewChange.getInstance().onResponsive(function () {
				self.toggleTray();
			});
		}
	}
}());