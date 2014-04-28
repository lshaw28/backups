/*global $:true, window:true, document:true, Class:true, searchPanel:true, revealPanel:true, responsiveImage: true, video:true, guideNavigation:true, regula:true */
(function (window) {
	"use strict";
	/**
	 * Global functionality instantiation
	 */
	$(document).ready(function () {
		/**
		 * IE support
		 */
		if ($.browser.msie) {
			var v = $.browser.version;
			v = v.slice(0, v.indexOf('.'));
			if (window.SPDUtils.validNumber(v, 1000) < 10) {
				$('html').addClass('lt-ie10');
			}
			if (window.SPDUtils.validNumber(v, 1000) < 9) {
				$('html').addClass('lt-ie9');
			}
			$('html').addClass('ie-v' + v);
		}
		/**
		 * Seo landing Page
		 */
	    var newSeoHeroImage = new seoHeroImage();
		/**
		 * Set up userData singleton class before all else
		 */
		var newUserData = new userData();
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
		var hamburgerOverlay = $('<div></div>').addClass('hamburger-overlay').appendTo('#viewport');
		$('.trigger').add(hamburgerOverlay).bind('click', function (e) {
			e.preventDefault();
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
		 * Navigation hover
		 */
		$('.cartNavItems a[data-toggle]').hover(function() {
			console.log('open');
		  $(this).parent().find('.dropdown-menu').stop(true, true).delay(100).fadeIn(200);
		}, function() {
		  $(this).parent().find('.dropdown-menu').stop(true, true).delay(100).fadeOut(200);
		});
		/**
		 * customAccordionForms component setup
		 */
		$('.customAccordionForms').each(function () {
			var newCustomAccordionForms = new customAccordionForms($(this));
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
		 * responsivePinchImage class setup
		 */
		$('.responsivePinchImage').each(function () {
			var newResponsivePinchImage = new responsivePinchImage($(this));
		});
		/**
         * responsiveDropdown class setup
         */
        $('[data-toggle="responsive-dropdown"]').each(function () {
            var newResponsiveDropdown = new responsiveDropdown($(this));
        });
        /**
         * scrub values in select options to remove '.00'
         * for responsive filter dropdown
         */
        $('[data-toggle="responsive-filter-dropdown"] option').each(function () {
            var temp;
            if (this.innerHTML.indexOf('.00') != -1) {
                temp = this.innerHTML.slice(0, -3);
                this.innerHTML = temp;
                this.value = temp;
            }
        });
        /**
         * responsiveFilterDropdown class setup
         */
        $('[data-toggle="responsive-filter-dropdown"]').each(function (index) {

            var newResponsiveDropdown = new responsiveFilterDropdown($(this), index);

        });
		/**
         * airFilterPartDetails class setup
         */
        $('.airFilterPartDetails').each(function () {
            var newAFPD = new airFilterPartDetails($(this));
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
		 * responsiveCollapse class setup
		 */
		$('.responsiveFindThisPart').each(function () {
			var newresponsiveCollapse = new responsiveCollapse($(this), 'responsiveFindThisPart_js');
		});
		$('.category101').each(function () {
			var newresponsiveCollapse = new responsiveCollapse($(this), 'category101_js');
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
		$('.carousel .desktopCarousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 769,
				max: 100000,
				obj: new shc.pd.base.widgets.DesktopCarousel(carouselElement)
			}));
		});
		// touch carousel initialization
		$('.carousel .touchCarousel').each(function () {
			var carouselElement = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1,
				max: 768,
				obj: new shc.pd.base.widgets.TouchCarousel(carouselElement)
			}));
		});
		// touch relatedGuide initialization
		$('.relatedGuides .touchCarousel').each(function () {
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
		shc.pd.base.widgets.SearchPanelFinder.init($('.modelFinder'));
		/*
		 * SVG icon compatibility fix for legacy browsers
		 */
		if ($('html').hasClass('no-backgroundsize')) {
			$('[class^="svg-icon"]').each(function () {
				var newSVGIcon = new svgIcon($(this));
			});
		}
		/**
		 * Cross-domain iframe fix
		 */
		$('iframe[data-src]').each(function () {
			var isMobileBrowser = window.SPDUtils.isMobileBrowser(),
				windowWidth = $(window).width(),
				newSrc = $(this).data('src').replace('$1', isMobileBrowser.toString()).replace('$2', windowWidth.toString())
				+ window.SPDUtils.getLocationDetails().fullAddress;

			$(this).attr('src', newSrc);
		});
		// Mobile Ad Units
		$('.mobileAdUnit').each(function () {
			var mobileAdUnit = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 0,
				max: 767,
				obj: new shc.pd.base.widgets.AdUnit(mobileAdUnit, 'mobileAdEnabled')
			}));
		});
		// Tablet Ad Units
		$('.tabletAdUnit').each(function () {
			var tabletAdUnit = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 768,
				max: 1023,
				obj: new shc.pd.base.widgets.AdUnit(tabletAdUnit, 'tabletAdEnabled')
			}));
		});
		// Desktop Ad Units
		$('.desktopAdUnit').each(function () {
			var desktopAdUnit = $(this);

			shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
				min: 1024,
				max: 100000,
				obj: new shc.pd.base.widgets.AdUnit(desktopAdUnit, 'desktopAdEnabled')
			}));
		});
		// Initialise AddThis if needed
		if ($('.socialBar').length > 0) {
			window.SPDUtils.prepareAddThis();
		}
		// Side Chat Navigation
		$('.sideChatNavigation').each(function () {
			var newSideChatNavigation = new sideChatNavigation($(this));
		});


		/**
            *
        * NOTE:
        * Please update your the code in the bindEvents fn to
        * more specifically select the accordion(aka collapse) components
        * that are required in the mervRatingHelp update.
            *
        * As it stands, your code will now break the other accordions used
        * throughout the project. Thanks!
            *
        * (See recommendedParts.less for an example of how this works with less,
            * no js/jquery needed.
            *
        */

        //Merv Rating Help
        //var newMervRatingHelp = new mervRatingHelp($(this));
        /**
		 * airFilterDimension class setup
		 */
        var newFilterDim = new airFilterDimension();           
		
		
		/**
		 * (Air/Water) Filter Banners class setup
		 */

        var filterBannerTemplate = $('#js_filterBannerTemplate').length;
        if (filterBannerTemplate === 1 ) {
            var newBanners = new banners();
        }

        /**
         * (PRC) Fix to hide the header-promo message and side-chat on PRC Landing Page
         */
        if ($('#partsRepairCenter').length > 0) {
            $(".headerPromo").addClass('hidden');
            $(".sideChatNavigation").addClass('hidden');
        }
        
	});
}(window));