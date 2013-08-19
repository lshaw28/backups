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
			$('.customAccordionForms .accordion-toggle').bind('click', function () {
				if ($(this).parent().hasClass('cafHeadingOpen')) {
					$(this).parent().removeClass('cafHeadingOpen');
				} else {
					$(this).parent().addClass('cafHeadingOpen');
				}
			});
		}
	};
}());