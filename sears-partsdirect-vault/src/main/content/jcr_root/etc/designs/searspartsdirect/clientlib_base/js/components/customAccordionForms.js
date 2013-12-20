var customAccordionForms = Class.extend(function () {
	"use strict";
	var xhrRespHandler = new Object();
	var monthArray = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
	
	return {
		/**
		 * Initializes customAccordionForms class
		 */
		init: function () {
			// Redirect if LDAP user isn't logged in and page is not on local
			if (window.location.host.indexOf('localhost') < 0) {
				if (NS('shc.pd').roles == undefined) {
					window.location = mainSitePathSecure + '/partsdirect/wfsLogin';
				} else {
					if (NS('shc.pd').roles.indexOf('WFS') == -1) {
						window.location = mainSitePathSecure + '/partsdirect/wfsLogin';
					}
				}
			}
			// Render
			this.bindEvents();
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this,
				filterSearchInterval = 0,
				typingFilter = false,
				searchText = '',
				unitPrice = 0.0,
				preTaxPrice = 0,
				validateDotsInterval = 0;
			
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
						$(this).find('.cafStepEdit').addClass('hidden');
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
			
			//This handles when a user enters text into the filter search field
			$('.numberField').on('keypress', function (e) {
				typingFilter = true;
				clearInterval(filterSearchInterval);
				filterSearchInterval = setInterval(function () {
					searchText = $('.numberField').val();
					if (!typingFilter && searchText != "Enter your part or model number" && searchText.length > 2) {
						var searchType = "part";
						if ($('#ntPart').attr('checked') == undefined) {
							searchType = "model";
						}
						clearInterval(filterSearchInterval);
						$.ajax({
							type: "GET",
							cache: false,
							dataType: "json",
							data: {
								number: searchText
							},
							url: apiPath + 'searchWaterFilter/' + searchType,
							success: function(response) {
								//console.log(response);
								if(response == null) {
									$('.searchText .filterError').removeClass('hidden');
									$('.searchText .filterFound').addClass('hidden');
									$('#finalPartNumber').val('');
									$('#finalGroupId').val('');
									$('#finalSupplierId').val('');
								} else {
									var groupId = response.productGroupId;
									var supplierId = response.supplierId;
									$('#finalPartNumber').val(response.partNumber);
									$('#finalGroupId').val(groupId);
									$('#finalSupplierId').val(supplierId);
									$('.searchText .filterError').addClass('hidden');
									$('.searchText .filterFound').removeClass('hidden');
									$('#cafSelectFilterFrequencyForm .cafSubmit').removeClass('hidden');
									$('.searchText .filterFound .filterDescription').attr('href', mainSitePathSecure + '/partsdirect/part-number/' + searchText + '/' + groupId + '/' + supplierId);
									$('.searchText .filterFound .filterDescription').html(response.description);
									//Multiplies the price by 100 so it can be converted into an integer
									unitPrice = parseFloat(response.sellingPrice) * 100;
								}
							},
							error: function(response) {
								//console.log('fail');
								//console.log(response);
							}
						});
					}
				}, 2000);
			});
			$('.numberField').on('keyup', function (e) {
				typingFilter = false;
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
				var submittedFormId = $(e.target.form).attr('id');
				e.preventDefault();
				//Sets the frequency, date, quantity and price based on user input in the first step
				if (submittedFormId == "cafSelectFilterFrequencyForm") {
					if ($('#finalPartNumber').val() == '') {
						$('.filterAlert').removeClass('hidden');
						return;
					} else {
						$('.filterAlert').addClass('hidden');
					}
					var selectedQty = $('#waterFilterQuantity').val();
					$('#filterLink').html($('.filterDescription').html());
					$('#freqSel').html($('.filFreq:checked').val());
					$('#startDate').html(monthArray[parseInt($('#odInput').val().substr(0,2)) - 1] + ' ' + parseInt($('#odInput').val().substr(3,5)) + ', ' + $('#odInput').val().substr(6,10));
					$('#subQty').html(selectedQty);
					//Multiplies and converts the multiplied price to an integer then converts it into a string to display the price properly
					preTaxPrice = Math.round(unitPrice) * parseInt(selectedQty);
				}
				if (submittedFormId == "cafShippingAddressForm") {
					if (!$('#cafShippingAddressForm .cafValidating').hasClass('hidden')) {
						return;
					}
					if ($('#shippingPO').attr('checked') == "checked") {
						$('#finalPO').val('true');
					} else {
						$('#finalPO').val('false');
					}
					if (!$('#countyRow').hasClass('hidden')) {
						$('#finalGeocode').val($('#shippingCounty').val());
					}
					//Special bit to fill out the billing form if user wants to use the same address (already on checkbox, here in case user updates after clicking the checkbox)
					if ($('#shippingSame').attr('checked')) {
						self.setBillingFields(true);
						$('#cafBillingAddressForm').parent().parent().parent().find('.cafStepEdit').removeClass('hidden');
					}
				}
				if (submittedFormId == "cafPaymentForm") {
					$('.payAlert').addClass('hidden');
				}
				//Set Regula to validate current form
				self.bindRegula(parseInt(e.target.attributes['data-form-number'].value));
				self.validate(e);
			});
			
			//Brings up the county dropdown for address validation
			$('#shippingAddress, #shippingCity, #shippingState, #shippingZip').on('change.addressValidation', function() {
				var address = $('#shippingAddress').val(),
					city = $('#shippingCity').val(),
					state = $('#shippingState').val(),
					zip = $('#shippingZip').val();
				if (address != "" && city != "" && $('#shippingState').attr('data-changed') == 'true' && zip != "") {
					$('#cafShippingAddressForm .cafValidating').removeClass('hidden');
					clearInterval(validateDotsInterval);
					$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html('.....');
					validateDotsInterval = setInterval(function () {
						var currentDots = $('#cafShippingAddressForm .cafValidating .cafValidatingDots').html();
						if (currentDots.length > 0) {
							$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html(currentDots.substr(0, currentDots.length - 1));
						} else {
							$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html('.....');
						}
					}, 1000);
					if ($.browser.msie) {
						xdr = new XDomainRequest();
						xdr.open('POST', apiPath + 'address/validate');
						xdr.send('{"address1":"' + address + '","city":"' + city + '","zipCode":"' + zip + '","state":"' + state + '"}');
						xdr.onload = function () {
							xhrRespHandler.getGeoCode($.parseJSON(xdr.responseText), address, city, state, zip);
						};
					} else {
						$.ajax({
							type : "POST",
							dataType: "json",
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							cache: false,
							data: JSON.stringify({
								'address1': address,
								'city': city,
								'zipCode': zip,
								'state': state
							}),
							url: apiPath + 'address/validate',
							success: function(response) {
								//console.log(response);
								xhrRespHandler.getGeoCode(response, address, city, state, zip);
							},
							error: function(response) {
								//console.log('fail');
							}
						});
					}
				}
			});
			$('#shippingCounty').on('change.addressValidation', function() {
				if (!$(this).val() == 'ZZ') {
					$('#cafShippingAddressForm .cafValidating').removeClass('hidden');
					clearInterval(validateDotsInterval);
					$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html('.....');
					validateDotsInterval = setInterval(function () {
						var currentDots = $('#cafShippingAddressForm .cafValidating .cafValidatingDots').html();
						if (currentDots.length > 0) {
							$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html(currentDots.substr(0, currentDots.length - 1));
						} else {
							$('#cafShippingAddressForm .cafValidating .cafValidatingDots').html('.....');
						}
					}, 1000);
					if ($.browser.msie) {
						var xhReqTax = new XMLHttpRequest(),
							dataReqTax = '{"address":{"address1":"' + $('#shippingAddress').val() + '","city":"' + $('#shippingCity').val() + '","geoCode":"' + $('#shippingCounty').val() + '","zipCode":"' + $('#shippingZip').val() + '","state":"' + $('#shippingState').val() + '"},"partCompositeKey":{"partNumber":"' + $('#finalPartNumber').val() + '","productGroupId":"' + $('#finalGroupId').val() + '","supplierId":"' + $('#finalSupplierId').val() + '"},"quantity":' + parseInt($('#waterFilterQuantity').val()) + '}';
						xhReqTax.open('POST', apiPath + 'address/validate/taxandshipping', false);
						xhReqTax.setRequestHeader("Accept","application/json");
						xhReqTax.setRequestHeader("Content-type","application/json");
						xhReqTax.send(dataReqTax);
						xdr.onload = function () {
							xhrRespHandler.getTax(xhrResp, $.parseJSON(xhReqTax.responseText));
						};
					} else {
						$.ajax({
							type : "POST",
							dataType: "json",
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							cache: false,
							data: JSON.stringify({
								"address": {
									'address1': $('#shippingAddress').val(),
									'city': $('#shippingCity').val(),
									'geoCode': $('#shippingCounty').val(),
									'zipCode': $('#shippingZip').val(),
									'state': $('#shippingState').val()
								},
								"partCompositeKey": {
									"partNumber": $('#finalPartNumber').val(),
									"productGroupId": $('#finalGroupId').val(),
									"supplierId": $('#finalSupplierId').val()
								},
								"quantity": parseInt($('#waterFilterQuantity').val())
							}),
							url: apiPath + 'address/validate/taxandshipping',
							success: function(response) {
								xhrRespHandler.getTax(response, response2);
							},
							error: function(response) {
								//console.log('taxandshipping ajax fail');
							}
						});
					}
				}
			});
			//Success handlers for address verification separated because there has to be a different request type in IE
			xhrRespHandler.getGeoCode = function(xhrResp, address, city, state, zip) {
				$('#shippingCounty').parent().find('.responsiveDropdown').remove();
				$('#shippingCounty').html('');
				if (xhrResp.geoCodeValues != null) {
					$('#cafShippingAddressForm .cafValidating').addClass('hidden');
					clearInterval(validateDotsInterval);
					var geoCodes = xhrResp.geoCodeValues.length;
					for (var i = 0; i < geoCodes; i++) {
						var key = xhrResp.geoCodeValues[i].key;
						var value = xhrResp.geoCodeValues[i].value;
						
						if (key == '') {
							key = 'ZZ';
						}
						$('#shippingCounty').append($('<option>', {value : key}).text(value).attr('data-value', key));
					}
					$('#shippingCounty').attr('data-changed', 'false');
					$('#shippingCounty').each(function () {
						var newResponsiveDropdown = new responsiveDropdown($(this));
					});
					$('.countyRow').removeClass('hidden');
				} else {
					$('.countyRow').addClass('hidden');
					$('#shippingCounty').append($('<option>', {value : xhrResp.validatedAddress.verifiedAddress.geoCode}).text('----'));
					if ($.browser.msie) {
						var xdr = new XDomainRequest(),
							dataReqTax = '{"address":{"address1":"' + address + '","city":"' + city + '","geoCode":"' + xhrResp.validatedAddress.verifiedAddress.geoCode + '","zipCode":"' + zip + '","state":"' + state + '"},"partCompositeKey":{"partNumber":"' + $('#finalPartNumber').val() + '","productGroupId":"' + $('#finalGroupId').val() + '","supplierId":"' + $('#finalSupplierId').val() + '"},"quantity":' + parseInt($('#waterFilterQuantity').val()) + '}';
						xdr.open('POST', apiPath + 'address/validate/taxandshipping', false);
						xdr.send(dataReqTax);
						xdr.onload = function () {
							xhrRespHandler.getTax(xhrResp, $.parseJSON(xdr.responseText));
						};
					} else {
						$.ajax({
							type : "POST",
							dataType: "json",
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							cache: false,
							data: JSON.stringify({
								"address": {
									'address1': address,
									'city': city,
									'geoCode': xhrResp.validatedAddress.verifiedAddress.geoCode,
									'zipCode': zip,
									'state': state
								},
								"partCompositeKey": {
									"partNumber": $('#finalPartNumber').val(),
									"productGroupId": $('#finalGroupId').val(),
									"supplierId": $('#finalSupplierId').val()
								},
								"quantity": parseInt($('#waterFilterQuantity').val())
							}),
							url: apiPath + 'address/validate/taxandshipping',
							success: function(response2) {
								//console.log(response2);
								xhrRespHandler.getTax(xhrResp, response2);
							},
							error: function(response2) {
								//console.log('taxandshipping ajax fail');
							}
						});
					}
				}
			};
			xhrRespHandler.getTax = function(xhrRespOne, xhrRespTwo) {
				if (xhrRespTwo.taxAmount != null) {
					$('#cafShippingAddressForm .cafValidating').addClass('hidden');
					clearInterval(validateDotsInterval);
					//Price formatting in case price is an even integer or tens of cents
					var totalPrice = preTaxPrice + Math.round(xhrRespTwo.taxAmount * 100);
					var formattedPrice = totalPrice.toString();
					$('#cafFilterPrice').html('$' + formattedPrice.substring(0, formattedPrice.length - 2) + '.' + formattedPrice.substring(formattedPrice.length - 2) + '*');
					$('#cafShippingAddressForm .cafSubmit').removeClass('hidden');
					$('#finalGeocode').val(xhrRespOne.validatedAddress.verifiedAddress.geoCode);
				} else {
					//console.log('taxandshipping fail');
				}
			};
			
			//These are for setting if the dropdowns have had an option selected by a user
			$('#shippingState, #billingState, #payMonth, #payYear').on('change.initial', function() {
				$(this).off('change.initial');
				$(this).attr('data-changed', 'true');
			});
			//Separated in case the address is changed to one that also requires a county selection
			$('#shippingCounty').on('change.initial', function() {
				$(this).attr('data-changed', 'true');
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
			
			//This is for when there is an error on the expiration date has an error (only the month dropdown gets the validation change event)
			$('#payYear').on('change.error', function() {
				$('#payMonth').change();
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
			
			//Checks the card type as the user is typing
			$('#payNumber').on('keyup', function (e) {
				var number = $(this).val().replace(/[a-zA-Z\s-]/g, "");
				var cardType = '';
				var cards = new Array();
				cards[0] = { cardType: "VISA", cardName: "Visa", prefixes: [4] };
				cards[1] = { cardType: "SEARS_MASTERCARD", cardName: "Sears Mastercard", prefixes: [512106,512107,512108] };
				cards[2] = { cardType: "MASTERCARD", cardName: "Mastercard", prefixes: [51,52,53,54,55] };
				cards[3] = { cardType: "AMERICAN_EXPRESS", cardName: "American Express", prefixes: [34,37] };
				cards[4] = { cardType: "DISCOVER", cardName: "Discover", prefixes: [6011,622,64,65] };
				cards[5] = { cardType: "SEARS_CARD", cardName: "Sears Card", prefixes: [5049] };
				cards[6] = { cardType: "SearsCard13", cardName: "Sears Card", prefixes: [95501] };
				var cardsLength = cards.length;
				
				for (var i = 0; i < cardsLength; i++) {
					var prefixLength = cards[i].prefixes.length;
					for (var h = 0; h < prefixLength; h++) {
						var exp = new RegExp('^' + cards[i].prefixes[h]);
						if (exp.test(number)) {
							cardType = cards[i].cardType;
							$('#payCardType').html(cards[i].cardName);
							$('#finalCardType').val(cardType);
							break;
						}
					}
					if (cardType != '') {
						break;
					}
				}
				if (cardType == '') {
					$('#payCardType').html('');
					$('#finalCardType').val('');
				}
			});
			
			//Where the information to start the subscription is submitted
			$('#finalSubmit').on('click', function () {
				var dateEntered = $('#odInput').val();
				$(this).unbind('click');
				$('.accordion-toggle').removeAttr('href').attr('data-toggle', 'false');
				$('#processingIcon').show();
				if ($.browser.msie) {
					var xdr = new XDomainRequest(),
						dataReqEnroll = '{"shippingInfo":{"firstName":"' + $('#shippingFirst').val() + '","lastName":"' + $('#shippingLast').val() + '","dayTimePhone":"' + $('#shippingPhone').val() + '","dayTimePhoneExt":"' + $('#shippingExt').val() + '","email":"' + $('#shippingEmail').val() + '","address":{"address1":"' + $('#shippingAddress').val() + '","address2":"' + $('#shippingApt').val() + '","city":"' + $('#shippingCity').val() + '","geoCode":"' + $('#finalGeocode').val() + '","zipCode":"' + $('#shippingZip').val() + '","state":"' + $('#shippingState').val() + '"}},"billingInfo":{"firstName":"' + $('#billingFirst').val() + '","lastName":"' + $('#billingLast').val() + '","dayTimePhone":"' + $('#shippingPhone').val() + '","dayTimePhoneExt":"' + $('#shippingExt').val() + '","address":{"address1":"' + $('#billingAddress').val() + '","address2":"' + $('#billingApt').val() + '","city":"' + $('#billingCity').val() + '","zipCode":"' + $('#billingZip').val() + '","state":"' + $('#billingState').val() + '"}},"isShippingBillingSame":' + ($('#shippingSame').attr('checked') == 'checked') + ',"partCompositeKey":{"partNumber":"' + $('#finalPartNumber').val() + '","productGroupId":"' + $('#finalGroupId').val() + '","supplierId":"' + $('#finalSupplierId').val() + '"},"creditCard":{"cardNumber":"' + $('#payNumber').val() + '","cardType":"' + $('#finalCardType').val() + '","expMonth":' + parseInt($('#payMonth').val()) + ',"expYear":' + parseInt($('#payYear').val()) + ',"securityCode":' + parseInt($('#payCode').val()) + ',"name":"' + $('#payName').val() + '"},"subscriptionInfo":{"renewalPeriod":' + parseInt($('.filFreq:checked').val()) + ',"nextFullfillmentDate":"' + dateEntered.substr(6,4) + dateEntered.substr(0,2) + dateEntered.substr(3,2) + '"},"ldapUserInfo":{"casId":"' + NS('shc.pd').casId + '", "unitCenterId":"' + NS('shc.pd').unitCenterId + '"},"quantity":' + parseInt($('#waterFilterQuantity').val()) + '}';
					xdr.open('POST', apiPathSecure + 'subscriptionservice/enroll', false);
					xdr.send(dataReqEnroll);
					xdr.onload = function () {
						xhrRespHandler.enroll($.parseJSON(xdr.responseText));
					};
				} else {
					$.ajax({
						type: "POST",
						dataType: "json",
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/json'
						},
						cache: false,
						data: JSON.stringify({
							"shippingInfo": {
								"firstName": $('#shippingFirst').val(),
								"lastName": $('#shippingLast').val(),
								"dayTimePhone": $('#shippingPhone').val(),
								"dayTimePhoneExt": $('#shippingExt').val(),
								"email": $('#shippingEmail').val(),
								"address": {
									"address1": $('#shippingAddress').val(),
									"address2": $('#shippingApt').val(),
									"city": $('#shippingCity').val(),
									"geoCode": $('#finalGeocode').val(),
									"zipCode": $('#shippingZip').val(),
									"state": $('#shippingState').val()
								}
							},
							"billingInfo": {
								"firstName": $('#billingFirst').val(),
								"lastName": $('#billingLast').val(),
								"dayTimePhone": $('#shippingPhone').val(),
								"dayTimePhoneExt": $('#shippingExt').val(),
								"address": {
									"address1": $('#billingAddress').val(),
									"address2": $('#billingApt').val(),
									"city": $('#billingCity').val(),
									"zipCode": $('#billingZip').val(),
									"state": $('#billingState').val()
								}
							},
							"isShippingBillingSame": $('#shippingSame').attr('checked') == 'checked',
							"partCompositeKey": {
								"partNumber": $('#finalPartNumber').val(),
								"productGroupId": $('#finalGroupId').val(),
								"supplierId": $('#finalSupplierId').val()
							},
							"creditCard": {
								"cardNumber": $('#payNumber').val(),
								"cardType": $('#finalCardType').val(),
								"expMonth": parseInt($('#payMonth').val()),
								"expYear": parseInt($('#payYear').val()),
								"securityCode": parseInt($('#payCode').val()),
								"name": $('#payName').val()
							},
							"subscriptionInfo": {
								"renewalPeriod": parseInt($('.filFreq:checked').val()),
								"nextFullfillmentDate": dateEntered.substr(6,4) + dateEntered.substr(0,2) + dateEntered.substr(3,2)
							},
							"ldapUserInfo": {
								"casId": NS('shc.pd').casId,
								"unitCenterId": NS('shc.pd').unitCenterId
							},
							"quantity": parseInt($('#waterFilterQuantity').val())
						}),
						url: apiPathSecure + 'subscriptionservice/enroll',
						success: function(response) {
							//console.log(response);
							xhrRespHandler.enroll(response);
						},
						error: function(response) {
							//console.log('fail');
							//console.log(response);
						}
					});
				}
			});
			
			//Success handler for enrolment separated because there has to be a different request type in IE
			xhrRespHandler.enroll = function(xhrResp) {
				if (xhrResp.message == 'SUCCESS') {
					$('.visible-desktop').html('enrolling');
					$.ajax({
						type:"GET",
						url:'SubscriptionConfirmation.html',
						success: function(pageResponse) {
							$('.pageTitleHeader, .customAccordionForms').addClass('hidden');
							$('.customAccordionForms').after($(pageResponse).find('.subscriptionConfirmation'));
							$('html, body').animate({
								'scrollTop': $('a[name=backToTop]').offset().top
							}, 1000);
							$('#confirmNo').html(xhrResp.membershipId);
							$('#confirmNew').attr('href', window.location);
							$('#confirmShipFirst').html(xhrResp.shippingInfo.firstName);
							$('#confirmShipLast').html(xhrResp.shippingInfo.lastName);
							$('#confirmShipAddress').html(xhrResp.shippingInfo.address.address1);
							if (xhrResp.shippingInfo.address.address2 != '') {
								$('#confirmShipAddress + br').after(xhrResp.shippingInfo.address.address2 + '<br />');
							}
							$('#confirmShipCity').html(xhrResp.shippingInfo.address.city);
							$('#confirmShipState').html(xhrResp.shippingInfo.address.state);
							$('#confirmShipZip').html(xhrResp.shippingInfo.address.zipCode);
							$('#confirmBillFirst').html(xhrResp.billingInfo.firstName);
							$('#confirmBillLast').html(xhrResp.billingInfo.lastName);
							$('#confirmBillAddress').html(xhrResp.billingInfo.address.address1);
							if (xhrResp.billingInfo.address.address2 != '') {
								$('#confirmBillAddress + br').after(xhrResp.billingInfo.address.address2 + '<br />');
							}
							$('#confirmBillCity').html(xhrResp.billingInfo.address.city);
							$('#confirmBillState').html(xhrResp.billingInfo.address.state);
							$('#confirmBillZip').html(xhrResp.billingInfo.address.zipCode);
							$('#confirmBillCardType').html(xhrResp.paymentInfo.cardType);
							$('#confirmBillCardNo').html(xhrResp.paymentInfo.cardNumber);
							$('#confirmDate').html($('#startDate').html());
							$('#confirmEmail').html($('#shippingEmail').val());
							$('#confirmFreq').html($('.filFreq:checked').val());
							$('#confirmPartNo').html($('#finalPartNumber').val());
							$('#confirmPartDesc').html($('.filterFound a').html());
							$('#confirmQty').html($('#waterFilterQuantity').val());
							//Price formatting in case price is an even integer or tens of cents
							var formattedUnitPrice = Math.round(xhrResp.price * 100).toString(),
								formattedTax = (Math.round(xhrResp.paymentInfo.amount * 100) - Math.round(xhrResp.price * 100) * parseInt($('#waterFilterQuantity').val())).toString(),
								formattedFinalPrice = Math.round(xhrResp.paymentInfo.amount * 100).toString();
							$('#confirmUnitPrice').html(formattedUnitPrice.substring(0, formattedUnitPrice.length - 2) + '.' + formattedUnitPrice.substring(formattedUnitPrice.length - 2));
							$('#confirmTax').html(formattedTax.substring(0, formattedTax.length - 2) + '.' + formattedTax.substring(formattedTax.length - 2));
							$('#confirmTotalPrice').html(formattedFinalPrice.substring(0, formattedFinalPrice.length - 2) + '.' + formattedFinalPrice.substring(formattedFinalPrice.length - 2));
						},
						error: function(pageResponse) {
							//console.log('fail');
						}
					});
				} else {
					
				}
			};
			
			//Custom Regula constraint for dropdowns
			regula.custom({
				name: "DropDown",
				defaultMessage: "Select something",
				validator: function() {
					var selected = $(this).attr('data-changed') == 'true';
					return selected;
				}
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
					return (match.test(this.value) && this.value.length > 11 && this.value.length < 17);
				}
			});
			//Custom Regula constraint for when two drop downs must be selected as a pair
			regula.custom({
				name: "SelectGroup",
				defaultMessage: "Select fields",
				params: ["inputId"],
				validator: function(params) {
					var returnVal = false;
					if ($('#' + params["inputId"]).attr('data-changed') == 'true' && $(this).attr('data-changed') == 'true') {
						returnVal = true;
					}
					return (returnVal);
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
				$('#billingState').parent().find('.responsiveDropdown li[data-value=' + $('#shippingState option:selected').val() + '] a').click();
				$('.billingStateDrop .responsiveDropdown ul').addClass('dropdownDisabled');
				$('#billingZip').val($('#shippingZip').val()).attr('disabled', 'disabled');
				$('#billingPhone').val($('#shippingPhone').val()).attr('disabled', 'disabled');
				$('#billingExt').val($('#shippingExt').val()).attr('disabled', 'disabled');
				$('#finalBillingFName').val($('#shippingFirst').val());
				$('#finalBillingLName').val($('#shippingLast').val());
				$('#finalBillingAddress').val($('#shippingAddress').val());
				$('#finalBillingApt').val($('#shippingApt').val());
				$('#finalBillingCity').val($('#shippingCity').val());
				$('#finalBillingState').val($('#shippingState').val());
				$('#finalBillingZip').val($('#shippingZip').val());
				$('#finalBillingPhone').val($('#shippingPhone').val());
				$('#finalBillingExt').val($('#shippingExt').val());
			} else {
				$('#billingFirst').val('').removeAttr('disabled');
				$('#billingLast').val('').removeAttr('disabled');
				$('#billingAddress').val('').removeAttr('disabled');
				$('#billingApt').val('').removeAttr('disabled');
				$('#billingCity').val('').removeAttr('disabled');
				$('.billingStateDrop .responsiveDropdown ul').removeClass('dropdownDisabled');
				$('#billingState').parent().find('.responsiveDropdown li[data-value=ZZ] a').click();
				$('#billingZip').val('').removeAttr('disabled');
				$('#billingPhone').val('').removeAttr('disabled');
				$('#billingExt').val('').removeAttr('disabled');
				$('#finalBillingFName').val('');
				$('#finalBillingLName').val('');
				$('#finalBillingAddress').val('');
				$('#finalBillingApt').val('');
				$('#finalBillingCity').val('');
				$('#finalBillingState').val('');
				$('#finalBillingZip').val('');
				$('#finalBillingPhone').val('');
				$('#finalBillingExt').val('');
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
							{constraintType: regula.Constraint.DropDown,
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
					if (!$('.countyRow').hasClass('hidden')) {
						regula.bind(
							{element: document.getElementById("shippingCounty"),
							constraints: [
								{constraintType: regula.Constraint.DropDown,
									params: {message: "Please select your county."}
								}
							]}
						);
					}
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
								params: {regex: /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/, message: "Please enter your email address."}
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
							{constraintType: regula.Constraint.DropDown,
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
					nextToggle = $('.accordion-toggle[data-status=unavailable]:eq(0)'),
					formNumber = button.target.attributes['data-form-number'].value;
				
				//Performing credit card validation before moving to last step
				if (parseInt(formNumber) == 4) {
					var number = $('#payNumber').val().replace(/[a-zA-Z\s-]/g, "");
					var month = $('#payMonth').val();
					var year = $('#payYear').val();
					var code = $('#payCode').val().replace(/[a-zA-Z\s-]/g, "");
					var name = $('#payName').val().replace(/[\s]/g, "");
					var cardType = $('#finalCardType').val();
					
					if (cardType != '') {
						if ($.browser.msie) {
							var xdr = new XDomainRequest();
							xdr.open('POST', apiPathSecure + 'validate/paymentcard', false);
							xdr.send('{"cardNumber":"' + number + '","cardType":"' + cardType + '","expMonth":"' + month + '","expYear":"' + year + '","securityCode":"' + code + '","name":"' + name + '"}');
							xdr.onload = function () {
								xhrRespHandler.showPayValResult($.parseJSON(xdr.responseText), cardType, thisToggle, completedToggles, nextToggle);
							};
						} else {
							$.ajax({
								type: "POST",
								dataType: "json",
								headers: {
									'Accept': 'application/json',
									'Content-Type': 'application/json'
								},
								cache: false,
								data: JSON.stringify({
									'cardNumber': number,
									'cardType': cardType,
									'expMonth': month,
									'expYear': year,
									'securityCode': code,
									'name': name
								}),
								url: apiPathSecure + 'validate/paymentcard',
								success: function(response) {
									//console.log(response);
									xhrRespHandler.showPayValResult(response, cardType, thisToggle, completedToggles, nextToggle);
								},
								error: function(response) {
									//console.log('fail');
									//console.log(response);
									xhrRespHandler.showPayValError();
								}
							});
						}
					} else {
						$('#finalCardType').val('');
						$('.payAlert').html('Not a valid credit card type.');
						$('.payAlert').removeClass('hidden');
					}
				} else {
					self.openStep(thisToggle, completedToggles, nextToggle);
				}
			}
			
			//Success and error handlers for payment verification separated because there has to be a different request type in IE
			xhrRespHandler.showPayValResult = function(xhrResp, type, thisToggle, completedToggles, nextToggle) {
				if (xhrResp.status == 5) {
					$('.payAlert').addClass('hidden');
					$('#finalCardType').val(type);
					self.openStep(thisToggle, completedToggles, nextToggle);
				} else {
					$('.payAlert').html(xhrResp.message);
					$('.payAlert').removeClass('hidden');
					$('#finalCardType').val('');
				}
			};
			xhrRespHandler.showPayValError = function() {
				$('.payAlert').html('Error with request.');
				$('.payAlert').removeClass('hidden');
			}
		},
		/**
		 * Opens up the next step
		 * @return {void}
		 */
		openStep: function (toggle, cTog, nTog) {
			for (var i = 0; i < cTog; i++) {
				var currentToggle = $('.accordion-toggle[data-status=complete]:eq(' + i + ')');
				currentToggle.attr('href', currentToggle.attr('data-href')).attr('data-toggle', 'collapse');
			}
			//Separated so that opening a completed step can deactivate the other toggles
			if ($('.accordion-toggle[data-status=unavailable]:eq(0)').length > 0) {
				toggle.attr('href', toggle.attr('data-href')).attr('data-toggle', 'collapse');
				toggle.find('.cafStepEdit').removeClass('hidden');
				toggle.click();
				toggle.attr('data-status', 'complete');
				nTog.attr('data-status', 'incomplete');
				nTog.attr('href', nTog.attr('data-href')).attr('data-toggle', 'collapse');
				nTog.click();
				nTog.removeAttr('href').attr('data-toggle', 'false');
			}
		}
	};
}());