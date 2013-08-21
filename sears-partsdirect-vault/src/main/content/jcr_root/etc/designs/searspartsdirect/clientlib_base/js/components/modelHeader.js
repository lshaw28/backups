/*global window:true, $:true, Class:true, mainSitePath:true */
var modelHeader = Class.extend(function() {
	"use strict";

	return {
		/**
		 * Initializes responsiveFindThisPart class
		 * @param {object} el Target element
		 */
		init : function(el) {
			// Parameters
			this.el = el;
			// Events
			this.bindEvent();
		},
		/**
		 * toggle model section
		 *
		 * @return {void}
		 */
		bindEvent : function() {
			$('.modelInfo').click(function() {
				$(".icon-chevron-up").toggleClass("icon-chevron-down");
				$('#modelSection').slideToggle(function() {
				});
			});
		}
	}
}());