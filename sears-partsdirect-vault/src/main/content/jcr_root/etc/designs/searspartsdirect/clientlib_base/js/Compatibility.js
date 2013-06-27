/**
 * Provide cross-browser compatibility with object methods
 */
''.trim || (String.prototype.trim = function () {
	return this.replace(/^[\s\uFEFF]+|[\s\uFEFF]+$/g,'');
});