var XDM = function() {
	var interval_id,
	last_hash,
	cache_bust = 1,
	attached_callback,
	window = this;

	return {
		send : function(message) {
			if (window['postMessage']) {
				window.top['postMessage'](message, parentDomain.replace( /([^:]+:\/\/[^\/]+).*/, '$1'));
			} else {
				window.top.location = parentDomain.replace(/#.*$/, '') + '#' + (+new Date) + (cache_bust++) + '&' + message;
			}
		}
	};
}();