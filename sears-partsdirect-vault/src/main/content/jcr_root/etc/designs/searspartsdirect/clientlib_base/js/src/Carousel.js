/**
 * @class Carousel
 */
NS('shc.pd.base.src').Carousel = shc.pd.base.src.IndexController.extend(function () {
	/**
	 * @type {Number}
	 */
	var ANIMATION_SPEED = 500;

	return {
		/**
		 * @constructor
		 * @param {jQuery} parent {HTMLElement}
		 * @param {Number} itemCount
		 * @param {Number} movementPx How much to move
		 */
		init: function (parent, itemCount, movementPx) {
			this._super();

			// we need items, or else this should not be in use
			if ($('.carouselItemHolder', parent).length === 0) {
				throw new Error('No items to carousel');
			}

			this.callback = function () {};
			this.hasAction = true;

			// item wrapper that we move
			this.itemWrapper = $('.carouselListWrapper', parent);

			// fx handler
			this.moveFx = new shc.pd.base.fx.Translate3dResolver(this.itemWrapper, 'marginLeft');

			// detect what kind of width we're gonna use
			this.setMovementPx(movementPx);

			// maxIndex
			this.setMaxIndex(itemCount - 1);
		},
		/**
		 * @Override
		 * @return {void}
		 */
		action: function () {
			if (this.hasAction === true) {
				this.moveFx.animateX(this.movementPx * this.index * -1, 'px', this.animationSpeedMs || ANIMATION_SPEED, this.callback);
			}
		},
		/**
		 * @return {Number}
		 */
		getMovementPx: function () {
			return this.movementPx;
		},
		/**
		 * @return {void}
		 */
		setMovementPx: function (movementPx) {
			this.movementPx = movementPx;
		},
		/**
		 * Set fx callback
		 * @param {Function} callback
		 * @return {void}
		 */
		onFxComplete: function (callback) {
			this.callback = callback;
		},
		/**
		 * @return {void}
		 */
		enableAction: function () {
			this.hasAction = true;
		},
		/**
		 * @return {void}
		 */
		disableAction: function () {
			this.hasAction = false;
		},
		/**
		 * Overrides default animation
		 * @param {Number} ms
		 * @returns {undefined}
		 */
		setAnimationSpeed: function (ms) {
			this.animationSpeedMs = ms;
		},
		/**
		 * Returns animation object
		 * @returns {shc.pd.base.fx.Translate3dResolver}
		 */
		getFx: function () {
			return this.moveFx;
		}
	};
}());