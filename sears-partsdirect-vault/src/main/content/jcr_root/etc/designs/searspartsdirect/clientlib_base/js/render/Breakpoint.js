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
		 * @return {void}
		 */
		setActive: function (active) {
			this.active = active;
		},
		/**
		 * Stub method to be overrided
		 * @return {void}
		 */
		responsiveCallback: function () {},
		/**
		 * Attachment callback stub
		 * @return {void}
		 */
		activate: function () {
			// Not required
		},
		/**
		 * Detach callback stub
		 * @return {void}
		 */
		deactivate: function () {
			// Not required
		}
	};
}());