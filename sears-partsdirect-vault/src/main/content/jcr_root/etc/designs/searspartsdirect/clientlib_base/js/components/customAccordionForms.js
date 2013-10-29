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
			
			$('body').append($('#modalShipping')).append($('#modalCode'));
			//This modifies the accordion to change the head background color when toggled and to have one open at a time (preference to current step)
			$('.customAccordionForms .accordion-toggle').on('click', function () {
				//Checks if accordion is deactivated
				if ($(this).attr('data-toggle') == "collapse") {
					if ($(this).parent().hasClass('cafHeadingOpen')) {
						//Closing accordion
						$(this).parent().removeClass('cafHeadingOpen');
					} else {
						//Opening accordion
						$(this).parent().addClass('cafHeadingOpen');
						$(this).find('span').addClass('hidden');
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
			
			//This sets up the DatePickers
			var today = new Date();
			$('#orderDate').datepicker({
				startDate: (today.getMonth() + 1).toString() + "/" + today.getDate().toString() + "/" + today.getFullYear().toString(),
				endDate: (today.getMonth() + 1).toString() + "/" + today.getDate().toString() + "/" + (today.getFullYear() + 1).toString(),
				orientation: "top auto",
				autoclose: true
			});
			//Ties the frequency radio buttons and dropdown together and sets the order date
			$('.freqDropCont .responsiveDropdown li').live('click', 'a', function () {
				var beginDate = new Date();
				beginDate.setMonth(beginDate.getMonth() + parseInt($(this).attr('data-value')));
				$('#orderDate').datepicker('update', (beginDate.getMonth() + 1).toString() + '/' + beginDate.getDate().toString() + '/' + beginDate.getFullYear().toString());
				$('.filFreq').removeAttr('checked');
				$('.filFreq[value=' + $(this).attr('data-value') + ']').attr('checked', 'checked');
			});
			
			$('.filFreq').on('click', function () {
				$('.freqDropCont .responsiveDropdown li a[data-value=' + $(this).val() + ']').click();
			});
			today.setMonth(today.getMonth() + 6);
			$('#orderDate').datepicker('update', (today.getMonth() + 1).toString() + '/' + today.getDate().toString() + '/' + today.getFullYear().toString());
			
			$('.cafSubmit', self.el).on('click', function (e) {
				e.preventDefault();
				//Sets the frequency, dates (eventually) and quantity the user input on the first step
				if ($(e.target.form).attr('id') == "cafSelectFilterFrequencyForm") {
					$('#freqSel').html($('.filFreq:checked').val());
					$('#startDate').html($('#odInput').val());
					$('#subQty').html($('#waterFilterQuantity').val());
				}
				//Special bit to fill out the billing form if user wants to use the same address (already on checkbox, here in case user updates after clicking the checkbox)
				if ($(e.target.form).attr('id') == "cafShippingAddressForm" && $('#shippingSame').attr('checked')) {
					self.setBillingFields(true);
				}
				//Set Regula to validate current form
				self.bindRegula(parseInt(e.target.attributes['data-form-number'].value));
				self.validate(e);
			});
			
			//These handle autofills for the dropdowns (i.e.: if browser fills in previously used address)
			$('#shippingState').on('change.autofill', function() {
				$('#shippingState').off('change.autofill');
				$('#shippingState').parent().find('.responsiveDropdown li[data-value=' + $('#shippingState option:selected').val() + '] a').click();
			});
			$('#billingState').on('change.autofill', function() {
				$('#billingState').off('change.autofill');
				$('#billingState').parent().find('.responsiveDropdown li[data-value=' + $('#billingState option:selected').val() + '] a').click();
			});
			$('#payMonth').on('change.autofill', function() {
				$('#payMonth').off('change.autofill');
				$('#payMonth').parent().find('.responsiveDropdown li[data-value=' + $('#payMonth option:selected').val() + '] a').click();
			});
			$('#payYear').on('change.autofill', function() {
				$('#payYear').off('change.autofill');
				$('#payYear').parent().find('.responsiveDropdown li[data-value=' + $('#payYear option:selected').val() + '] a').click();
			});
			
			//This is for when there is an error on the expiration date has an error (only the month get the validation change event)
			$('#payYear').on('change.error', function() {
				$('#payMonth').change();
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
					return (match.test(this.value) && this.value.length > 12 && this.value.length < 17);
				}
			});
			//Custom Regula constraint for when two drop downs must be selected as a pair
			regula.custom({
				name: "SelectGroup",
				defaultMessage: "Select fields",
				params: ["inputId"],
				validator: function(params) {
					var returnVal = false;
					if ($('#' + params["inputId"])[0].selectedIndex && $(this)[0].selectedIndex) {
						returnVal = true;
					}
					return (returnVal);
				}
			});
			
			//This will have the shipping address submit button submit the billing address form with shipping address data
			$('.customAccordionForms #shippingSame').on('click', function () {
				if ($(this).attr('checked')) {
					self.setBillingFields(true);
					$('#billingSame').attr('checked', 'checked');
					$('.billingToggle').attr('data-status', 'complete');
					$('.billingToggle span').removeClass('hidden');
				} else {
					self.setBillingFields(false);
					$('#billingSame').removeAttr('checked');
					$('.billingToggle').attr('data-status', 'unavailable');
					$('.billingToggle span').addClass('hidden');
				}
			});
			
			//This will have the shipping address submit button fill out the billing address form with shipping address data
			$('.customAccordionForms #billingSame').on('click', function () {
				if ($(this).attr('checked')) {
					self.setBillingFields(true);
					$('#shippingSame').attr('checked', 'checked');
					// Clear out old error messages
					$('.accordion-inner .error').remove();
					// Remove validation on individual fields and error border color
					$('.errorField').off('change.error').removeClass('errorField');
					$('.dropError').removeClass('dropError');
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
				$('#billingFirst').val($('#shippingFirst').val()).attr('disabled', 'disabled');
				$('#billingLast').val($('#shippingLast').val()).attr('disabled', 'disabled');
				$('#billingAddress').val($('#shippingAddress').val()).attr('disabled', 'disabled');
				$('#billingApt').val($('#shippingApt').val()).attr('disabled', 'disabled');
				$('#billingCity').val($('#shippingCity').val()).attr('disabled', 'disabled');
				$('#billingZip').val($('#shippingZip').val()).attr('disabled', 'disabled');
				$('#billingPhone').val($('#shippingPhone').val()).attr('disabled', 'disabled');
				$('#billingExt').val($('#shippingExt').val()).attr('disabled', 'disabled');
			} else {
				$('#billingFirst').val('').removeAttr('disabled');
				$('#billingLast').val('').removeAttr('disabled');
				$('#billingAddress').val('').removeAttr('disabled');
				$('#billingApt').val('').removeAttr('disabled');
				$('#billingCity').val('').removeAttr('disabled');
				$('#billingZip').val('').removeAttr('disabled');
				$('#billingPhone').val('').removeAttr('disabled');
				$('#billingExt').val('').removeAttr('disabled');
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
					regula.bind(
						{element: document.getElementById("odInput"),
						constraints: [
							{constraintType: regula.Constraint.Pattern,
								params: {regex: /[0-9]{2}\/[0-9]{2}\/[0-9]{4}/,message: "Please enter a date"}
							}
						]}
					);
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
						{element: document.getElementById("shippingState"),
						constraints: [
							{constraintType: regula.Constraint.Selected,
								params: {message: "Please select your state."}
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
						{element: document.getElementById("billingState"),
						constraints: [
							{constraintType: regula.Constraint.Selected,
								params: {message: "Please select your state."}
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
						{element: document.getElementById("payMonth"),
						constraints: [
							{constraintType: regula.Constraint.SelectGroup,
								params: {inputId: "payYear", message: "Please select a month and a year."}
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
				regulaResponse = regula.validate();
			
			// Clear out old error messages
			$('.accordion-inner .error').remove();
			// Remove validation on individual fields and error border color
			$('.errorField').off('change.error').removeClass('errorField');
			$('.dropError').removeClass('dropError');
			
			// Place the error messages under each field
			for (i = 0; i < regulaResponse.length; i = i + 1) {
				var currentInvalid = $(regulaResponse[i].failingElements[0]);
				currentInvalid.after('<span class="error">*' + regulaResponse[i].message + '</span>');
				currentInvalid.addClass('errorField');
				if (currentInvalid.siblings('.responsiveDropdown').length > 0) {
					currentInvalid.siblings('.responsiveDropdown').find('.new-btn-dropdown').addClass('dropError');
					//Custom error handling for SelectGroup
					if (regulaResponse[i].constraintName == "SelectGroup") {
						var otherSelect = $('#' + regulaResponse[i].constraintParameters.inputId);
						currentInvalid.attr('data-match', regulaResponse[i].constraintParameters.inputId);
						if (currentInvalid.find('option:selected').index() > 0) {
							currentInvalid.siblings('.responsiveDropdown').find('.new-btn-dropdown').removeClass('dropError');
						}
						if (otherSelect.find('option:selected').index() < 1) {
							otherSelect.siblings('.responsiveDropdown').find('.new-btn-dropdown').addClass('dropError');
						}
					}
				}
				currentInvalid.on('change.error', function (e) {
					var z = 0,
						remainingErrors = regula.validate(),
						stillError = false;
					
					// Search for field among list of invalid fields
					for (z = 0; z < remainingErrors.length; z = z + 1) {
						// Check if field is still in error
						if ($(remainingErrors[z].failingElements[0]).attr('id') == $(this).attr('id')) {
							stillError = true;
						}
					}
					if (!stillError) {
						// Clear out error message
						$(this).siblings('.error').remove();
						// Remove validation on individual field
						$(this).off('change.error').removeClass('errorField');
						if ($(this).siblings('.responsiveDropdown').length > 0) {
							$(this).siblings('.responsiveDropdown').find('.new-btn-dropdown').removeClass('dropError');
							if ($(this).attr('data-match') != undefined) {
								$('#' + $(this).attr('data-match')).siblings('.responsiveDropdown').find('.new-btn-dropdown').removeClass('dropError');
							}
						}
					}
				});
			}
			
			// Submit the form if there are no errors
			if (regulaResponse.length == 0) {
				var thisToggle = $('#' + button.target.attributes['data-this-toggle-id'].value),
					completedToggles = $('.accordion-toggle[data-status=complete]').length,
					nextToggle = $('.accordion-toggle[data-status=unavailable]:eq(0)');
				
				for (var i = 0; i < completedToggles; i++) {
					var currentToggle = $('.accordion-toggle[data-status=complete]:eq(' + i + ')');
					currentToggle.attr('href', currentToggle.attr('data-href')).attr('data-toggle', 'collapse');
				}
				//Separated so that opening a completed step can deactivate the other toggles
				if ($('.accordion-toggle[data-status=unavailable]:eq(0)').length > 0) {
					thisToggle.attr('href', thisToggle.attr('data-href')).attr('data-toggle', 'collapse');
					thisToggle.find('span').removeClass('hidden');
					thisToggle.click();
					thisToggle.attr('data-status', 'complete');
					nextToggle.attr('data-status', 'incomplete');
					nextToggle.attr('href', nextToggle.attr('data-href')).attr('data-toggle', 'collapse');
					nextToggle.click();
					nextToggle.removeAttr('href').attr('data-toggle', 'false');
				}
			}
		}
	};
}());