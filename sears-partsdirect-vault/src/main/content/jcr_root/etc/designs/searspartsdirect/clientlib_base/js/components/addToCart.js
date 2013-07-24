/*global window:true, $:true, Class:true, mainSitePath:true */
var addToCart = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @class addToCart
		 * Creates an instance of an add to cart button
		 * Uses AJAX to add item then updates the cart drop down
		 *
		 * init: On page load events to fire
		 * @param {object} el jQuery element to attach to
		 * @param {object} qf jQuery element for corresponding quantity field
		 */
		init: function (el, qf) {
			// Properties
			this.el = el;
			this.quantityField = qf;
			this.partNumber = '';
			this.divId = '';
			this.plsId = '';
			// Setup
			this.setProperties();
			this.bindEvents();
		},
		/**
		 * Set instance properties from element attributes
		 * @return {void}
		 */
		setProperties: function () {
			var self = this,
				su = window.SPDUtils;

			// Retrieve properties
			self.partNumber = su.validString(self.el.data('partnumber'));
			self.divId = su.validString(self.el.data('divid'));
			self.plsId = su.validString(self.el.data('plsid'));
		},
		/**
		 * Add item to cart
		 * @return {void}
		 */
		addItem: function () {
			var self = this,
				su = window.SPDUtils,
				quantity = su.validNumber(self.quantityField.value(), 1),
				addAddress = apiPath + 'cart/addtocart',
				params = {};

			// Ensure all parameters are valid
			if (self.partNumber !== '' && self.divId !== '' && self.plsId !== '') {
				// Add standard, required params
				params.partno = self.partNumber;
				params.divid = self.divId;
				params.plsid = self.plsId;
				params.quantity = quantity;

				// Add user if available
				if (su.validString(registeredUserId) !== '') {
					params.userid = registeredUserId;
				}
				// Add cart ID if available
				if (su.validString(cartId) !== '') {
					params.cartid = cartId;
				}

				// Make an AJAX call
				$.ajax({
					type: 'POST',
					url: addAddress,
					async: false,
					contentType: 'application/json',
					dataType: 'JSON',
					data: params
				})
				.success(function (data) {
					self.handleResponse(data);
				})
				.fail(function (e) {
					// Handle error
					console.log(e);
				});
			} else {
				// Handle error
			}

		},
		/**
		 * Process AJAX response
		 * @param {object} data AJAX response
		 * @return {void}
		 */
		handleResponse: function (data) {
			var self = this;

			console.log(data);
		},
		/**
		 * Bind events to button
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;

			self.el.bind('click', function (e) {
				e.preventDefault();
				self.addItem();
			});
		}
	}
}());