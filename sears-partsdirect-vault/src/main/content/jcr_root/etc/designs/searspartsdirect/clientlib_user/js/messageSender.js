/*global window:true, $:true, Class:true, mainSitePath:true */
var XDM = {
	'send': function(message) {
		var data = JSON.stringify(message);

		if (window['postMessage']) {
			window.parent['postMessage'](data, parentDomain.replace( /([^:]+:\/\/[^\/]+).*/, '$1'));
		} else {
			window.parent.location = parentDomain.replace(/#.*$/, '') + '#' + (+new Date) + (cache_bust++) + '&' + data;
		}
	}
};