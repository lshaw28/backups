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
			
			//This modifies the accordion to change the head background color when toggled and to have one open at a time (preference to current step)
			$('.customAccordionForms .accordion-toggle').bind('click', function () {
				//Checks if accordion is deactivated
				if ($(this).attr('data-toggle') == "collapse") {
					if ($(this).parent().hasClass('cafHeadingOpen')) {
						//Closing accordion
						$(this).parent().removeClass('cafHeadingOpen');
					} else {
						//Opening accordion
						$(this).parent().addClass('cafHeadingOpen');
					}
				}
			});
			//This handles when a user opens a finished step (it closes the current one and deactivates all the toggles)
			$('.customAccordionForms .accordion').on('show', function () {
				var thisToggle = $(this).find('.accordion-toggle'),
                    openToggle = $('.accordion-toggle[data-status=incomplete]');
                if (thisToggle.attr('data-status') == "complete") {
					openToggle.attr('href', openToggle.attr('data-href')).attr('data-toggle', 'collapse');
                    openToggle.click();
                    openToggle.attr('data-status', 'unavailable');
					$('.accordion-toggle').removeAttr('href').attr('data-toggle', 'false');
                }
			});
			$('.customAccordionForms .accordion').on('hide', function () {
				//console.log('close');
			});
			
			$('.cafSubmit', self.el).bind('click', function (e) {
				e.preventDefault();
				//Special bit to fill out the billing form if user wants to use the same address (already on checkbox, here in case user updates after clicking the checkbox)
				if ($(e.target.form).attr('id') == "cafShippingAddressForm" && $('#shippingSame').attr('checked')) {
					self.setBillingFields(true);
				}
				//Set Regula to validate current form
				self.bindRegula(parseInt(e.target.attributes['data-form-number'].value));
				self.validate(e);
			});
			
			//Custom Regula constraint for matching fields
			regula.custom({
				name: "MatchInput",
				defaultMessage: "Fields must match",
				params: ["inputId"],
				validator: function(params) {
					var match = $('#' + params["inputId"]).val();
					return (this.value == match);
				}
			});
			//Custom Regula constraint for validating credit cards
			regula.custom({
				name: "CreditCard",
				defaultMessage: "Invalid number",
				validator: function() {
					var match = /^\d+$/;
					console.log(this.value.length);
					return (match.test(this.value) && this.value.length > 12 && this.value.length < 17);
				}
			});
			
			//This will have the shipping address submit button submit the billing address form with shipping address data
			$('.customAccordionForms #shippingSame').bind('click', function () {
				if ($(this).attr('checked')) {
					self.setBillingFields(true);
					$('#billingSame').attr('checked', 'checked');
					$('.accordion-group').has('#cafBillingAddressForm').find('.accordion-toggle').attr('data-status', 'complete');
				} else {
					self.setBillingFields(false);
					$('#billingSame').removeAttr('checked');
					$('.accordion-group').has('#cafBillingAddressForm').find('.accordion-toggle').attr('data-status', 'unavailable');
				}
			});
			
			//This will have the shipping address submit button fill out the billing address form with shipping address data
			$('.customAccordionForms #billingSame').bind('click', function () {
				if ($(this).attr('checked')) {
					self.setBillingFields(true);
					$('#shippingSame').attr('checked', 'checked');
				} else {
					self.setBillingFields(false);
					$('#shippingSame').removeAttr('checked');
				}
			});
		},
		/**
		 * Sets billing form input fields
		 * @return {void}
		 */
		setBillingFields: function (fill) {
			if (fill) {
				$('#billingFirst').val($('#shippingFirst').val());
				$('#billingLast').val($('#shippingLast').val());
				$('#billingAddress').val($('#shippingAddress').val());
				$('#billingApt').val($('#shippingApt').val());
				$('#billingCity').val($('#shippingCity').val());
				$('#billingZip').val($('#shippingZip').val());
				$('#billingPhone').val($('#shippingPhone').val());
				$('#billingExt').val($('#shippingExt').val());
			} else {
				$('#billingFirst').val('');
				$('#billingLast').val('');
				$('#billingAddress').val('');
				$('#billingApt').val('');
				$('#billingCity').val('');
				$('#billingZip').val('');
				$('#billingPhone').val('');
				$('#billingExt').val('');
			}
		},
		/**
		 * Binds Regula to the current form elements
		 * @return {void}
		 */
		bindRegula: function (formNumber) {
			regula.unbind();
			switch(formNumber) {
				case 1:
					regula.bind(
						{element: document.getElementById("ntModel"),
						constraints: [
							{constraintType: regula.Constraint.Checked,
								params: {message: "Please select a search type"}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("frequency12"),
						constraints: [
							{constraintType: regula.Constraint.Checked,
								params: {message: "Please select a frequency"}
							}
						]}
					);
					/*regula.bind(
						{element: document.getElementById("startMonth"),
						constraints: [
							{constraintType: regula.Constraint.Selected,
								params: {message: "Please select a month"}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("startDay"),
						constraints: [
							{constraintType: regula.Constraint.Selected,
								params: {message: "Please select a day"}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("startYear"),
						constraints: [
							{constraintType: regula.Constraint.Selected,
								params: {message: "Please select a year"}
							}
						]}
					);*/
					regula.bind(
						{element: document.getElementById("waterFilterQuantity"),
						constraints: [
							{constraintType: regula.Constraint.Min,
								params: {value: 1, message: "Please enter a quantity"}
							}
						]}
					);
					break;
				case 2:
					regula.bind(
						{element: document.getElementById("shippingFirst"),
						constraints: [
							{constraintType: regula.Constraint.Alpha,
								params: {message: "Please enter your first name."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingLast"),
						constraints: [
							{constraintType: regula.Constraint.Alpha,
								params: {message: "Please enter your last name."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingAddress"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your address."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingCity"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your city."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingZip"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your 5-digit ZIP code."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingPhone"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter a valid 10-digit phone number."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingEmail"),
						constraints: [
							{constraintType: regula.Constraint.Pattern,
								params: {regex: /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/, message: "Please enter your email address."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("shippingConfirm"),
						constraints: [
							{constraintType: regula.Constraint.MatchInput,
								params: {inputId: "shippingEmail", message: "Please confirm your email address."}
							}
						]}
					);
					break;
				case 3:
					regula.bind(
						{element: document.getElementById("billingFirst"),
						constraints: [
							{constraintType: regula.Constraint.Alpha,
								params: {message: "Please enter your first name."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("billingLast"),
						constraints: [
							{constraintType: regula.Constraint.Alpha,
								params: {message: "Please enter your last name."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("billingAddress"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your address."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("billingCity"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your city."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("billingZip"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your 5-digit ZIP code."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("billingPhone"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter a valid 10-digit phone number."}
							}
						]}
					);
					break;
				case 4:
					regula.bind(
						{element: document.getElementById("payNumber"),
						constraints: [
							{constraintType: regula.Constraint.CreditCard,
								params: {message: "Please enter your 16-digit credit card number."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("payCode"),
						constraints: [
							{constraintType: regula.Constraint.NotBlank,
								params: {message: "Please enter your security code."}
							}
						]}
					);
					regula.bind(
						{element: document.getElementById("payName"),
						constraints: [
							{constraintType: regula.Constraint.Pattern,
								params: {regex: /^[a-zA-Z\s]+$/, message: "Please enter the name on the card."}
							}
						]}
					);
					break;
				default:
					//Error
			}
		},
		/**
		 * Validates the forms and displays friendly errors
		 * @return {void}
		 */
		validate: function (button) {
			var self = this,
				i = 0,
				errorMessage = '',
				divider = '',
				regulaResponse = regula.validate(),
				alert = $('#' + button.target.attributes['data-alert-id'].value);
			
			// Parse the error messages
			for (i = 0; i < regulaResponse.length; i = i + 1) {
				errorMessage += divider + regulaResponse[i].message;
				divider = '<br />';
			}

			// Populate the errors
			alert.html(errorMessage);
			
			// Display errors or submit the form
			if (errorMessage.length > 0) {
				alert.removeClass('hidden');
			} else {
				var thisToggle = $('#' + button.target.attributes['data-this-toggle-id'].value),
					completedToggles = $('.accordion-toggle[data-status=complete]').length,
					nextToggle = $('.accordion-toggle[data-status=unavailable]:eq(0)');
				
				alert.addClass('hidden');
				
				for (var i = 0; i < completedToggles; i++) {
					var currentToggle = $('.accordion-toggle[data-status=complete]:eq(' + i + ')');
					currentToggle.attr('href', currentToggle.attr('data-href')).attr('data-toggle', 'collapse');
				}
				//Separated so that opening a completed step can deactivate the other toggles
				thisToggle.attr('href', thisToggle.attr('data-href')).attr('data-toggle', 'collapse');
				thisToggle.click();
				thisToggle.attr('data-status', 'complete');
				nextToggle.attr('data-status', 'incomplete');
				nextToggle.attr('href', nextToggle.attr('data-href')).attr('data-toggle', 'collapse');
				nextToggle.click();
				nextToggle.removeAttr('href').attr('data-toggle', 'false');
			}
		}
	};
}());