/*global window:true, $:true, Class:true, mainSitePath:true */
var messageHandler = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton windowMessaging
		 * Singleton class that handles window messaging events
		 *
		 * init: On load events to fire
		 */
		init: function () {
		},
		/**
		 * Handle posted message
		 * @param {object} message Message object
		 */
		handleMessage: function (message) {
			var self = this;

			// Validate message object
			// You can check message.origin, message.data or message.source
			if (message.data) {
				// Take decisions based on properties of the message's data object
				if (message.data.closeModal) {
					$(message.data.closeModal).modal('hide');
				}
                if (message.data.openModal) {
                    $(message.data.openModal).modal('show');
                }
                if (message.data.redirect) {
                    document.location.href = message.data.redirect;
                }
				if (message.data.reload) {
					document.location.reload();
				}
                if (message.data.heightChange) {
                    var newHeight = $(message.data.affectedModal).height()-message.data.heightChange;
                    $(message.data.affectedModal).find('iframe').height(newHeight);
                }
			}
		}
	}
}());