/**
 * @class Breakpoint
 * Base class for breakpoint driven widgets
 */
NS('shc.pd.base.render').Breakpoint = Class.extend(function () {
	'use strict';

	return {
		/**
		 * @constructor
		 */
		init: function () {},
		/**
		 * @type {boolean}
		 */
		active: false,
		/**
		 * @return {boolean}
		 */
		isActive: function () {
			return this.active;
		},
		/**
		 * @param {Boolean} active
		 * @return {undefined}
		 */
		setActive: function (active) {
			this.active = active;
		},
		/**
		 * Stub method to be overrided
		 * @return {undefined}
		 */
		responsiveCallback: function () {},
		/**
		 * Attachment callback stub
		 * @return {undefined}
		 */
		activate: function () {
			console.log(this, 'is active');
		},
		/**
		 * Detach callback stub
		 * @return {undefined}
		 */
		deactivate: function () {
			console.log(this, 'is deactive');
		}
	};
}());