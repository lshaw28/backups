/*global $:true, window:true, Class:true */
(function (window) {
	"use strict";
	window.SPDAuthorUtils = {
		/**
		 * @namespace SPDAuthorUtils
		 * Global utilities and helper methods for Author environment
		 *
		 * init: On page load events to fire
		 */
		init: function () {
			console.log('SPDAuthorUtils available');
		},
		/**
		 * Set rich text plug-in visibility
		 * @param {object} el ExtJS caller
		 */
		setRTEPlugins: function (el) {
			/* @TODO: Make this cleaner and more reusable */
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
	};
	window.SPDAuthorUtils.init();
}(window));