/*global $:true, window:true, document:true, Class:true, searchPanel:true, revealPanel:true, responsiveImage: true, video:true, guideNavigation:true, regula:true */
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
		// desktop carousel initialization
		$('.desktop-carousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1024,
				max: 100000,
				obj: new shc.pd.base.widgets.DesktopCarousel(carouselElement)
			}));
		});
		// touch carousel initialization
		$('.touch-carousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1,
				max: 1023,
				obj: new shc.pd.base.widgets.TouchCarousel(carouselElement)
			}));
		});
		/**
		 * Form validation
		 */
		var registerForm = new modalForm($('#registerModal')),
			loginForm = new modalForm($('#loginModal'));

		// Custom validation for matching email fields
		regula.custom({
			name: "EmailsMatch",
			formSpecific: true,
			defaultMessage: "Email addresses do not match!",
			params: ["field1", "field2"],
			validator: function(params) {
				var failingElements = [],
					emailField1 = document.getElementById(params["field1"]),
					emailField2 = document.getElementById(params["field2"]);

				if (emailField1.value != emailField2.value) {
					failingElements = [emailField1, emailField2];
				}

				return failingElements;
			}
		});
		regula.bind();
	});
}(window));