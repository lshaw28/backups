NS('shc.pd.base.widgets').TouchCarousel = shc.pd.base.widgets.DesktopCarousel.extend(function () {
	'use strict';

	return {
		/**
		 * @constructor
		 * @param {jQuery} parent {HTMLElement}
		 */
		init: function (parent) {
			this.enablerClassName = 'touchCarouselEnabled';
			this.masterCtrClassName = 'carouselMarkersTouch';

			try {
				var items = $('.carouselItem', parent);

				if (items.length === 0) {
					throw new Error('There are no carousel items');
				}

				this.parent = parent;

				// get sample item width
				//this.movementPx = items.eq(0).width();
				this.movementPx = 290;

				// init carousel object
				this.carousel = new shc.pd.base.src.Carousel(parent, items.length, this.movementPx);
				this.carousel.setAnimationSpeed(300);

				// trigger bindings
				this.bindMasterController();
				this.bindTouchEvents();
				this.onIndexChangeControlDisplay(0);
			} catch (e) {
				console.error(e.message);
			}
		},
		/**
		 * Bind gestures
		 * @returns {undefined}
		 */
		bindTouchEvents: function () {
			var hasSwiped = false,
				_this = this; // is a Enum when set

			Hammer(this.parent, {swipe_velocity: 0.3}).on('swipeleft', function (e) {
				e.gesture.preventDefault();
				hasSwiped = shc.pd.base.util.Enums.Direction.Right;
			});

			Hammer(this.parent, {swipe_velocity: 0.3}).on('swiperight', function (e) {
				e.gesture.preventDefault();
				hasSwiped = shc.pd.base.util.Enums.Direction.Left;
			});

			// experimental, not in requirement anyways
//			Hammer(this.parent).on('drag', function (e) {
//				_this.carousel.getFx().setX(e.gesture.deltaX, 'px');
//			});

			Hammer(this.parent).on('dragend', function (e) {
				if (_this.active === true) {
					if (hasSwiped !== false) { // decide based on gesture
						if (hasSwiped === shc.pd.base.util.Enums.Direction.Right) {
							_this.carousel.next();
						} else {
							_this.carousel.back();
						}
					}

					// reset gesture reference
					hasSwiped = false;
				}
			});
		}
	};
}());