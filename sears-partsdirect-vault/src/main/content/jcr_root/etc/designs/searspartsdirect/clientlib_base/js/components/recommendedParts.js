/*global window:true, $:true, Class:true */
var recommendedParts = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes category101 class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Properties
			this.uniqueID = window.SPDUtils.getGUID();
			this.parentID = 'parent_' + this.uniqueID;
			// set uniqueIDs on Accordion;
			this.setAccordionIDs();
			// default accordions to closed
			this.initAccordion();
		},

		/**
		 * sets unique ids in case of multiple
		 * find this part components on page
		 * @return {void}
		 */
		setAccordionIDs: function () {
			var self = this;

			$('div.accordion', self.el).attr('id', self.parentID);
			$('div.accordion-body', self.el).attr('id', self.uniqueID);
			$('a.accordion-toggle', self.el).attr('data-target', "#"+self.uniqueID);
		},

		/**
		 * adds the class 'in' from the accordion-body
		 * to ensure that the accordion defaults to open.
		 * @return {void}
		 */
		initAccordion: function () {
			var self = this,
				accBody = $('div.accordion-body', self.el);

			if (!accBody.hasClass('in')) {
				$('div.accordion-body', self.el).addClass('in');
			}

		}
	}
}());