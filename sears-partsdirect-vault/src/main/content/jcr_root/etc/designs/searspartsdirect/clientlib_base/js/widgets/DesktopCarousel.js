NS('shc.pd.base.widgets').DesktopCarousel = shc.pd.base.render.Breakpoint.extend(function () {
	'use strict';

	return {
		/**
		 * @constructor
		 * @param {jQuery} parent {HTMLElement}
		 */
		init: function (parent) {
			this.enablerClassName = 'desktopCarouselEnabled';
			this.masterCtrClassName = 'carouselMarkersDesktop';

			try {
				var items = $('.carouselItemHolder', parent);

				this.parent = parent;

				// get sample item width
				//this.movementPx = items.eq(0).width();
				this.movementPx = 550;

				// init carousel object
				this.carousel = new shc.pd.base.src.Carousel(parent, items.length, this.movementPx);

				// trigger bindings
				this.bindSideTriggers();
				this.bindMasterController();
				this.onIndexChangeControlDisplay(0);
			} catch (e) {
			}
		},
		/**
		 * Activation event
		 * @return {undefined}
		 */
		activate: function () {
			this.parent.addClass(this.enablerClassName);
			this.carousel.enableAction();
		},
		/**
		 * Deactivation event
		 * @return {undefined}
		 */
		deactivate: function () {
			this.parent.removeClass(this.enablerClassName);

			// disable animation
			this.carousel.disableAction();

			// reset index
			this.carousel.setIndex(0);

			// remove all styling
			$('.carouselListWrapper', this.parent).removeAttr('style');
		},
		/**
		 * Generate left/right triggers
		 * @return {undefined}
		 */
		bindSideTriggers: function () {
			var left = $('<a />'),
				right = $('<a />'),
				_this = this;

			// trigger handler back
			left.addClass('carouselTriggerLeft').click(function (e) {
				e.preventDefault();
				_this.carousel.back();
			});

			left.prependTo(this.parent);

			// trigger handler next
			right.addClass('carouselTriggerRight').click(function (e) {
				e.preventDefault();
				_this.carousel.next();
			});

			right.prependTo(this.parent);

			// add icons
			left.html('<i class="icon-angle-left"></i>');
			right.html('<i class="icon-angle-right"></i>');

			_this.left = left;
			_this.right = right;
		},
		/**
		 * Generate master controller
		 * @return {undefined}
		 */
		bindMasterController: function () {
			var container = $('<div />'),
				itemWrapper = $('<div />'),
				i,
				items = null,
				_this = this;

			for (i = 0; i <= this.carousel.getMaxIndex(); ++i) {
				itemWrapper.append($('<a />'));
			}

			container.addClass(this.masterCtrClassName);
			itemWrapper.addClass('carouselMarkersWrapper');

			// append to container, and append to document
			itemWrapper.appendTo(container);
			container.appendTo(this.parent);

			// get all div as jQuery reference
			items = $('a', itemWrapper);

			// controller clicks
			items.click(function (e) {
				e.preventDefault();
				_this.carousel.setIndex($(this).index());
			});

			// set current
			items.eq(this.carousel.getIndex()).addClass('active');

			// event callback
			this.carousel.setOnIndexChange(function (index) {
				items.removeClass('active');
				items.eq(index).addClass('active');
				_this.onIndexChangeControlDisplay(index);
			});

			_this.controls = container;
		},
		/**
		 * Set control displays on index change
		 */
		onIndexChangeControlDisplay: function (index) {
			var _this = this,
				itemCount = $('.carouselItemHolder', _this.parent).length;

			// Catch errors in case there is inheritance
			try {
				// Show or hide the left trigger
				if (itemCount < 2) {
					_this.left.addClass('hideTrigger');
				} else if (index === 0) {
					_this.left.addClass('hideTrigger');
				} else {
					_this.left.removeClass('hideTrigger');
				}
			} catch(e) {
			}
			try {
				// Show or hide the right trigger
				if (itemCount < 2) {
					_this.right.addClass('hideTrigger');
				} else if (index === (itemCount - 1)) {
					_this.right.addClass('hideTrigger');
				} else {
					_this.right.removeClass('hideTrigger');
				}
			} catch(e) {
			}
			try {
				// Show or hide the master controls
				if (itemCount < 2) {
					_this.controls.addClass('hideControls');
				} else {
					_this.controls.removeClass('hideControls');
				}
			} catch(e) {
			}
		}
	};
}());