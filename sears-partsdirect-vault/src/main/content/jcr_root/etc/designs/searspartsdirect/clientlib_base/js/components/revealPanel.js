/*global window:true, $:true, Class:true */
var revealPanel = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes revealPanel class
		 * Follows a similar pattern to Twitter Bootstrap's JavaScript widgets
		 * @param {object} el Toggle element
		 * @param {string} ac Optional active class
		 * @param {string} iac Optional inactive class
		 */
		init: function (el, ac, iac) {
			// Parameters
			this.el = $(el);
			// Properties
			this.targetEl = null;
			this.ac = 'active';
			this.iac = '';
			// Retrieve data
			this.setProperties(ac, iac);
			// Bind events
			this.bindEvent();
		},
		/**
		 * Retrieves data from attributes and parameters
		 * @param {string} ac Optional active class
		 * @param {string} iac Optional inactive class
		 * @return {void}
		 */
		setProperties: function (ac, iac) {
			var self = this,
				su = window.SPDUtils;

			// Target element
			self.targetEl = $(self.el.data('target'));
			// Parameters
			if (su.validString(ac) !== '') {
				self.ac = su.validString(ac);
			}
			if (su.validString(iac) !== '') {
				self.iac = su.validString(iac);
			}
		},
		/**
		 * Toggle the class on the target element
		 */
		togglePanel: function () {
			var self = this;

			// Use class to determine state
			// Optionally add or remove an inactive class
			if (self.targetEl.hasClass(self.ac)) {
				self.targetEl.removeClass(self.ac);

				if (self.iac !== '') {
					self.targetEl.addClass(self.iac);
				}
			} else {
				self.targetEl.addClass(self.ac);

				if (self.iac !== '') {
					self.targetEl.removeClass(self.iac);
				}
			}
		},
		/**
		 * Bind the toggle event
		 * @return {void}
		 */
		bindEvent: function () {
			var self = this;

			self.el.bind('click', function (e) {
				e.preventDefault();
				self.togglePanel();
			});
		}
	};
}());