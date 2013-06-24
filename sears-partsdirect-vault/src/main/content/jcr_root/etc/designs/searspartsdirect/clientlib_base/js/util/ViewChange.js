NS('shc.pd.base.util').ViewChange = (function () {
	'use strict';
	
	return {
		/**
		 * @property {Object} callbacks Stores all callbacks
		 */
		callbacks: {
			resize: [],
			orientation: []
		},
		/**
		 * @property {boolean} eventBinded events binded yet
		 */
		eventBinded: false,
		/**
		 * Add callback on orientation change
		 * @param {Function} callback
		 * @return {undefined}
		 */
		onResize: function (callback) {
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
		onWindowChange: function (callback) {
			this.initEventBindings();
			this.onResize(callback);
			this.onOrientationChange(callback);
		},
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
		 * Fire specified event
		 * @param {string} event Callback property name
		 * @param {array} args Passed variable
		 * @return {undefined}
		 */
		fire: function (event, args) {
			var i = 0;
			// iterate over callback type list
			for (i = 0; i < this.callbacks[event].length; ++i) {
				this.callbacks[event][i](args);
			}
		}
	};
}());