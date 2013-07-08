/*global window:true, $:true, Class:true */
var category101 = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes category101 class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Bind events
			this.bindEvent();
            // check if at mobile breakpoint
            this.toggleAccordion();
		},
                
                /**
		 * Determines the correct rendering method
		 * @return {void}
		 */
		toggleAccordion: function () {
			var self = this,
                isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();

            self[$(target).hasClass('in') ? 'addClass' : 'removeClass']('collapsed');
                        
			if (isMobileBreakpoint === true) {

                $('.accordion-body').collapse101('hide');
				
			} else {
                if (!$('.accordion-body').hasClass('in') ) {
                    $('.accordion-body').addClass('in');
                }
                $('.accordion-body').collapse101('show');
			}
		},

                
         /**
		 * Bind the window resize event
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			// Window resize and orientation change
			// @TODO: Use Matt's responsive helper to bind
			$(window).resize(function () {
				self.toggleAccordion();
			})
			.bind('orientationchange', function () {
				self.toggleAccordion();
			});

			self.isBound = true;
		}
                        

                        
        }
}());