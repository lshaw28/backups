NS('shc.pd.base.widgets').DesktopCarousel = shc.pd.base.render.Breakpoint.extend(function () {
	return {
		/**
		 * @constructor
		 * @param {jQuery} parent {HTMLElement}
		 */
		init: function (parent) {
			try {
				var items = $('.carousel-item', parent);
				
				if (items.length === 0) {
					throw new Error('There are no carousel items');
				}
				
				this.parent = parent;
				
				// get sample item width
				this.movementPx = items.eq(0).width();
				
				// init carousel object
				this.carousel = new shc.pd.base.src.Carousel(parent, items.length, this.movementPx);
				
				// trigger bindings
				this.bindSideTriggers();
				
				window.testing = this.carousel;
			} catch (e) {
				console.error(e.message);
			}
		},
		activate: function () {
			
		},
		deactivate: function () {
			
		},
		bindSideTriggers: function () {
			var left = $('<a />'),
				right = $('<a />'),
				_this = this;
			
			// trigger handler back
			left.addClass('carousel-trigger-left').click(function (e) {
				e.preventDefault();
				_this.carousel.back();
			});
			
			left.prependTo(this.parent);
			
			// trigger handler next
			right.addClass('carousel-trigger-right').click(function (e) {
				e.preventDefault();
				_this.carousel.next();
			});
			
			right.prependTo(this.parent);
		},
		bindMasterController: function () {
			
			
			this.carousel.setOnIndexChange(function () {
				
			});
		}
	};
}());