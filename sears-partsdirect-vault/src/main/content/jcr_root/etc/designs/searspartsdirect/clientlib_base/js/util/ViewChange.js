NS('shc.pd.base.util');

/**
 * @type {String} Engine type
 */
shc.pd.base.util.VIEWCHANGE_ENGINE_TYPE = 'default';

/**
 * @class ViewChange
 */
shc.pd.base.util.ViewChange = (function () {
	'use strict';
	
	return {
		instance: null,
		/**
		 * Helper function for quick window detection impls
		 * @return {void}
		 */
		getInstance: function () {
			if (this.instance === null) {
				// which engine type to use
				switch (shc.pd.base.util.VIEWCHANGE_ENGINE_TYPE) {
					case 'simulator':
					default: 
						this.instance = new shc.pd.base.render.DefaultResponsiveEngine();
					break;
				}
			}
			
			return this.instance;
		},
		/**
		 * @return {Number}
		 */
		getWidth: function () {
			return this.getInstance().getWidth();
		}
	};
}());