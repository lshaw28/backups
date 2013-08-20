var customAccordionForms = Class.extend(function () {
	"use strict";
	
	return {
		/**
		 * Initializes customAccordionForms class
		 */
		init: function () {
			// Render
			this.bindEvents();
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;
			
			$('.cafSubmit', self.el).bind('click', function (e) {
				e.preventDefault();
				self.validate();
			});
			$('.customAccordionForms .accordion-toggle').bind('click', function () {
				if ($(this).parent().hasClass('cafHeadingOpen')) {
					$(this).parent().removeClass('cafHeadingOpen');
				} else {
					$(this).parent().addClass('cafHeadingOpen');
				}
			});
		},
		/**
		 * Validates the forms and displays friendly errors
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
				$('.alert', self.el).addClass('hidden');
				$('#cafSelectFilterFrequencyForm p a').html($('#cafSelectFilterFrequencyForm p a').html() + '&!');
			}
		}
	};
}());