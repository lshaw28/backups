NS('shc.pd.base.widgets').TouchCarousel = shc.pd.base.render.Breakpoint.extend(function () {
	'use strict';
	
	var enablerClassName = 'touch-carousel-enabled';
	
	return {
		/**
		 * @constructor
		 * @param {jQuery} parent {HTMLElement}
		 */
		init: function (parent) {
			this.parent = parent;
		},
		activate: function () {
			this.parent.addClass(enablerClassName);
		},
		deactivate: function () {
			this.parent.removeClass(enablerClassName);
		}
	};
}());