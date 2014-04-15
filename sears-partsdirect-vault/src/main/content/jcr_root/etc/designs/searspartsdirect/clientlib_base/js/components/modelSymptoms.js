/*global window:true, $:true, Class:true, mainSitePath:true */
var modelSymptoms = Class.extend(function() {
	"use strict";

	return {
		/**
		 * Initializes modelSymptoms class
		 * @param {object} el Target element
		 */
		init : function(el) {
			// Parameters
			this.el = el;
			// Events
			this.bindEvent();
		},
		/**
		 * initial call to send Omniture if page found from Repair Help search
		 *
		 * @return {void}
		 */
		bindEvent : function() {
			if ($('#modelNo').attr('data-search') == 'RepairHelp') {
				SPDUtils.trackEvent({event: 'searchSuccess', values: {searchTerm: $('#modelNo').text(), searchType: 'Repair Help Model Search', searchTotal: '1.1', resultType: 'Model Repair Help Section'}, componentPath: $('#modelNo').attr('data-component')}, 'Model_Symptoms_#templateName');
			}
		}
	}
}());