/*global window:true, $:true, Class:true, mainSitePath:true */
var commentCheck = Class.extend(function() {
	"use strict";

	return {
		/**
		 * @singleton cartNav Singleton class for the CommentSection
		 * 
		 * init: On page load events to fire
		 */
		init : function() {
			// Properties
			$(".articleComments-form").hide();
			// Initialize events
			this.bindEvents();
			this.enableCommentSection();
		},
		/**
		 * check Screen resolution
		 * 
		 * @return {void}
		 */
		enableCommentSection : function() {
			var self = this, isMobileBreakpoint = window.SPDUtils
					.isMobileBreakpoint();
			$("#btn_load").hide();
			$(".comments-target").show();
			$(".comment-text-label").hide();
			if (isMobileBreakpoint === true) {
				$("#btn_load").hide();
				$(".comments-target").hide();
				$(".articleComments-wrapper h2").hide();
				$(".primary-btn").live("click", function() {
					$(".articleComments-form").show();
					$(".comments-target").show();
					$(".primary-btn").hide();
				});

			} else {
					$(".articleComments-form").show();
					$("#btn_load").hide();
			}
		},
		/**
		 * Perform initial event binding
		 * 
		 * @return {void}
		 */
		bindEvents : function() {
			var self = this;

			// Window resize and orientation change
		// @TODO: Use Matt's responsive helper to bind
		$(window).resize(function() {
			self.enableCommentSection();
		}).bind('orientationchange', function() {
			self.enableCommentSection();
		});
	}
	}
}());
