NS('shc.pd.base.fx').Easing = (function () {
	'use strict';
	return {
		/**
		 * Quadratic Easing
		 * @param {number} t Tick mark
		 * @param {number} b Base (current position)
		 * @param {number} e Delta of new position and base position
		 * @param {number} d Target destination of the Tick mark
		 * @return {number}
		 */
		quadraticEnd: function (t, b, e, d) {
			return (~e + 1) * (t /= d) * (t - 2) + b;
		}
	};
}());