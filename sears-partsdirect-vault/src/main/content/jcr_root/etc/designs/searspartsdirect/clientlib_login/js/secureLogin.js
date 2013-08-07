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
                var serverAddress = window.SPDUtils.getLocationDetails().fullAddress;
                var userName = $('[name=loginId]', self.el).val();
                var prepareLoginService = 'http://partsbetavip.qa.ch3.s.com/partsdirect/prepareLogin.pd';

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
				self.postMessage({ 'closeModal': '#loginModal' });
			});
		},

        /**
         * Binds register link to close login modal and open register modal
         */
        bindLinks: function () {
            var self = this;

            $('[data-target]', self.el).bind('click', function() {
                var target = $(this).data('target');
                self.postMessage({ 'closeModal': '#loginModal', 'openModal': target});
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
			var self = this;

			$('.alert', self.el).addClass('hidden');
			$('input[type!="hidden"]', self.el).each(function() {
				$(this).val('');
			});

		},
		/**
		 * Handles a successful callback
		 */


        successCallback: function (obj) {
            var self = this,
                commercial_Url = 'https://commercial.searspartsdirect.com';

            // NOT authenticated at this point...
            if (!obj.isUserConsumer) {
                // redirect to commercial PD site
                $('[name=loginId]', self.el).attr('name', 'j_username');
                //$('[name=logonPassword]', self.el).attr('name', 'j_password');
                //logonPassword.name = "j_password";
                //loginId.name = "j_username";
                document.secureLoginFormModal.action = commercial_Url+"/partsdirect/commercialLogin.pd?email=" + $('[name=loginId]', self.el).val();
                $('.alert', self.el).html("Our records show you're a member of our commercial parts website. We're automatically redirecting you to Sears Commercial Parts.");
                $('.alert', self.el).removeClass('hidden');
                setTimeout(function(){document.loginFormModal.submit();}, 3000);
            } else {
                // They're a normal user
                // submit to SSO
                $('form', self.el)[0].submit();
            }
        },

        failCallback: function (errors) {
               // this ajax call should never fail
        },

        prepareLogin: function(username, prepareLoginURL) {
            var self = this,
                hostName = window.SPDUtils.getLocationDetails().fullAddress;

            $.ajax({
                type: "GET",
                async: false,
                contentType: 'application/json',
                dataType: 'JSON',
                url: prepareLoginURL,
                data: { userName: username,
                        authSuccessURL: encodeURI(hostName+'content/searspartsdirect/en/login_form.html?authSuccessURL=true'),
                        authFailureURL:encodeURI(hostName+'content/searspartsdirect/en/login_form.html?errorCode=300')
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
                errorMessage = 'Unauthorized credentials. Please re-enter.';

            // Populate the alert field with the errors
            $('.alert', self.el).html(errorMessage);
            $('.alert', self.el).removeClass('hidden');
            // blank out input fields
            $('input', self.el).each(function() {
                $(this).val('');
            });
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