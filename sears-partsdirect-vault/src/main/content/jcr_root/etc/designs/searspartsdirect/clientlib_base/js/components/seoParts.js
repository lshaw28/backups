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
			placeholderFallback();
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
            
            $('#seoModelNumberSearchInput').bind('focus', function () {
				$(this).removeClass('error');
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
					$('#seoSearchModels').click();
				} 
                if (key === 9) {
					 if (self.getValue() == '') {
						$('#seoModelNumberSearchInput').addClass('error');
                     } else {
                         $('#seoSearchModels').focus(function() { })
                     }

				} 
			});
            
			// Bind event on button
			$('#seoSearchModels').bind('click', function (e) {
				e.preventDefault();
                if (self.getValue() !== '') {
	                $('#seoModelSearchForm').submit();
	                $('#seoModelNumberSearchInput').removeClass('error');
                } else {
                    $('#seoModelNumberSearchInput').addClass('error');
                }
			});
		}
	}
}());

function placeholderFallback(){
    $("input[placeholder], textarea[placeholder]").each(function() {
        var val = $(this).attr("placeholder");
        if ( this.value == "" ) {
            this.value = val;
        }
        $(this).focus(function() {
            if ( this.value == val ) {
                this.value = "";
            }
        }).blur(function() {
                if ( $.trim(this.value) == "" ) {
                    this.value = val;
                }
            })
    });
}

