/**
 * @class WidgetBreakpointResolver
 * Data store for breakpoints and handles activation/deactivation
 */
NS('shc.pd.base.render').WidgetBreakpointRegistry = (function () {
	'use strict';
	
	/**
	 * @type {Array}
	 * Stores configurations and obj wrapper for {BreakPoint};
	 */
	var registryArray = [],
	/**
	 * @type {Number}
	 * Unique identifier for registries
	 */
	id = 0,
	/**
	 * @param {Boolean}
	 */
	binded = false;
	
	return {
		/**
		 * Adds to breakpint registry
		 * @param {BreakpointConfig} breakpointConfig
		 * @return {void}
		 */
		add: function (breakpointConfig) {
			// lazy init
			this.bind();
			
			// type check
			if (breakpointConfig instanceof shc.pd.base.render.BreakpointConfig === false) {
				throw new TypeError('Invalid type to add to BreakpointRegistry');
			}
			
			// set ID
			breakpointConfig.setIdentifier(++id);
			
			// add and generate ID
			registryArray.push(breakpointConfig);
			
			// activate or deactivate
			this.executeChangeAction(breakpointConfig, shc.pd.base.util.ViewChange.getWidth());
		},
		/**
		 * Removes a registry by ID
		 * @param {Number} id
		 * @return {void}
		 */
		remove: function (id) {
			var i;
			
			for (i = 0; registryArray.length > 0; ++i) {
				if (registryArray[i].getIdentifier() === id) {
					registryArray.splice(i, 1);
					break;
				}
			}
		},
		/**
		 * @return {Array}
		 */
		getAll: function () {
			return registryArray;
		},
		/**
		 * Bind responsive callback
		 */
		bind: function () {
			if (binded === false) {
				var _this = this;
				
				// event callback on resize/orientation
				shc.pd.base.util.ViewChange.getInstance().onResponsive(function (args) {
					_this.executeChangeActions(args[0]);
				});
				
				binded = true;
			}
		},
		/**
		 * Iterates over registry using provided parameters and inits enable/disable
		 * @param {Number} width
		 * @return {void}
		 */
		executeChangeActions: function (width) {
			var i;
			
			for (i = 0; i < registryArray.length; ++i) {
				// activation creds met?
				this.executeChangeAction(registryArray[i], width);
			}
		},
		/**
		 * Toggles activation 
		 * @param {mixed|BreakpointConfig} bpConf
		 * @param {Number} width
		 * @return {void}
		 */
		executeChangeAction: function (bpConf, width) {
			// activation creds met?
			if (
				bpConf.getMin() <= width && 
				bpConf.getMax() >= width && 
				bpConf.getObj().isActive() === false
			) {
				// deactivate
				bpConf.getObj().activate();

				// set active state
				bpConf.getObj().setActive(true);
			}

			// deactivation creds met?	
			if (
				(bpConf.getMin() > width || bpConf.getMax() < width) &&
				bpConf.getObj().isActive() === true
			) {
				// activate
				bpConf.getObj().deactivate();

				// set active state 
				bpConf.getObj().setActive(false);
			}
		}
	};
}());