/*global window:true, $:true, Class:true, mainSitePath:true */
var searchPanel = Class.extend(function () {
	"use strict";

	return {
		/**
		 * @singleton searchPanel
		 * Singleton class for the searchPanel component
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			// Initialize events
			this.findItems();
			this.bindEvents();
			this.airFilterParts = this.checkIfAirFilterPart();
		},
		/**
		 * Finds dropdown items
		 * @return {void}
		 */
		findItems: function () {
			var self = this;

			// Bind an event to each drop-down selection
			$('#searchContent .dropdown-menu li a').bind('click', function (e) {
				self.selectType($(this), true);
			});
		},
		/**
		 * Handles type selection
		 * @param {object} el jQuery element
		 * @param {boolean} click Boolean denoting that a list item initiated event
		 * @return {void}
		 */
		selectType: function (el, click) {
			var self = this,
				action = mainSitePath + '/partsdirect/' + el.data('postpath') + '/',
				selectType = el.data('postpath'),
				value = self.getValue(selectType),
				label = el.data('label'),
				modelNumber = '',
				partNumber = '';

			// Close the dropdown
			if (click === true) {
				$('#searchContent .dropdown-menu').dropdown('toggle');
			}
			// Update selection status
			$('#searchContent .dropdown-menu li').removeClass('selected');
			el.parent().addClass('selected');
			// Set the drop-down label
			$('#searchTypeLabel').text(label);
			// Update hidden fields
			if (el.data('pathtaken') === 'modelSearch') {
				modelNumber = value;
			} else {
				partNumber = value;
			}
			$('#shdMod').attr('value', modelNumber);
			$('#shdPart').attr('value', partNumber);
			$('#pathTaken').attr('value', el.data('pathtaken'));
			// Update form action
			$('#searchBarForm').attr('action', action + encodeURIComponent(value));
		},
		/**
		 * Sanitises the current value
		 * @return Sanitised value
		 */
		getValue: function (selectType) {
			var self = this,
				field = $('#searchBarField'),
				selectMethod = selectType,
				value = field.attr('value'),
                searchedTerm = value;

			// Make sure the value isn't the help text
			if (value === field.data('inputhelp') || value === field.data('inputhelpmobile')) {
				value = '';
			}

			if(selectMethod === 'part-number') {
	            value = value.replace(/\$/g, '');
	            if (value.indexOf('/') != -1){
	            	value = value.replace(/\//g, '@SLASH@');
	            } else if ( value.indexOf('%') != -1 ){
	            	value = value.replace(/\%/g, '@SLASHPERCENT@');
	            }
			} else {
				value = value.replace(/\'/g, '');            
				value = value.replace(/\%/g, '');	           
				value = value.replace(/\#/g, '');	           
				value = value.replace(/\&/g, '');
				value = value.replace(/\(/g, '');
				value = value.replace(/\)/g, '');
				value = value.replace(/\-/g, '');
				value = value.replace(/\*/g, '');
				value = value.replace(/\$/g, '');
				value = value.replace(/\^/g, '');
				value = value.replace(/\,/g, '');
				value = value.replace(/\"/g, '');
		        value = value.replace(/\//g, '');
		        value = value.replace(/\?/g, '');
		        value = value.replace(/\\/g, '');

	            if (value.indexOf('/') != -1){
	            	value = value.replace(/\//g, '');
	            } else if ( value.indexOf('%') != -1 ){
	            	value = value.replace(/\%/g, '');
	            }
			}
			searchedTerm = searchedTerm.replace(/[^0-9A-Za-z]/g, '');
			// Sanitise non-alpha-numeric characters
			field.attr('value', searchedTerm);
			return value;
		},
		/**
		 * checkIfAirFilterPart
		 * @return airfilter partsearch term
		 */
		checkIfAirFilterPart : function() {

			return [ 
			         {name : 'airfilter'},
			         {name : 'airfilters'},
			         {name : 'hvacfilter'},
			         {name : 'hvacfilter'},
			         {name : 'heatingfilter'},
			         {name : 'heaterfilter'},
			         {name : 'coolingfilter'},
			         {name : 'acfilter'},
			         {name : 'airconditioner'}
			         ];
		},
		/**
		 * Perform initial event binding
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this,
				selectStatement = '#searchContent .dropdown-menu li.selected a';

			// Bind events on search field
			$('#searchBarField').bind('blur', function () {
				if ($(selectStatement).length > 0) {
					self.selectType($(selectStatement));
				}
			})
			.bind('focus', function () {
				$(this).removeClass('error');
			})
			.bind('change', function () {
				if ($(selectStatement).length > 0) {
					self.selectType($(selectStatement));
				}
			})
			.bind('keypress', function (e) {
				var key = -1;

				// Determine which key was pressed
				if (e.keyCode) {
					key = e.keyCode;
				} else if (e.which) {
					key = e.which;
				}

				// If the user hit enter, check if there's a type
				if (key === 13) {
					if ($(selectStatement).length > 0) {
						self.selectType($(selectStatement));
						return true;
					} else {
						$('#searchContent .dropdown-menu').dropdown('toggle');
						return false;
					}
				} else {
					return true;
				}
			});

			// Bind event on button
			$('#searchModelsParts').bind('click', function (e) {
				e.preventDefault();
				if (self.getValue() !== '' && $(selectStatement).length > 0) {
					var ifairfilterpart = 'false',
						searchTerm = self.getValue(),
						UpdatedSearchTerm = searchTerm.toUpperCase();

					for (i = 0; i < self.airFilterParts.length; ++i) {

						var airFilterPartsName = self.airFilterParts[i].name,
						UpdatedAirFilterPartsName = airFilterPartsName.toUpperCase();
						
						if (UpdatedSearchTerm == UpdatedAirFilterPartsName) {
							ifairfilterpart = 'true';
						}
					}
					if (ifairfilterpart == 'true') {
						e.preventDefault();
						var loc = mainSitePath + '/replacement-parts/hvac-air-filters/dimensions.html';
						window.location.href = loc;
					} else {
						$('#searchBarForm').submit();
						$('#searchBarField').removeClass('error');
					}
				} else {
					$('#searchBarField').addClass('error');
				}
			});
		}
	}
}());