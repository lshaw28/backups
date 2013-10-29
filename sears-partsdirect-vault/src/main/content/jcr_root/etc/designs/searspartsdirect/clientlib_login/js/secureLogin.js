/*global window:true, $:true, Class:true, regula:true */
var secureLogin = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes secureLogin class
		 * Uses Regula validation
		 * See documentation: https://github.com/vivin/regula/wiki
		 */
		init: function (el) {
			this.el = el;
			//this.isMobileBrowser = su.isMobileBrowser();
			//this.isTabletSize = isTabletSize;
			//this.isMobileSize = isMobileSize;
			this.serviceCallPending = false;
			this.maxRecallServiceTries = 5;
			this.recallServiceTries = 0;
			this.bindSubmit();
			this.bindCancel();
			this.bindLinks();
			this.bindCheckField();
			this.resetFields();
		},

		/**
		 * Binds the submit button to perform Regula validation
		 * @return {void}
		 */
		bindSubmit: function () {
			var self = this;

			if (!self.serviceCallPending) {

				self.serviceCallPending = true;

				$('[data-submit=true]', self.el).bind('click', function (e) {
					e.preventDefault();
					self.validate();
				});
			}
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
				var serverAddress = window.SPDUtils.getLocationDetails().fullAddress,
					userName = $('[name=loginId]', self.el).val(),
					prepareLoginService = mainSitePathSecure + '/partsdirect/prepareLogin.pd';

				self.prepareLogin( userName, prepareLoginService);
			}
		},
		/**
		 * Binds cancel button to reset fields
		 */
		bindCancel: function () {
			var self = this;

			$('[data-cancel]', self.el).bind('click', function () {
				self.resetFields();
                setTimeout(function(){self.recallServiceTries = 0;}, 3000);
				XDM.send({
					'closeModal': '#loginModal'
				});
			});
		},
		/**
		 * Binds register link to close login modal and open register modal
		 */
		bindLinks: function () {
			var self = this;

			$('[data-target]', self.el).bind('click', function() {
				var target = $(this).data('target');
				XDM.send({
					'closeModal': '#loginModal',
					'openModal': target
				});
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
		 * Handles a successful callback
		 */
		successCallback: function (obj) {
			var self = this,
				commercial_Url = 'https://commercial.searspartsdirect.com';

			self.serviceCallPending = false;

			if (obj === "") {
				// that jBoss bug...increment retry counter
				self.recallServiceTries = parseInt(self.recallServiceTries, 10) + 1;

				if (self.recallServiceTries <= self.maxRecallServiceTries) {
					self.validate();
				} else {
					// else, show server error msg
					self.showServerErrorMsg();
				}
			} else {
				// proceed normally
				obj = $.parseJSON($.trim(obj));

				// NOT authenticated at this point...
				if (!obj.isUserConsumer) {
					// redirect to commercial PD site
					$('[name=loginId]', self.el).attr('name', 'j_username');
					document.secureLoginFormModal.action = commercial_Url+"/partsdirect/commercialLogin.pd?email=" + $('[name=loginId]', self.el).val();
					$('.alert', self.el).html("Our records show you're a member of our commercial parts website. We're automatically redirecting you to Sears Commercial Parts.");
					$('.alert', self.el).removeClass('hidden');
					setTimeout(function(){ document.loginFormModal.submit(); }, 3000);
				} else {
					// They're a normal user
					// submit to SSO
					$('form', self.el)[0].submit();
				}
			}
		},
		prepareLogin: function(username, prepareLoginURL) {
			var self = this,
				hostName = window.SPDUtils.getLocationDetails().fullAddress;

			$.ajax({
				type: "GET",
				dataType: 'text',
				url: prepareLoginURL,
				data: { userName: username,
						authSuccessURL: encodeURI(hostName+'content/searspartsdirect/en/login_form.html?authSuccessURL=true#'+window.parentDomain),
						authFailureURL:encodeURI(hostName+'content/searspartsdirect/en/login_form.html?errorCode=300#'+window.parentDomain)
				},
				xhrFields: {
					withCredentials: true
				},
				success: self.successCallback,
				error: self.failCallback
			});
			return false;
		},
		showUnauthorizedMessage: function () {
			var self = this,
				i = 0,
				errorMessage = 'Unauthorized credentials. Please re-enter.',
				prevHeight = 0;

			prevHeight = $(document.body).height();
			// Populate the alert field with the errors
			$('.alert', self.el).html(errorMessage);
			$('.alert', self.el).removeClass('hidden');
			// blank out input fields
			$('input[type!="hidden"]', self.el).each(function() {
				$(this).val('');
			});
			self.checkHeight(prevHeight);
		},
		showServerErrorMsg: function () {
			var self = this,
				errorMessage = "We're sorry. A server error has occurred. Please wait a moment and retry.",
				prevHeight = 0;

			prevHeight = $(document.body).height();
			// Populate the alert field with the errors
			$('.alert', self.el).html(errorMessage);
			$('.alert', self.el).removeClass('hidden');
			// blank out input fields
			$('input[type!="hidden"]', self.el).each(function() {
				$(this).val('');
			});
			self.checkHeight(prevHeight);
			setTimeout(function(){self.recallServiceTries = 0;}, 3000);
		},
		/**
		 * Checks the current height
		 * @param {number} prevHeight Current document.body height
		 * @return void
		 */
		checkHeight: function (prevHeight) {
			var self = this,
				heightDelta = $(document.body).height() - prevHeight;

			if (heightDelta != 0) {
				XDM.send({
					'heightChange': heightDelta,
					'affectedModal': '#loginModal'
				});
			}
		}
	};
}());