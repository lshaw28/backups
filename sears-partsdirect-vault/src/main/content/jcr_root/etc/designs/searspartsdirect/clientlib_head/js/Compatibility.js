/**
 * Fix jQuery XHR bug
 */
$.ajaxTransport("+*", function( options, originalOptions, jqXHR ) {
	if(jQuery.browser.msie && window.XDomainRequest) {
		var xdr;

		return {
			send: function( headers, completeCallback ) {
				// Use Microsoft XDR
				xdr = new XDomainRequest();
				xdr.open("get", options.url);
				xdr.onload = function() {
					if (this.contentType.match(/\/xml/)) {
						var dom = new ActiveXObject("Microsoft.XMLDOM");
						dom.async = false;
						dom.loadXML(this.responseText);
						completeCallback(200, "success", [dom]);
					} else {
						completeCallback(200, "success", [this.responseText]);
					}
				};
				xdr.ontimeout = function(){
					completeCallback(408, "error", ["The request timed out."]);
				};
				xdr.onerror = function(){
					completeCallback(404, "error", ["The requested resource could not be found."]);
				};
				xdr.send();
			},
			abort: function() {
				if(xdr)xdr.abort();
			}
		};
	}
});
/**
 * Provide cross-browser compatibility with object methods
 */
console = console || { 'log': function () {} };
''.trim || (String.prototype.trim = function () {
	return this.replace(/^[\s\uFEFF]+|[\s\uFEFF]+$/g,'');
});
var JSON = JSON || {};
JSON.stringify = JSON.stringify || function (obj) {
	var t = typeof (obj);
	if (t != "object" || obj === null) {
		// simple data type
		if (t == "string") obj = '"'+obj+'"';
		return String(obj);
	} else {
		// recurse array or object
		var n, v, json = [], arr = (obj && obj.constructor == Array);
		for (n in obj) {
			v = obj[n]; t = typeof(v);
			if (t == "string") v = '"'+v+'"';
			else if (t == "object" && v !== null) v = JSON.stringify(v);
			json.push((arr ? "" : '"' + n + '":') + String(v));
		}
		return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
	}
};
JSON.parse = JSON.parse || function (str) {
	if (str === "") str = '""';
	eval("var p=" + str + ";");
	return p;
};