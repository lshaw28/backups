/**
 * @class ResponsiveEngineImpl
 */
NS('shc.pd.base.render').ResponsiveEngineImpl = Class.extend(function () {
	return {
		/**
		 * @constructor
		 *
		 */
		init: function () {
			this.eventBinded = false;
		},
		/**
		 * Fire specified event
		 * @param {string} event Callback property name
		 * @param {array} args Passed variable
		 * @return {undefined}
		 */
		fire: function (event, args) {
			try {
				var i = 0;
				// iterate over callback type list
				for (i = 0; i < this.callbacks[event].length; ++i) {
					// property driven store
					this.callbacks[event][i](args);
				}
			} catch (e) {
			}
		},
		/**
		 * @type Object
		 * Holds callback arrays
		 */
		callbacks: {},
		/**
		 * Stub method for entire window resize
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onResponsive: function (callback) {},
		/**
		 * Check if events are binded
		 * @return {undefined}
		 */
		initEventBindings: function () {
			if (this.eventBinded === false) {
				this.bindEvents();
				// update property
				this.eventBinded = true;
			}
		},
		/**
		 * Bind events stub
		 * @return {undefined}
		 */
		bindEvents: function () {},
		/**
		 * Stub to be overrided
		 * @return {Number}
		 */
		getWidth: function () {}
	};
}());