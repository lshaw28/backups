/*global $:true, window:true, document:true, Class:true, searchPanel:true, revealPanel:true, responsiveImage: true, video:true, guideNavigation:true, regula:true */
(function (window) {
    "use strict";
    /**
     * Global functionality instantiation
     */
    $(document).ready(function () {
		/**
		 * Set window messaging
		 */
		window['parentDomain'] = decodeURIComponent(document.location.hash.replace(/^#/, ''));
        /**
         * Define secure modals:
         * 1. login
         * 2. forgot pw
         * 3. register
         */
        var loginForm = new secureLogin($('#secureLoginModal'));
        var forgotPasswordForm = new secureForgotPassword($('#secureForgotPasswordModal'));
        var registerForm = new secureRegister($('#secureRegisterModal'));

        // Check if we need to show modal with error code
        if (document.location.search.indexOf('errorCode') > 0) {
            // Trigger invalid authentication messaging
            loginForm.showUnauthorizedMessage();
            XDM.send({
				'openModal': '#loginModal'
			});
            $('#secureLoginModal').removeClass('hidden');
            $('.icon-spinner').addClass('hidden');
        } else {
            loginForm.resetFields();
            $('#secureLoginModal').removeClass('hidden');
            $('.icon-spinner').addClass('hidden');
        }
        if (document.location.search.indexOf('authSuccessURL') > 0) {
            XDM.send({'reload':true});
        } else {
            //display form, hide wheel
            $('#secureLoginModal').removeClass('hidden');
            $('.icon-spinner').addClass('hidden');
        }

        // Custom validation for matching email fields
        regula.custom({
            name: "EmailsMatch",
            formSpecific: true,
            defaultMessage: "Email addresses do not match!",
            params: ["field1", "field2"],
            validator: function(params) {
                var failingElements = [],
                    emailField1 = document.getElementById(params["field1"]),
                    emailField2 = document.getElementById(params["field2"]);

                if (emailField1.value != emailField2.value) {
                    failingElements = [emailField1, emailField2];
                }
                return failingElements;
            }
        });
        regula.bind();
    });
}(window));