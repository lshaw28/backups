/*
 * @class Translate3d
 * Manipulate Translate3d - partial support (only x axis)
 */
NS('shc.pd.base.fx').Translate3d = Class.extend(function () {
	'use strict';
	
	/** @const */
	var FPS = 77, // frames per second
		BASE = 1000, // 1 second
		
		/** @private {number} */
		frameRate = BASE / FPS,
		/** @private {mixed} */
		staticGetPoint = null,
		/** @private {mixed} */
		staticCssProperty = null;
	
	/**
	 * Resolves prefix for transform
	 * @private
	 * @return {void}
	 */
	function resolveVendorPrefix() {
		var div = $('<div />'),
			i,
			list = ['transform', 'msTransform', 'MozTransform', 'WebkitTransform', 'OTransform'];
		
		for (i = 0; i < list.length; ++i) {
			if (typeof div[0].style[list[i]] !== 'undefined') {
				staticCssProperty = list[i];
				return; // stop any further execution
			}
		}
		
		// default
		staticCssProperty = list[0];
	}
	
	return {
		/**
		 * @constructor
		 * @param {jQuery} node Reference to object
		 */
		init: function (node) {
			// set vendor prefix resolver
			if (staticCssProperty === null) {
				resolveVendorPrefix();
			}
			
			// property definitions
			this.node = node;
			this.timer = new shc.pd.base.util.Timer(frameRate);
			
			this.eventOnFinish = function () {};
			// set default values
			this.node.translate3d = {
				// store current position
				x: 0,
				y: 0,
				z: 0
			};
			
			// reference to calculating point on a curve
			staticGetPoint = shc.pd.base.fx.Easing.quadraticEnd;
		},
		/**
		 * Set X axis
		 * @param {number} x
		 * @param {string} unit
		 * @return {void}
		 */
		setX: function (x, unit) {
			this.node[0].style[staticCssProperty] = 'translate3d(' + x + unit + ',0,0)';
			// save
			this.node.translate3d.x = x;
		},
		/**
		 * Animate over the X axis using translate3d
		 * @param {number} destination
		 * @param {string} unit
		 * @param {number} duration (milliseconds)
		 * @param {Function} callback
		 * @return {void}
		 */
		animateX: function (destination, unit, duration, callback) {
			if (typeof duration === 'undefined') {
				throw new Error('`duration` argument not supplied');
			}
			
			var n = 0,
				// determine when to stop N from incrementing
				nLimit = Math.round(duration / BASE * FPS),
				// current position
				xCurrent = this.node.translate3d.x,
				// desired position
				xDestination = destination,
				// delta of new and future position
				xDelta = xDestination - xCurrent,
				// reference to instance
				_this = this;
				
			// no queue-ing allowed! (looks bad on UX anyways)
			this.stop(true);
			
			// set callback (used if `stop` is invoked)
			this.eventOnFinish = callback || this.eventOnFinish;
			
			// set tick action (executed per iteration)
			this.timer.setTickAction(function () {
				// get new X from easing method
				_this.setX(staticGetPoint(++n, xCurrent, xDelta, nLimit), unit);
				// stop animation yet?
				if (n >= nLimit) {
					// clean up mathmatical remainders
					_this.setX(xDestination, unit);
					// stop timer
					_this.stop();
				}
			});
			
			// begin animation
			this.timer.start();
		},
		/**
		 * Stop animation
		 * @param {boolean} suppressEvent
		 * @return {void}
		 */
		stop: function (suppressEvent) {
			this.timer.stop();
			// fire callback if allowed
			if (typeof suppressEvent === 'undefined' || suppressEvent === false) {
				this.eventOnFinish();
			}
		}
	};
}());