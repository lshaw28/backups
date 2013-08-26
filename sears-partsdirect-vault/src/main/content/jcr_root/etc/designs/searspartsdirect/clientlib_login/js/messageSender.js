/*global window:true, $:true, Class:true, mainSitePath:true */
var XDM = {
	'send': function(message) {
		var self = this,
			data = this.format(message);

		if (window['postMessage']) {
			window.parent['postMessage'](data, parentDomain.replace( /([^:]+:\/\/[^\/]+).*/, '$1'));
		} else {
			window.parent.location = parentDomain.replace(/#.*$/, '') + '#' + (+new Date) + (cache_bust++) + '&' + message;
		}
	},
	'format': function(message) {
		var items = new Array();
		for (var i in message) {
			items.push(i + '=' + message[i]);
		}
		return items.join('&');
	}
};