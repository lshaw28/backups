/*global $:true, Class:true */
var guideNavigation = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes guideNavigation class
		 */
		init: function (el) {
			// Parameters
			this.el = el;
			this.classOn = 'gn-sticky';
			this.classOff = 'gn-unsticky';
			this.maxScroll = false;
			// Bind Events
			this.bindEvents();
			this.callback = '';

            // set scroll offsets for links
            $(".guideNavigation li a").each( function (index, element) {
                $( element ).on("click", function() {
                    var $href = $(this).attr('href'),
						name = $href.substring(1),
						anchor = $('[name='+name+']').offset(),
						stickyHeight = $('.guideNavigation')[0].offsetHeight + 5;
                    $('html,body').animate({
						scrollTop: anchor.top - stickyHeight
					});
                })

            });

		},
		/**
		 * Given scroll position of parent window, applies and removes "sticky" css classes
		 * @param {number} val Required current scrolltop of window
		 * @return {void}
		 */
		checkState: function(val) {
			var self = this,
				breakpoint = self.el.prev().offset().top + self.el.prev()[0].offsetHeight;

			if (self.el !== false) {
				if (val > breakpoint && !self.el.hasClass(self.classOn)) {
					self.el.removeClass(self.classOff).addClass(self.classOn);
				} else if (val <= breakpoint && !self.el.hasClass(self.classOff)) {
					self.el.removeClass(self.classOn).addClass(self.classOff);
				}
			}
		},
		/**
		 * Sets max scrolling distance for sticky nav functionality
		 * @param {int} max Optional maxscroll value
		 */
		maxScroll: function (max) {
			var self = this;

			self.maxScroll = max !== null ? maxScroll : false;
		},
		/**
		 * Bind events
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;

			$(window).bind('scroll', function() {
				var scrollDist = $(window).scrollTop();
				self.checkState(scrollDist);
			});
		}
	};
}());