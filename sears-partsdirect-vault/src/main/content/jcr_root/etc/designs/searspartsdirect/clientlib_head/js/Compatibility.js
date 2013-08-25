/**
 * Fix jQuery XHR bug
 */
var jQueryXHR = (window.ActiveXObject) ? function() { try { return new window.ActiveXObject("Microsoft.XMLHTTP"); } catch(e) {} } : function() { return new window.XMLHttpRequest(); };
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