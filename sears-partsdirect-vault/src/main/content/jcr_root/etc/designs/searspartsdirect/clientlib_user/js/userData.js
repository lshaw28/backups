/*global window:true, $:true, Class:true, mainSitePath:true, NS:true, XDM: true */
var userData = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @class userData
		 * Singleton class to retrieve userData from API
		 */
		init: function () {
			// Begin setup straight away
			this.getHeaderCookies();
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
				userAddress = apiPathSecure + 'userservice/retrive',
				params = {};

			// Validate and add additional parameters
			if (NS('shc.pd.cookies').username !== '') {
				params.username = NS('shc.pd.cookies').username;
			} else {
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
				async: false,
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

			if (resp) {
				XDM.send({ 'resp': resp });
			}
		}
	};
}());