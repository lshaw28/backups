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
		 * revealPanel class setup
		 */
		$('[data-toggle="reveal"]').each(function () {
			var newRevealPanel = new revealPanel($(this));
		});
		/**
		 * Login Navigation
		 */
		$('.trigger').bind('click', function () {
			$('body').toggleClass('loginNav-open');
		});
		/**
		 * Cart Navigation
		 */
		var newCartNav = new cartNav();
		$('#cartShop [data-toggle]').bind('click', function () {
			$('body').toggleClass('cartNav-open');
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
		 */
		$('.video div[data-youtubeid]').each(function () {
			var newVideo = new video($(this));
		});
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
                
                $('.category101').each(function() {
                    var newCategory101 = new category101($(this));
                });
	});
}(window));