/*global window:true, $:true, Class:true, mainSitePath:true */
var seoHeroImage = Class.extend(function () {
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
            this.bindEvents();

		},
        getValue: function () {
			var self = this,
				field = $('#seoModelNumberSearchInput'),
                 action = mainSitePath + '/partsdirect/part-model/',
                 modelNumber='',
				value = field.attr('value');

			// Make sure the value isn't the help text
			if (value === field.data('inputhelp') || value === field.data('inputhelpmobile')) {
				value = '';
			}
            modelNumber = value;
            modelNumber = modelNumber.replace(/\ /g, '');
				modelNumber = modelNumber.replace(/\'/g, '');            
				modelNumber = modelNumber.replace(/\%/g, '');	           
				modelNumber = modelNumber.replace(/\#/g, '');	           
		        modelNumber = modelNumber.replace(/\&/g, '');
		        modelNumber = modelNumber.replace(/\(/g, '');
		        modelNumber = modelNumber.replace(/\)/g, '');
		        modelNumber = modelNumber.replace(/\-/g, '');
		        modelNumber = modelNumber.replace(/\*/g, '');
		        modelNumber = modelNumber.replace(/\$/g, '');
		        modelNumber = modelNumber.replace(/\^/g, '');
		        modelNumber = modelNumber.replace(/\,/g, '');
		        modelNumber = modelNumber.replace(/\"/g, '');
		        modelNumber = modelNumber.replace(/\//g, '');
		        modelNumber = modelNumber.replace(/\?/g, '');
		        modelNumber = modelNumber.replace(/\\/g, '');
				

	            if (modelNumber.indexOf('/') != -1){
	            	modelNumber = modelNumber.replace(/\//g, '');
	            } else if ( modelNumber.indexOf('%') != -1 ){
	            	modelNumber = modelNumber.replace(/\%/g, '');
	            }
	            value = modelNumber;
            $('#pathTaken').attr('value', 'modelSearch');
			// Update form action
			$('#seoModelSearchForm').attr('action', action + encodeURIComponent(value));
			return value;

		},

        bindEvents: function () {
            var self = this;
			// Bind event on button
			$('#seoSearchModels').bind('click', function (e) {

                if (self.getValue() !== '') {

                $('#seoModelSearchForm').submit();
                $('#seoModelSearchForm').removeClass('error');
                } else {
                    $('#seoModelSearchForm').addClass('error');
                }
			});
		}
	}
}());

function disableFieldValue(field){
	var panel = field.findParentByType('panel');
	var text = panel.find('itemId', 'text1')[0];
	var desc = panel.find('itemId', 'desc1')[0];
	var flag = field.getValue();
	if(flag=="true"){
		text.disable();
		desc.disable();
	}
	else{
		text.enable();
		desc.enable();
	}
}
