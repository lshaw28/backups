/*global window:true, $:true, Class:true, regula:true */
var secureRegister = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes secureRegister class
		 * Uses Regula validation
		 * See documentation: https://github.com/vivin/regula/wiki
		 */
		init: function (el) {
			this.el = el;
			this.bindSubmit();
			this.bindCancel();
			this.bindLinks();
			this.bindCheckField();
			this.bindPassword();
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
				prevHeight = 0,
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
				prevHeight = $(document.body).height();
				$('.alert', self.el).removeClass('hidden');
				self.checkHeight(prevHeight);
			} else {
				var hostName = window.SPDUtils.getLocationDetails().fullAddress,
					tempRedirectURL = hostName+'content/searspartsdirect/en/login_form.html?authSuccessURL=true#'+window.parentDomain;

				// set the hidden form redirect url values
				$('#currentPageURL').val(tempRedirectURL);
				$('#successfulRegistrationURL').val(tempRedirectURL);
				// for the register modal, just submit the form
				// (no need for ajax calls)
				$('form', self.el)[0].submit();
			}
		},
		/**
		 * Binds cancel button to reset fields
		 */
		bindCancel: function () {
			var self = this;

			$('[data-cancel]', self.el).bind('click', function () {
				self.resetFields();
				self.postMessage({ 'closeModal': '#registerModal' });
			});
		},
		/**
		 * Binds register link to close login modal and open register modal
		 */
		bindLinks: function () {
			var self = this;

			$('[data-target]', self.el).bind('click', function() {
				var target = $(this).data('target');
				self.postMessage({ 'closeModal': '#registerModal', 'openModal': target});
			});
		},
		/**
		 * Binds any related fields so changing one affects the other
		 */
		bindCheckField: function () {
			var self = this;

			$('[data-checkfield]', self.el).each(function () {
				var me = $(this),
					relative = $(me.data('checkfield'));

				me.bind('change', function () {
					var checked = me.attr('checked');

					if (checked === 'true' || checked === 'checked') {
						relative.attr('value', me.attr('value'));
					} else {
						relative.attr('value', '');
					}
				});
			});
		},
		bindPassword: function () {
			var self = this,
				prevHeight = 0;

			$('[data-focus]', self.el).bind('focus', function() {
					prevHeight = $(document.body).height();
					$('.passwordRules').removeClass('hidden');
					self.checkHeight(prevHeight);
			}).bind('blur', function() {
					prevHeight = $(document.body).height();
					$('.passwordRules').addClass('hidden');
					self.checkHeight(prevHeight);
			});
		},
		resetFields: function () {
			var self = this,
				prevHeight = 0;

			prevHeight = $(document.body).height();
			$('.alert', self.el).addClass('hidden');
			$('input[type!="hidden"]', self.el).each(function() {
				$(this).val('');
			});
			self.checkHeight(prevHeight);
		},
		/**
		 * Creates validation object literal
		 * @return {object}
		 */
		createValidationObject: function () {
			var self = this;

			return {
				groups: [regula.Group[self.group]]
			};
		},
		registerUser: function(username, registerURL) {
			var self = this,
				hostName = window.SPDUtils.getLocationDetails().fullAddress;

			$.ajax({
				type: "GET",
				async: false,
				contentType: 'application/json',
				dataType: 'JSON',
				url: prepareLoginURL,
				data: { firstName: username,
						lastName: lastName,
						email: email,
						password: password
				},
				success: self.successCallback,
				error: self.failCallback
			});
			return false;
		},
		successCallback: function (obj) {
			var self = this
			// hit the login service with username/pw
			// (as if user entered their credentials in login modal)
			// absolutely the same flow as login
			// so
		},
		failCallback: function (errors) {
			// this ajax call should never fail
		},
		showUnauthorizedMessage: function () {
			var self = this,
				i = 0,
				prevHeight = 0,
				errorMessage = 'Unauthorized credentials. Please re-enter.';

			prevHeight = $(document.body).height();
			// Populate the alert field with the errors
			$('.alert', self.el).html(errorMessage);
			$('.alert', self.el).removeClass('hidden');
			// blank out input fields
			$('input', self.el).each(function() {
				$(this).val('');
			});
			self.checkHeight(prevHeight);
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
		},
		checkHeight: function (prevHeight) {
			var self = this,
				heightDelta = 0;

			heightDelta = $(document.body).height()-prevHeight;
			// console.log("register height delta: "+heightDelta);
			if (heightDelta != 0) {
				self.postMessage({ 'heightChange': heightDelta, 'affectedModal': '#registerModal' });
			}
		}
	};
}());