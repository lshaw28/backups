/*global window:true, $:true, Class:true, mainSitePath:true */
var commentCheck = Class.extend(function() {
	"use strict";

	return {
		/**
		 * @singleton commentSection
		 * Singleton class for the commentSection
		 * 
		 * init: On page load events to fire
		 */
		init : function() {
			this.bindEvents();
		},
		/**
		 * BindEvents
		 * 
		 * @return {void}
		 */
		bindEvents : function() {
			var self = this,
				isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint(),
				isTabletBreakpoint = window.SPDUtils.isTabletBreakpoint();
			$(".articleComments-loader button.new-btn").bind('click', function (e) {
				var commentPath = $("[data-path]").data("path");
				$(".comments-target").load(commentPath.concat("/jcr:content/comments.load.html"));
				$(".articleComments-loader button.new-btn").remove();
			});
			if (!(isMobileBreakpoint || isTabletBreakpoint)) {
				$(".articleComments-loader button.new-btn").click();
			}
		}
	}
}());
