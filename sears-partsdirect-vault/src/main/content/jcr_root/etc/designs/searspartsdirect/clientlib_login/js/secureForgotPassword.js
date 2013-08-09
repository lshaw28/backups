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

            $('[data-submit=true]', self.el).unbind('click').bind('click', function (e) {
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

                var userName = $('#fldEmail', self.el).val();
                //var forgotPasswordService = 'https://www.searspartsdirect.com/partsdirect/ssoForgotPasswordEmailModal.action';
                var forgotPasswordService = mainSitePathSecure + '/partsdirect/ssoForgotPasswordEmailModal.action?modal=true&forgotResponse=?';

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
            // hide the success msg, show the intro msg, form, and cancel btn
            $('.forgot-intro', self.el).removeClass('hidden');
            $('fieldset', self.el).removeClass('hidden');
            $('[data-cancel]', self.el).removeClass('hidden');
            $('.forgot-success', self.el).addClass('hidden');
        },
        /**
         * Handles a successful callback
         */
        showSuccess: function () {
            var self = this;

            $('.alert', self.el).addClass('hidden');
            // successful...change h1 text and show success paragraph
            $('h1', self.el).html('Email Sent');
            $('.forgot-success', self.el).removeClass('hidden');
            // also, hide fieldset, old paragraph and cancel button.
            $('.forgot-intro', self.el).addClass('hidden');
            $('fieldset', self.el).addClass('hidden');
            $('[data-cancel]', self.el).addClass('hidden');
            // finally, change continue button action to close modal
            $('[data-submit=true]', self.el).unbind('click').bind('click', function (e) {
                self.resetFields();
                self.postMessage({ 'closeModal': '#forgotPasswordModal' });
            });

        },
        showError: function (errorMsg) {
            var self = this;

            // Populate the alert field with the errors
            $('.alert', self.el).html(errorMsg);
            $('.alert', self.el).removeClass('hidden');
            // blank out input fields
            $('input', self.el).each(function() {
                $(this).val('');
            });

        },

        submitForgotPassword: function(userEmail, forgotPasswordURL) {
            var self = this,
            notFoundErrorMsg = "We are sorry. We cannot find the email address you entered. Please verify and enter again.",
            errorHappenedMsg = "Our records show you’re a member of our commercial parts website. We’re automatically redirecting you to Sears Commercial Parts.",
            casErrorMsg = "Cas service problem. Can't find such user",
            commericalURL = "partscommercialvip.qa.ch3.s.com";

            function forgotResponse(data, textStatus, jqXHR) {
                console.log(data);

                switch (data.Status) {

                    case "success":
                        self.showSuccess();
                        break;
                    case "notregister":
                        self.showError(notFoundErrorMsg)
                        break;
                    case "error":
                        self.showError(errorHappenedMsg);
                        setTimeout(function(){window.location.href = commericalURL+"/partsdirect/commercialLogin.pd?email=" + $('#fldEmail').val()}, 3000);
                        break;
                    case "caserror":
                        self.showError(casErrorMsg);
                        break;
                }
            };

            var ajx = $.ajax({
                type: "GET",
                async: false,
                dataType: 'jsonp',
                url: forgotPasswordURL,
                data: { email: userEmail},
                jsonpCallback: 'forgotResponse'
            });

            ajx.done(forgotResponse);

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