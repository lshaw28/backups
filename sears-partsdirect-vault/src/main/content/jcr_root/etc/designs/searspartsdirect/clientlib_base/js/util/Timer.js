NS('shc.pd.base.util').Timer = Class.extend(function () {
	'use strict';
	return {
		/**
		 * @constructor
		 * @param {number} interval Number of milliseconds between each iteration
		 * @param {boolean} singleUse Determines to fire one or many iterations
		 */
		init: function (interval, singleUse) {
			this.interval = interval;
			this.singleUse = singleUse;
			this.session = null; // stores Interval session
			this.tickAction = function () {}; // stores executable code as a functional expression
		},
		/**
		 * Starts iteration
		 * @return {void}
		 */
		start: function () {
			if (this.singleUse === true) {
				// single iteration
				this.session = setTimeout(this.tickAction, this.interval);
			} else {
				// multiple iterations
				this.session = setInterval(this.tickAction, this.interval);
			}
		},
		/**
		 * Stop iteration
		 * @param {boolean} invokeLastTickAction Fires last tick if set to true
		 * @return {void}
		 */
		stop: function (invokeLastTickAction) {
			// check for multiple iterations
			if (this.singleUse === true) {
				throw new Error('Cannot use `stop` on `setTimeout`s');
			}
			
			// set default
			invokeLastTickAction = invokeLastTickAction || false;
			// stop timer session
			clearInterval(this.session);
			
			// optional fire
			if (invokeLastTickAction === true) {
				this.tickAction();
			}
		},
		/**
		 * Stops and starts timer
		 * @return {void}
		 */
		reset: function () {
			this.stop();
			this.start();
		},
		/**
		 * Event that is invoked per iteration
		 * @param {Function} setTickAction
		 * @return {void}
		 */
		setTickAction: function (setTickAction) {
			this.tickAction = setTickAction;
		}
	};
}());