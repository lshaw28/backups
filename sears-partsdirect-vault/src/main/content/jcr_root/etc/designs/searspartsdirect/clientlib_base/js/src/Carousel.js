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
			if ($('.carousel-item', parent).length === 0) {
				throw new Error('No items to carousel');
			}
			
			this.callback = function () {};
			
			// item wrapper that we move
			this.itemWrapper = $('.carousel-list-wrapper', parent);
			
			// fx handler
			this.moveFx = new shc.pd.base.fx.Translate3dResolver(this.itemWrapper, 'marginLeft');
			
			// detect what kind of width we're gonna use
			this.setMovementPx(movementPx);
			
			// maxIndex
			this.setMaxIndex(itemCount - 1);
		},
		/**
		 * @Override
		 * @return {undefined}
		 */
		action: function () {
			this.moveFx.animateX(this.movementPx * this.index * -1, 'px', ANIMATION_SPEED, this.callback);
		},
		/**
		 * @return {Number}
		 */
		getMovementPx: function () {
			return this.movementPx;
		},
		/**
		 * @return {undefined}
		 */
		setMovementPx: function (movementPx) {
			this.movementPx = movementPx;
		},
		/**
		 * Set fx callback
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onFxComplete: function (callback) {
			this.callback = callback;
		}
	};
}());