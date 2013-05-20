/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window.SPDAuthorUtils = Class.extend({
		/**
		 * @class SPDAuthorUtils
		 * Global utilities and helper methods for Author environment
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			try {
				console.log('SPDUtils available');
			} catch (e) {
			}
		},
		/**
		 * Set rich text plug-in visibility
		 * @param {Object} el ExtJS caller
		 */
		setRTEPlugins: function (el) {
			/* @TODO: Make this cleaner and more reusable */
			if (console) {
				console.log(el);
			}
			$('.x-edit-justifyleft').parent().parent().parent().parent().parent().parent().prev().hide();
			$('.x-edit-justifyleft').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-justifycenter').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-justifyright').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-insertunorderedlist').parent().parent().parent().parent().parent().parent().prev().hide();
			$('.x-edit-insertunorderedlist').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-insertorderedlist').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-outdent').parent().parent().parent().parent().parent().parent().hide();
			$('.x-edit-indent').parent().parent().parent().parent().parent().parent().hide();
		}
	});
}(window));