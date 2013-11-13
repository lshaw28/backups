/**
 * @class IndexController
 * Used primarily for carousels and slideshow-like components
 */
NS('shc.pd.base.src').IndexController = Class.extend(function () {
	'use strict';
	return {
		/**
		 * @constructor
		 */
		init: function () {
			// default properties
			this.index = 0; // default is on 0
			this.lastIndex = 0;
			this.maxIndex = 0;
			this.isInfinite = false;
			this.onIndexChange = function () {}; // callback fired
		},
		/**
		 * Proceeds to next index value going forward, invokes `setIndex`
		 * Does nothing if conditions aren't met
		 * @return {Boolean}
		 */
		next: function () {
			if (this.index < this.maxIndex) {
				this.setIndex(this.index + 1, shc.pd.base.util.Enums.Direction.Right);
			} else if (this.isInfinite === true) {
				this.setIndex(0, shc.pd.base.util.Enums.Direction.Right);
			} else {
				return false;
			}
		},
		/**
		 * Proceeds to next index value going backwards, invokes `setIndex`
		 * Does nothing if conditions aren't met
		 * @return {Boolean}
		 */
		back: function () {
			if (this.index > 0) {
				this.setIndex(this.index - 1, shc.pd.base.util.Enums.Direction.Left);
			} else if (this.isInfinite === true) {
				this.setIndex(this.maxIndex, shc.pd.base.util.Enums.Direction.Left);
			} else {
				return false;
			}
		},
		/**
		 * Sets index, defines previous index, invokes `action`
		 * @param {number} index
		 * @param {util.Enums.Direction} direction
		 */
		setIndex: function (index, direction) {
			// set a last index for point of references in `action`
			this.lastIndex = this.index;
			// defined new index
			this.index = index;
			// invoke action which may have animation
			this.action(direction || null);
			// fire onIndexChange event
			this.onIndexChange(index, this.lastIndex);
		},
		/**
		 * Return current index
		 * @return {number}
		 */
		getIndex: function () {
			return this.index;
		},
		/**
		 * Stub, overridable method
		 * @return {void}
		 */
		action: function () {},
		/**
		 * Set max index
		 * @param {number} number
		 * @return {void}
		 */
		setMaxIndex: function (number) {
			this.maxIndex = number;
		},
		/**
		 * Set max index
		 * @param {number} number
		 * @return {void}
		 */
		getMaxIndex: function (number) {
			return this.maxIndex;
		},
		/**
		 * Set event for on index change
		 * @param {Function} event
		 * @return {void}
		 */
		setOnIndexChange: function (event) {
			this.onIndexChange = event;
		}
	};
}());