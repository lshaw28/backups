/*global $:true, window:true, document:true, Class:true, responsiveImage: true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	$(document).ready(function () {
		/**
		 * Search Panel
		 */
		var mainSearchPanel = new searchPanel();
		/**
		 * Login Navigation
		 */
		$('.loginNav').each(function () {
			var newLoginNav = new loginNav($(this));
		});
		/**
		 * Input help text listeners
		 */
		$('[data-inputhelp]').each(function () {
			// Perform initial check
			window.SPDUtils.checkInput($(this));
			// Bind events
			$(this).bind('blur', function () {
				window.SPDUtils.checkInput($(this));
			})
			.bind('focus', function () {
				window.SPDUtils.checkInput($(this), true);
			})
			.bind('change', function () {
				window.SPDUtils.checkInput($(this));
			});
		});
		/**
		 * Link help text listeners
		 */
		$('[data-texthelp]').each(function () {
			// Perform initial check
			window.SPDUtils.checkLink($(this));
		});
		/**
		 * responsiveImage component setup
		 */
		$('.responsiveImage div[data-desktopimage]').each(function () {
			var newResponsiveImage = new responsiveImage($(this));
		});
		/**
		 * video component setup
		 * Verify class is loaded
		 */
		if (typeof video === 'function') {
			$('.video div[data-youtubeid]').each(function () {
				var newVideo = new video($(this));
			});
		};
        /**
		 * guideNavigation component setup
		 */
		$('.guideNavigation').each(function() {
			var newGuideNavigation = new guideNavigation($(this));
			newGuideNavigation.setBreakPoint($(this).offset()['top']);
			newGuideNavigation.setClassToggles('gn-sticky', 'gn-unsticky');

			return $(window).scroll(function() {
				var scrollDist;
				scrollDist = $(window).scrollTop();
				newGuideNavigation.checkState(scrollDist);
			});

		});
	});
}(window));