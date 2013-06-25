/**
 * @class DefaultResponsiveEngine
 * Adds orientation and resize support
 */
NS('shc.pd.base.render').DefaultResponsiveEngine = shc.pd.base.render.ResponsiveEngineImpl.extend(function () {
	return {
		/**
		 * @type {Object} 
		 * callbacks Stores all callbacks
		 */
		callbacks: {
			resize: [],
			orientation: []
		},
		/**
		 * Add callback on orientation change
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onResize: function (callback) {
			this.initEventBindings();
			this.callbacks.resize.push(callback);
		},
		/**
		 * Add callback on orientation change
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onOrientationChange: function (callback) {
			this.initEventBindings();
			this.callbacks.orientation.push(callback);
		},
		/**
		 * Add callback to both events
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onResponsive: function (callback) {
			this.initEventBindings();
			this.onResize(callback);
			this.onOrientationChange(callback);
		},
		/**
		 * Bind events
		 * @return {undefined}
		 */
		bindEvents: function () {
			var _this = this,
				browser = $(window);
			
			// resize event
			browser.resize(function () {
				// give resize
				_this.fire('resize', [$(this).width()]);
			});
			
			// orientation change event
			browser.bind('orientationchange', function () {
				// if `orientation` property is undefined, default to 0
				_this.fire('orientation', [$(this).width(), window.orientation || 0]);
			});
		},
		/**
		 * @return {Number}
		 */
		getWidth: function () {
			return $(window).width();
		}
	};
}());