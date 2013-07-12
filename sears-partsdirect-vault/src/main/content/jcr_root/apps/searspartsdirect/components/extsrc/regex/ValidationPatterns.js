CQ.Ext.namespace('Shc.components.extsrc.regex');

Shc.components.extsrc.regex.ValidationPatterns = (function () {
	'use strict';
	
	return {
		/**
		 * @type {RegExp}
		 */
		youtube: /https?:\/\/(?:www\.)?youtube.com\/watch\?(?=.*v=\w+)(?:\S+)?$/,
		url: /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/
	};
}());