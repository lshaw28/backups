/*global window:true, $:true, Class:true, mainSitePath:true */
var sideChatNavigation = Class.extend(function() {
	"use strict";

	return {
		/**
		 * Initializes sideChatNavigation class
		 * @param {object} el Target element
		 */
		init: function(el) {
			// Parameters
			this.el = el;
			// Perform setup
			this.bindEvents();
			this.bindSwipes();
		},
		/**
		 * Bind events
		 * @return {void}
		 */
		bindEvents: function() {
			var self = this;

			$('[data-toggle="sidechattoggle"]', self.el).click(function () {
				self.show();
			}, function() {
				self.hide();
			});

		},
		bindSwipes: function() {
			var self = this;

			self.el.swipe({
			    swipeLeft:function(event, direction, distance, duration, fingerCount) {
			    	self.show();
			    },
			    swipeRight:function(event, direction, distance, duration, fingerCount) {
			    	self.hide();
			    },

			});
		},
		/**
		 * Show the chat panel
		 * @return {void}
		 */
		show: function () {
			var self = this;
			
			self.el.animate({
				width: 400,
				marginLeft: 1,
				marginRight: 340,
				display: 'toggle'
			}, 500);
		},
		/**
		 * Hide the chat panel
		 * @return {void}
		 */
		hide: function () {
			var self = this;
			
			self.el.animate({
				width: 400,
				marginLeft: 0,
				marginRight: 0,
				display: 'toggle'
			}, 500);
		}
	}
}());