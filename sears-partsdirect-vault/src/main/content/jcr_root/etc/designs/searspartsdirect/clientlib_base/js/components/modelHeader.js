
/*global window:true, $:true, Class:true, mainSitePath:true */
var modelsectionHeader = Class.extend(function() {
	"use strict";

	return {
		/**
		 * model section
		 * 
		 * init: On page load events to fire
		 */
		init : function() {


			this.modelSection();


		},
		/**
		 * toggle model section
		 * 
		 * @return {void}
		 */
		modelSection : function() {
            $('.modelInfo').click(function() {
              $(".icon-chevron-up").toggleClass("icon-chevron-down");
              $('#modelSection').slideToggle(function() {


              });
            });

		}
	}
}());