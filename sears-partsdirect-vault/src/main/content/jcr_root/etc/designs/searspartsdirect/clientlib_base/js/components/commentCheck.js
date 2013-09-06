/*global window:true, $:true, Class:true, mainSitePath:true */
var commentCheck = Class.extend(function() {
	"use strict";

	return {
		/**
		 * @singleton commentCheck
		 * Singleton class for the commentCheck functionality
		 * Automatically loads on desktop, requires user action on mobile
		 *
		 * init: On page load events to fire
		 */
		init: function() {
			// Properties
			this.buttons = $('.comments .new-btn');
			this.targets = $('.commentsTarget');
			this.path = '';
			// Set properties and configure
			this.setProperties();
		},
		/**
		 * Set properties
		 * @return {void}
		 */
		setProperties: function () {
			var self = this,
				su = window.SPDUtils,
				isMobile = su.isMobileBreakpoint(),
				isTablet = su.isTabletBreakpoint();

			self.path = su.validString(self.buttons.data('path'));

			if (self.path !== '') {
				if (isMobile || isTablet) {
					this.bindEvents();
				} else {
					this.loadComments();
				}
			}
		},
		/**
		 * Bind the click event to the button
		 * @return {void}
		 */
		bindEvents: function() {
			var self = this;

			self.buttons.bind('click', function () {
				self.loadComments();
			});
		},
		/**
		 * Load comments
		 * @return {void}
		 */
		loadComments: function () {
			var self = this;

			self.targets.load(self.path + '/jcr:content/comments.load.html');
			self.buttons.parent().remove();
		}
	}
}());
