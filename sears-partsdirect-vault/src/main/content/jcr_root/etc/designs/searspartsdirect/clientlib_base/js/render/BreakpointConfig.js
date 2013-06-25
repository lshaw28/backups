/**
 * @class BreakpointConfig
 * Config and wrapper for breakpoints. Used for {WidgetBreakpointRegistry}
 */
NS('shc.pd.base.render').BreakpointConfig = Class.extend(function () {
	return {
		/**
		 * @type {Number}
		 */
		min: null,
		/**
		 * @type {Number}
		 */
		max: null,
		/**
		 * @type {mixed|Breakpont}
		 */
		obj: null,
		/**
		 * @type {Number}
		 */
		id: null,
		/**
		 * @constructor
		 */
		init: function (config) {
			var prop;
			
			// auto config
			for (prop in config) {
				this[prop] = config[prop];
			}
		},
		/**
		 * @param {Number} min
		 * @return {undefined}
		 */
		setMin: function (min) {
			this.min = min;
		},
		/**
		 * @return {Number}
		 */
		getMin: function () {
			return this.min;
		},
		/**
		 * @param {Number} max
		 * @return {undefined}
		 */
		setMax: function (max) {
			this.max = max;
		},
		/**
		 * @return {Number}
		 */
		getMax: function () {
			return this.max;
		},
		/**
		 * @param {mixed|Breakpoint} obj
		 * @return {undefined}
		 */
		setObj: function (obj) {
			this.obj = obj;
		},
		/**
		 * @return {mixed|Breakpoint}
		 */
		getObj: function () {
			return this.obj;
		},
		/**
		 * @param {Number} id
		 * @return {undefined}
		 */
		setIdentifier: function (id) {
			this.id = id;
		},
		/**
		 * @return {Number}
		 */
		getIdentifier: function () {
			return this.id;
		}
	};
}());