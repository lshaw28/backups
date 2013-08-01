/*global window:true, $:true, Class:true, regula:true */
var modalForm = Class.extend(function () {
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
                if (self.group === 'loginModal') {
                    var userName = $('[name=loginId]', self.el).val();
                    var password = $('[name=logonPassword]', self.el).val();
                    var action = $('#loginFormModal').attr('action');
                    var renew = $('[name=renew]', self.el).val();
                    if (!window.secureLoginModal) window.secureLoginModal = new secureLoginModal();
                    window.secureLoginModal.submitLoginForm( userName, password, action, renew );
                } else {
                    $('form', self.el)[0].submit();
                }

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
        }
	}
}());