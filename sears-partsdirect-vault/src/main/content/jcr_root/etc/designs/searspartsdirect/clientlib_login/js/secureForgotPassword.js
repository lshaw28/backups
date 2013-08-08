/*global window:true, $:true, Class:true, regula:true */
var secureForgotPassword = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes secureForgotPassword class
		 * Uses Regula validation
		 * See documentation: https://github.com/vivin/regula/wiki
		 */
		init: function (el) {
			this.el = el;
			this.bindSubmit();
			this.bindCancel();
			this.resetFields();
		},
		/**
		 * Binds the submit button to perform Regula validation
		 * @return {void}
		 */
		bindSubmit: function () {
			var self = this;

			$('[data-submit=true]', self.el).bind('click', function (e) {
				e.preventDefault();
				self.validate();
			});
		},
		/**
		 * Validates the registration form and displays friendly errors
		 * @return {void}
		 */
		validate: function () {
			var self = this,
				i = 0,
				errorMessage = '',
				divider = '',
				regulaResponse = regula.validate();

			// Parse the error messages

			for (i = 0; i < regulaResponse.length; i = i + 1) {
				errorMessage += divider + regulaResponse[i].message;
				divider = '<br />';
			}

			// Populate the errors
			$('.alert', self.el).html(errorMessage);

			// Display errors or submit the form
			if (errorMessage.length > 0) {
				$('.alert', self.el).removeClass('hidden');
			} else {

				var userName = $('[name=loginId]', self.el).val();
				//var forgotPasswordService = 'https://www.searspartsdirect.com/partsdirect/ssoForgotPasswordEmailModal.action';
				var forgotPasswordService = mainSitePath + '/partsdirect/ssoForgotPasswordEmailModal.action';

				self.submitForgotPassword( userName, forgotPasswordService);
			}
		},
		/**
		 * Binds cancel button to reset fields
		 */
		bindCancel: function () {
			var self = this;

			$('[data-cancel]', self.el).bind('click', function () {
				self.resetFields();
				self.postMessage({ 'closeModal': '#forgotPasswordModal' });
			});
		},

		resetFields: function () {
			var self = this;

			$('.alert', self.el).addClass('hidden');
			$('input', self.el).each(function() {
				$(this).val('');
			});
		},
		/**
		 * Handles a successful callback
		 */
		successCallback: function (obj) {
			var self = this;
			console.log('forgotPassword success callback');
			if (obj.Status === "success") {
				// if successful, change h1 text and show success paragraph
				$('h1', self.el).html('Email Sent');
				$('.forgot-success', self.el).removeClass('hidden');
				// also, hide fieldset, old paragraph and cancel button.
				$('forgot-intro', self.el).addClass('hidden');
				$('fieldset', self.el).addClass('hidden');
				$('[data-cancel]', self.el).addClass('hidden');
				// finally, change continue button action to close modal
				$('[data-submit=true]', self.el).bind('click', function (e) {
					self.resetFields();
					self.postMessage({ 'closeModal': '#forgotPasswordModal' });
				});
			} else {
				console.log(obj.Status+" is what came back from forgotPassword service");
				// display error message that email was not found
				var errorMsg = "We are sorry. We cannot find the email address you entered. Please verfiy and enter again.";
				$('.alert', self.el).html(errorMessage);
				$('.alert', self.el).removeClass('hidden');
			}
		},

		errorCallback: function (errors) {
			var self = this;
		},

		submitForgotPassword: function(username, forgotPasswordURL) {
			var self = this;

			$.ajax({
				type: "GET",
				async: false,
				contentType: 'application/json',
				dataType: 'JSON',
				url: forgotPasswordURL,
				data: { userName: username},
				success: self.successCallback,
				error: self.errorCallback
			});
			return false;
		},

		/**
		 * Posts a message to the parent page via JavaScript
		 * @param {object} message The object to post
		 */
		postMessage: function (message) {
			var domain = document.location.protocol + '//' + document.location.hostname + ':' + document.location.port;

			if (typeof window['parentDomain'] === 'string') {
				domain = window['parentDomain'];
			}

			top.window.postMessage(message, domain);
		}
	};
}());