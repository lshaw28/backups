/*global window:true, $:true, Class:true, mainSitePath:true, NS:true */
var userData = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @class userData
		 * Singleton class to handle initial login, recently viewed and shopping cart states
		 */
		init: function () {
			// Elements
			this.cartItems = {
// Retrieve elements
				header: $('#cartShop .cartShopHeader_js'),
				checkOut: $('#cartShop .cartShopCheckOut_js'),
				totals: $('#cartShop .cartShopTotals_js'),
				view: $('#cartShop .cartShopView_js'),
				count: $('#cartShop .cartShopCount_js'),
				countBadge: $('#cartShop .count-badge')
			}
			this.cartEmpty = $('#cartShop .cartShopEmpty_js');
			// Begin setup straight away
			this.getHeaderCookies();
			this.displayRecentPartsModels();
			this.checkAPI();
		},
		/**
		 * Retrieves cookie values used by global header items
		 * @return {void}
		 */
		getHeaderCookies: function () {
			var self = this,
				su = window.SPDUtils;

			NS('shc.pd.cookies').username = su.getCookie('username', '');
			NS('shc.pd.cookies').recentlyViewedParts = su.getCookie('recentlyViewedParts', '');
			NS('shc.pd.cookies').recentlyViewedModels = su.getCookie('recentlyViewedModels', '');
			NS('shc.pd.cookies').myProfileModels = su.getCookie('myProfileModels', '');
			NS('shc.pd.cookies').cid = su.getCookie('cid', '');
		},
		/**
		 * Makes AJAX call to user API
		 * @return {void}
		 */
		checkAPI: function () {
			var self = this,
				su = window.SPDUtils,
				userAddress = apiPath.replace('/v1', '/intra/v1') + 'userservice/retrive',
				params = {
					username: NS('shc.pd.cookies').username
				};

			// Validate and add additional parameters
			if (NS('shc.pd.cookies').username === '') {
				// Add profile cookie
				if (NS('shc.pd.cookies').myProfileModels !== '') {
					params.profileid = NS('shc.pd.cookies').myProfileModels;
				}
				// Add cart cookie
				if (NS('shc.pd.cookies').cid !== '') {
					params.cartid = NS('shc.pd.cookies').cid;
				}
			}

			// Make an AJAX call
			$.ajax({
				type: 'GET',
				url: userAddress,
				contentType: 'application/json',
				dataType: 'JSON',
				data: params
			})
			.success(function (data) {
				self.handleResponse(data);
			})
			.fail(function (e) {
				// Handle error
			});
		},
		/**
		 * Handles response from the API
		 * @param {object} resp Response from API call
		 * @return {void}
		 */
		handleResponse: function (resp) {
			var self = this;

			// Set loginNav items
			self.setLoginState(resp);
			// Set recently viewed
			// Set my models
			// Set cart items
			self.displayCartItems(resp.cart.cartLines);

		},
		/**
		 * Set login status
		 * @param {object} resp Response from API call
		 * @return {void}
		 */
		setLoginState: function (resp) {
			var self = this,
				su = window.SPDUtils,
				username = su.validString(resp.username),
				firstName = su.validString(resp.firstName),
				lastName = su.validString(resp.lastName);

			// Logout on success, login on fail
			if (username !== '') {
				$('#loginNavStatus').html('Hello, <strong>' + firstName + '</strong><a href="' + mainSitePath + '/partsdirect/myprofile/logout.action">Logout</a>');
				$('#loginNavProfile').html('<a href="' + mainSitePath + '/partsdirect/myProfile.pd">My Profile</a>');
			}
		},
		/**
		 * Set recently viewed items
		 * @return {void}
		 */
		displayRecentPartsModels: function () {
			var self = this,
				su = window.SPDUtils,
				recentParts = su.tokenize(NS('shc.pd.cookies').recentlyViewedParts, ['partName','partDescription','partURL','partImageURL'], '|'),
				recentModels = su.tokenize(NS('shc.pd.cookies').recentlyViewedModels, ['modelName', 'modelDescription', 'modelURL'], '|'),
				i = 0,
				li = null;

			// Render items and enable toggle
			if (recentModels.length > 0 || recentParts.length > 0) {
				for (i = 0; i < recentModels.length; i = i + 1) {
					li = new modelItemTemplate(recentModels[i]);
					$('#cartRecents .dropdown-menu').append(li);
				}
				for (i = 0; i < recentParts.length; i = i + 1) {
					li = new partItemTemplate(recentParts[i]);
					$('#cartRecents .dropdown-menu').append(li);
				}
				$('#cartRecents [data-toggle]').attr('data-toggle', 'dropdown');
			}
		},
		/**
		 * Set shopping cart items
		 * @param {object} cartLines Array of cart item objects
		 */
		displayCartItems: function (cartLines) {
			var self = this,
				su = window.SPDUtils,
				itemCount = 0,
				i = 0;

			// Handle items
			if (cartLines.length > 0) {
				// Set visibility of elements
				self.cartItems.header.removeClass('inactive');
				self.cartItems.checkOut.removeClass('inactive');
				self.cartItems.totals.removeClass('inactive');
				self.cartItems.view.removeClass('inactive');
				self.cartEmpty.addClass('inactive');

				// Render items
				for (i = 0; i < cartLines.length; i = i + 1) {
					itemCount += self.renderCartItem(cartLines[i]);
				}

				// Set total item count
				if (itemCount > 99) {
					itemCount = '99+';
				}
				self.cartItems.count.text(itemCount);
				self.cartItems.countBadge.text(itemCount);
			}
		},
		/**
		 * Render a shopping cart item and insert it in the drop down
		 * @param {object} item Returned data item
		 * @return {number} Quantity of current item added
		 */
		renderCartItem: function (item) {
			var self = this,
				su = window.SPDUtils,
				quantity = 0,
				li = new cartItemTemplate(item.part, item.quantity);

			// Retrieve quantity
			quantity = item.quantity;
			// Insert element
			self.cartItems.totals.before(li);

			return quantity;
		}
	};
}());