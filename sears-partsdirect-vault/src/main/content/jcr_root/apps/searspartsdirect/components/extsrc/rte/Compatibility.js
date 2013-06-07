/**
 * @Override {CUI.rte.Compatibility}
 */
(function () {
	'use strict';
	
	// reference old method
	var configurePlugins = CUI.rte.Compatibility.configurePlugins;

	/**
	 * @Override
	 * Patch to support RTE plugin configurations from versions less than 5.6
	 * - Fixes removing existing plugins
	 * @param {Object} config The config object to be used for configuration
	 * @param {CUI.rte.EditorKernel} editorKernel The editor kernel
	 * @private
	 */
	CUI.rte.Compatibility.configurePlugins = function (config, editorKernel) {
		var pluginId,
			pluginItem = null;
		
		try {
			for (pluginId in config.rtePlugins) {
				pluginItem = config.rtePlugins[pluginId];
				
				// plugin ID verification
				if (typeof pluginItem.features === 'string') {
					// add removal support
					if (pluginItem.features === '-') {
						// remove it from list of registered plugins provided
						delete editorKernel.registeredPlugins[pluginId];
					}
				}
			}
		} catch (e) {
			console.log(e);
		} finally {
			return configurePlugins(config, editorKernel);
		}
	};
}());