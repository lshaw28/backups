/*global window:true, $:true, Class:true, regula:true */
var secureLogin = Class.extend(function () {
    "use strict";

    return {
        /**
         * Initializes modalForm class
         * Uses Regula validation
         * See documentation: https://github.com/vivin/regula/wiki
         */
        init: function (el) {
            this.el = el;
            this.group = $('form', el).attr('data-regulagroup').toString();
            this.bindSubmit();
            this.bindCancel();
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
                regulaResponse = regula.validate(self.createValidationObject());

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
                var password = $('[name=logonPassword]', self.el).val();
                var action = $('#loginFormModal').attr('action');
                var renew = $('[name=renew]', self.el).val();

                self.submitLoginForm( userName, password, action, renew );


            }
        },

        bindCancel: function () {
            var self = this;

            $('[data-cancel]', self.el).bind('click', function () {
                self.resetFields();
            });

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
            $('input', self.el).each(function() {
                $(this).val('');
            });
        },


        successCallback: function (isUserConsumer) {
            var self = this,
                commercial_Url = 'https://commercial.searspartsdirect.com';

            // NOT authenticated at this point...
            if (!isUserConsumer) {
                // redirect to commercial PD site
                $('[name=loginId]', self.el).attr('name', 'j_username');
                $('[name=logonPassword]', self.el).attr('name', 'j_password');
                //logonPassword.name = "j_password";
                //loginId.name = "j_username";
                document.secureLoginFormModal.action = commercial_Url+"/partsdirect/commercialLogin.pd?email=" + $('[name=loginId]', self.el).val();
                $('.alert', self.el).html("Our records show you're a member of our commercial parts website. We're automatically redirecting you to Sears Commercial Parts.");
                $('.alert', self.el).removeClass('hidden');
                setTimeout(function(){document.loginFormModal.submit();}, 3000);
            } else {
                // They're a normal user
                // submit the form and reload page
                $('form', self.el)[0].submit();
            }
        },

        unAuthCallback: function (errors) {
               // this ajax call should never fail
        },

        submitLoginForm: function(username, password, serviceURL, renew) {
            var self = this;

            $.ajax({
                type: "POST",
                url: actionURL,
                data: {loginID: username, logonPassword: password, service:serviceURL, renew: renew },
                success: self.successCallback,
                error: self.unAuthCallback
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
        }

    };

}());