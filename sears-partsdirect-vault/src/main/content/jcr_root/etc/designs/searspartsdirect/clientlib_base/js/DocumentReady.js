/*global $:true, window:true, document:true, Class:true, searchPanel:true, revealPanel:true, responsiveImage: true, video:true, guideNavigation:true, regula:true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	$(document).ready(function () {
		/**
		 * Cross-Domain Window Message Handling
		 */
		var newMessageHandler = new messageHandler();
		$(window).bind('message', function (e) {
			e.preventDefault();
			newMessageHandler.handleMessage(e.originalEvent);
		});
		/* Fix Twitter Bootstrap Dropdown Issue */
		$('.dropdown-menu li').click(function(e) {
			if (e.cancelBubble) {
				e.cancelBubble = true;
			} else {
				e.stopPropagation();
			}
		});
        /**
		 * modelHeader singleton class setup
		 */
		var newModelHeader = new modelHeader();
        /**
		 * commentCheck singleton class setup
		 */
		var newCommentCheck = new commentCheck();
		/**
		 * searchPanel singleton class setup
		 */
		var mainSearchPanel = new searchPanel();
		/**
		 * modelNumberSearch class setup
		 */
		$('.modelNumberSearch').each(function () {
			var newModelNumberSearch = new modelNumberSearch($(this));
		});
		/**
		 * revealPanel class setup
		 */
		$('[data-toggle="reveal"]').each(function () {
			var newRevealPanel = new revealPanel($(this));
		});
		/**
		 * Login Navigation toggles
		 */
		$('.trigger').bind('click', function () {
			$('body').toggleClass('loginNav-open');
		});
		$('#loginNavGetHelp').bind('click', function () {
			if (window.SPDUtils.isMobileBreakpoint() === true) {
				$('body').toggleClass('loginNav-open');
			}
		});
		/**
		 * Cart Navigation toggles
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
		 * responsiveImage class setup
		 */
		$('.responsiveImage div[data-desktopimage]').each(function () {
			var newResponsiveImage = new responsiveImage($(this));
		});
		/**
		 * responsiveDropdown class setup
		 */
		$('[data-toggle="responsive-dropdown"]').each(function () {
			var newResponsiveDropdown = new responsiveDropdown($(this));
		});
		/**
		 * video class setup
		 */
		$('.video div[data-youtubeid]').each(function () {
			var newVideo = new video($(this));
		});
        /**
		 * addToCart class setup
		 */
		$('.addToCart_js').each(function () {
			var newAddToCart = new addToCart($(this), $('.addToCartQuantity_js', $(this).parent().parent()));
		});
        /**
		 * guideNavigation class setup
		 */
		$('.guideNavigation').each(function() {
			var newGuideNavigation = new guideNavigation($(this));
		});
        /**
         * collapse101 class setup
         * NOTE: collapse101 is based on Twitter Bootstrap's
         * collapse component with modifications to make it
         * behave responsively the way Sears UX wanted.
         */
        $('[data-toggle="collapse101"]').each(function () {
            var newCollapse101 = new Collapse101($(this));
        });
        /**
         * category101 class setup
         */
        $('.category101').each(function() {
            var newCategory101 = new category101($(this));
        });
		/**
		 * responsiveFindThisPart class setup
		 */
		$('.responsiveFindThisPart').each(function () {
			var newResponsiveFindThisPart = new responsiveFindThisPart($(this));
		});
        // commonParts class setup
        $('.commonParts').each(function() {
            var newCommonParts = new commonParts($(this));
        });
        // recommendedParts class setup
        $('.recommendedParts').each(function() {
            var newrecommendedParts = new recommendedParts($(this));
        });
		// desktop carousel initialization
		$('.desktop-carousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1025,
				max: 100000,
				obj: new shc.pd.base.widgets.DesktopCarousel(carouselElement)
			}));
		});
		// touch carousel initialization
		$('.touch-carousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1,
				max: 650,
				obj: new shc.pd.base.widgets.TouchCarousel(carouselElement)
			}));
		});
		/*
		 * Responsive table initializer
		 */
		shc.pd.base.widgets.ResponsiveTable.init($('table.responsiveTable'));
		/*
		 * Search panel finder widget
		 */
		shc.pd.base.widgets.SearchPanelFinder.init($('.search-panel-finder'));
		/*
		 * SVG icon compatibility fix for legacy browsers
		 */
		if ($('html').hasClass('no-background-size')) {
			$('[class^="svg-icon"]').each(function () {
				var newSVGIcon = new svgIcon($(this));
			});
		}
		/**
		 * Cross-domain iframe fix
		 */
		$('iframe[data-src]').each(function () {
			var newSrc = $(this).data('src') + window.SPDUtils.getLocationDetails().fullAddress;

			$(this).attr('src', newSrc);
		});
	});
}(window));