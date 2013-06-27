/*
 * @class Translate3dResolver
 * Automatically determines to use jQuery.animate or Translate3d.animate
 * Partial Support (x axis only)
 */
NS('shc.pd.base.fx').Translate3dResolver = Class.extend(function () {
	'use strict';
	
	return {
		/**
		 * @constructor
		 * @param {jQuery} node
		 * @param {string} fallbackType CSS Property
		 */
		init: function (node, fallbackType) {
			this.node = node;
			this.fallbackType = fallbackType;
			// need translate3d instance?
			if (Modernizr.csstransforms3d === true) {
				// define 3d instance
				this.animator3d = new shc.pd.base.fx.Translate3d(this.node);
			}
			// define animateX
			this.lateBindAnimateX();
			this.lateBindSetX();
		},
		/**
		 * Stub to be late binded - set X on translate3d or other CSS properties
		 * @param {number} x
		 * @param {string} unit
		 * @return {undefined}
		 */
		setX: function (x, unit) {},
		/**
		 * Stub to be late binded - Animates X on translate3d or other CSS properties
		 * @param {number} x
		 * @param {string} unit
		 * @param {number} duration in milliseconds
		 * @param {Function} callback
		 * @return {undefined}
		 */
		animateX: function (x, unit, duration, callback) {},
		/**
		 * Redefine `util.Translate3dResolver.animateX`
		 * @return {undefined}
		 */
		lateBindAnimateX: function () {
			// redefine `animate` method based on available feature
			if (Modernizr.csstransforms3d === true) {
				// redefine animation instance
				this.animateX = function (x, unit, duration, callback) {
					
					this.animator3d.stop(true);
					
					// translate3d animation
					this.animator3d.animateX(x, unit, duration, callback || function () {});
				};
			} else {
				// redefine animation instance using basic jQuery animation
				this.animateX = function (x, unit, duration, callback) {
					var css = {};
					css[this.fallbackType] = x + unit;
					
					this.node.stop(true, true);
					
					// jQuery animation
					this.node.animate(css, duration, callback || function () {});
				};
			}
		},
		/**
		 * Redefine `util.Translate3dResolver.setX`
		 * @return {undefined}
		 */
		lateBindSetX: function () {
			// redefine `setX` method based on available feature
			if (Modernizr.csstransforms3d === true) {
				// set on translate3d instance
				this.setX = function (x, unit) {
					// if we're setting X while animation, this should override it
					this.animator3d.stop(true);
					// translate3d animation
					this.animator3d.setX(x, unit);
				};
			} else {
				// css
				this.setX = function (x, unit) {
					var css = {};
					css[this.fallbackType] = x + unit;
					// jQuery style update (and stop animation)
					this.node.stop(true, true).css(css);
				};
			}
		}
	};
}());