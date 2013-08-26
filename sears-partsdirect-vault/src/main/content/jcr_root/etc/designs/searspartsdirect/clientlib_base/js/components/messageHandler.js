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
			var self = this,
				formattedData = self.format(message.data.toString());

			// Validate message object
			if (formattedData) {
				// Take decisions based on properties of the message's data object
				if (formattedData.closeModal) {
					$(formattedData.closeModal).modal('hide');
				}
				if (formattedData.openModal) {
					$(formattedData.openModal).modal('show');
				}
				if (formattedData.redirect) {
					document.location.href = formattedData.redirect;
				}
				if (formattedData.reload) {
					document.location.reload();
				}
				if (formattedData.heightChange) {
					var modal = $(formattedData.affectedModal),
						iFrame = $('iframe', modal),
						newIFrameHeight = (modal.height() + formattedData.heightChange) - iFrame.offset().top,
						newModalHeight = modal.height() + formattedData.heightChange;

					iFrame.height(newIFrameHeight);
					modal.height(newModalHeight);
				}
			}
		},
		format: function (data) {
			var items = data.split('&'),
				returnData = {},
				name = '',
				val = '';

			for (var i = 0; i < items.length; i++) {
				name = items[i].split('=')[0];
				val = items[i].split('=')[1];
				returnData[name] = val;
			}

			return returnData;
		}
	}
}());