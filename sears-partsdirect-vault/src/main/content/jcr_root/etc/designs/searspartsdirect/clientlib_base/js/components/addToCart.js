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
			// Elements
			this.cartItems = {
				header: null,
				checkOut: null,
				totals: null,
				view: null,
				count: null,
				countBadge: null
			}
			this.cartEmpty = null;
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
			self.partNumber = self.el.data('partnumber');
			self.divId = self.el.data('divid');
			self.plsId = self.el.data('plsid');
			// Retrieve elements
			self.cartItems.header = $('#cartShop .cartShopHeader_js');
			self.cartItems.checkOut = $('#cartShop .cartShopCheckOut_js');
			self.cartItems.totals = $('#cartShop .cartShopTotals_js');
			self.cartItems.view = $('#cartShop .cartShopView_js');
			self.cartItems.count = $('.cartShopCount_js', self.cartItems.totals);
			self.cartItems.countBadge = $('#cartShop .count-badge');
			self.cartEmpty = $('#cartShop .cartShopEmpty_js');
		},
		/**
		 * Add item to cart
		 * @return {void}
		 */
		addItem: function () {
			var self = this,
				su = window.SPDUtils,
				quantity = su.validNumber(self.quantityField.attr('value'), 1),
				addAddress = apiPath + 'cart/addtocart',
				params = {
					partno: self.partNumber,
					divid: self.divId,
					plsid: self.plsId,
					quantity: quantity
				};

			// Ensure all parameters are valid
			if (self.partNumber !== '' && self.divId !== '' && self.plsId !== '') {
				// Add user param if available
				if (su.validString(registeredUserId) !== '') {
					params.userid = registeredUserId;
				}
				// Add cart ID param if available
				if (su.validString(cartId) !== '') {
					params.cartid = cartId;
				}

				// Make an AJAX call
				$.ajax({
					type: 'GET',
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
			var self = this,
				i = 0,
				itemCount = 0;

			// Set cartId
			cartId = data.cartId;

			// Handle items
			if (data.cartParts.length > 0) {
				// Set visibility of elements
				for (i = 0; i < self.cartItems.length; i = i + 1) {
					self.cartItems[i].removeClass('inactive').addClass('active');
				}
				self.cartEmpty.removeClass('active').addClass('inactive');
				// Remove current items - ensures quantity changes are reflected
				$('#cartShop .cart-item').remove();

				// Render new items
				for (i = 0; i < data.cartParts.length; i = i + 1) {
					itemCount += self.renderItem(data.cartParts[i]);
				}

				// Set total item count
				self.cartItems.count.text(itemCount);
				self.cartItems.countBadge.text(itemCount);
			} else {
				// Set visibility of elements
				for (i = 0; i < self.cartItems.length; i = i + 1) {
					self.cartItems[i].removeClass('active').addClass('inactive');
				}
				self.cartEmpty.removeClass('inactive').addClass('active');
			}

			console.log(data);
		},
		/**
		 * Render a shopping cart item and insert it in the drop down
		 * @param {object} item Returned data item
		 * @return {number} Quantity of current item added
		 */
		renderItem: function (item) {
			var self = this,
				quantity = 0,
				li = $('<li />'),
				description = '';

			// Retrieve information
			quantity = item.quantity;
			if (item.description) {
				description = item.description;
			} else {
				description = item.partNumber;
			}
			if (description.length > 17) {
				description = description.substring(0, 17) + '...';
			}

			li.addClass('cart-item');
			li.html('<span class="cart-part">' + description + '</span><span class="cart-quantity">' + quantity + '</span>');
			self.cartItems.totals.before(li);

			return quantity;
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