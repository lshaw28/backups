/*global window:true, $:true, Class:true */
var video = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes video class
		 * @param {object} el Target element
		 * @param {number} fw Optional forced width for consistent display
		 */
		init: function (el, fw, fh) {
			// Parameters
			this.el = $(el);
			// Properties
			this.iframe = null;
			this.youtubeId = '';
			this.fw = 0;
			this.fh = 0;
			this.useParentDims = true;
			this.isBound = false;
			// Retrieve data
			this.setProperties(fw, fh);
			// Render if a YouTube ID is present
			if (this.youtubeId !== '') {
				this.render();
			}
		},
		/**
		 * Retrieves data from attributes and parameters
		 * @param {number} fw Optional forced width for consistent display
		 * @param {number} fh Optional forced height for consistent display
		 * @return {void}
		 */
		setProperties: function (fw, fh) {
			var self = this,
				su = window.SPDUtils;

			// Get video information
			self.youtubeId = su.validString(self.el.data('youtubeid'));

			// Acquire parent dimensions
			self.getParentDims();

			// Override width
			if (su.validNumber(self.el.data('width')) > 0) {
				self.fw = parseInt(self.el.data('width'), 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			} else if (su.validNumber(fw) > 0) {
				self.fw = parseInt(fw, 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			}
			// Override height
			if (su.validNumber(self.el.data('height')) > 0) {
				self.fh = parseInt(self.el.data('height'), 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			} else if (su.validNumber(fh) > 0) {
				self.fh = parseInt(fh, 10);
				// Set the flag so re-renders do not reacquire parent dimensions
				self.useParentDims = false;
			} else {
				// Set height based on 16:10 aspect ratio
				self.fh = (parseInt(self.fw, 10) / 16) * 10;
			}
		},
		/**
		 * Sets dimension values to parent dimensions
		 * @return {void}
		 */
		getParentDims: function () {
			var self = this;
			self.fw = self.el.width();
			self.fh = (parseInt(self.fw, 10) / 16) * 10;
		},
		/**
		 * Renders video iframe from YouTube
		 */
		render: function () {
			var self = this,
				currentWidth = 0;

			// Get parent dimensions
			if (self.useParentDims === true) {
				self.getParentDims();
			}

			// Check if the iframe exists
			if (self.iframe === null) {
				// Create the iframe
				self.iframe = $('<iframe />');
				self.iframe.attr('src', 'http://www.youtube.com/embed/' + self.youtubeId)
					.attr('allowfullscreen', 'true')
					.attr('frameborder', '0')
					.addClass('videoIframe');
				self.el.append(self.iframe);
			} else {
				// Get the iframe's current size
				currentWidth = self.iframe.width();
			}

			// Only set dimensions if the iframe is new or the parent has resized
			if (currentWidth === 0 || currentWidth !== self.fw) {
				self.iframe.css({
					'width': self.fw,
					'height': self.fh
				});
			}

			// Bind event
			if (self.isBound === false && self.useParentDims === true) {
				self.bindEvent();
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
				self.render();
			})
			.bind('orientationchange', function () {
				self.render();
			});

			self.isBound = true;
		}
	};
}());