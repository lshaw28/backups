/*global window:true, $:true, Class:true */
var svgIcon = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes svgIcon class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Render
			this.render();
		},

		/**
		 * Renders an image inside an SVG icon
		 * @return {void}
		 */
		render: function () {
			var self = this,
				su = window.SPDUtils,
				backgroundImage = self.el.css('background-image');

			if (backgroundImage.indexOf('url(') > -1) {
				backgroundImage = backgroundImage.split('(')[1];
				backgroundImage = backgroundImage.split(')')[0].replace(/["']/g, "");
			}

			// Generate image
			var img = $('<img />');
			img.attr('src', backgroundImage);
			self.el.append(img);
			self.el.addClass('legacy');
		}
	};
}());