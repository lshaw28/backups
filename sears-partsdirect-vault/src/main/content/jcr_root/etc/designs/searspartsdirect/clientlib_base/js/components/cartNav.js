/*global window:true, $:true, Class:true, mainSitePath:true */
var cartNav = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton cartNav
		 * Singleton class for the cartNav component
		 * Handles Recently Viewed, My Models and Cart
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
			this.removedModels = new Array();
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
		 * Handle the remove button click event
		 * @return {void}
		 */
		removeItems: function () {
			var self = this,
				deleteAddress = apiPath + 'profile/models/delete?cookieId=' + guestCookieId,
				itemCount = 0;

			// Create query string parameters
			// API listens for multiple instances of modelId rather than a comma-separated string
			$('input', self.modelDropdown).each(function () {
				if ($(this)[0].checked === true) {
					deleteAddress += '&modelId=' + $(this).attr('value');
					self.removedModels.push($(this).attr('value'));
					itemCount = itemCount + 1;
				}
			});
			// Attempt AJAX call
			if (itemCount > 0) {
				$.ajax({
					type: 'GET',
					url: deleteAddress,
					async: false,
					contentType: 'application/json',
					dataType: 'JSON',
					mimeType: 'application/json;charset=UTF-8'
				})
				.success(function (data) {
					self.handleResponse(data);
				})
				.fail(function (e) {
				});
			}
		},
		/**
		 * Handle AJAX call
		 * @param {object} data AJAX response
		 * @return {void}
		 */
		handleResponse: function (data) {
			var self = this,
				totalCount = $('input', self.modelDropdown).length,
				removedCount = self.removedModels.length,
				returnedCount = data.profileModelsList.length,
				i = 0;

			// Compare lengths and remove elements
			if ((removedCount + returnedCount) === totalCount) {
				// Remove each item based on its ID
				for (i = 0; i < self.removedModels.length; i = i + 1) {
					$('input[value="' + self.removedModels[i] + '"]', self.modelDropdown).parent().remove();
				}
				self.removedModels = new Array();
			}
		},
		/**
		 * Toggle data-toggle action
		 * @return {void}
		 */
		toggleTray: function () {
			var self = this,
				viewportWidth = $('#viewport').width();

			if (viewportWidth < 651) {
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