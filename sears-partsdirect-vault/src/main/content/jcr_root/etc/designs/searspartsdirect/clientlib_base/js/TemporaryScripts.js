/**
 * Original style guide JavaScript
 * Needs to be refactored by Dale Price/Matt Lo
 * This includes:
 * - Implementing classes for commonly-used functionality
 * - Fixing IDs and classes to be more consistent
 * - Moving code into our own DocumentReady and WindowResize files
 * - Removing unnecessary code and debug statements
 */
$('#accordion2').on('hide', function () {
	var plusSign = $(this).find(".accordion-toggle span");
	plusSign.addClass("findPartClosed").removeClass("findPartOpen").html("+");
});

$('#accordion2').on('show', function () {
	var minusSign = $(this).find(".accordion-toggle span");
	minusSign.addClass("findPartOpen").removeClass("findPartClosed").html("-");
});

//Set icons classes
$(".shipping_cont a").hover(function () {
	$(this).closest(".fast_shipping_content").prev(".column_two_icons").addClass("column_two_icons_hover");
}, function () {
	$(".column_two_icons").removeClass("column_two_icons_hover");
});

var viewportWidth = $(window).width();
if (viewportWidth <= 450) {
    $("#searchBarSelectArea input").val("Select");
    $("#searchBarField").val("Model or Part #");
    $("#changeText").val("");
    $("#newFinderModel").val("Locate your model number with our finder");
    menuTrigger();
} else if (viewportWidth <= 650) {
    menuTrigger();
}

var searchTabs = function () {
    // set the tab content and tab pane classes
    $(".legacy_search").addClass("tab-content");
    $("#search_input_tab").addClass("tab-pane");
    $("manuals_help_tab").addClass("tab-pane");
    // add click handlers to tabs
    $('#phone_menu_tab_search a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});
    $('#phone_menu_tab_manuals a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});
    // show the search tab by default
    $('#phone_menu_tab_search a').tab('show');
}

function menuTrigger() {
	var htmlBody = $('body'),
		viewport = $('#viewport'),
		page = $('.container-fluid'),
		trigger = $('.trigger'),
		viewportWidth = $(window).width(),
		viewportMove = viewportWidth - 44,
		loginNav = $("#logNav"),
		menu = $(loginNav),
		closeMask = $("li.home_menu_link a");

	htmlBody.prepend(loginNav);

	var transitionEnd = whichTransitionEvent();

	trigger.click(function () {
		if (htmlBody.hasClass('menu-open')) {
			closeMenu();
			loginNav.hide();
		} else {
			openMenu();
			loginNav.show();
		}
	});

	closeMask.bind('click', function (e) {
		e.preventDefault();
		closeMenu();
	});

	closeMask.bind('touchmove', function (e) {
		e.preventDefault();
	});

	if (('ontouchend' in window)) {
		var startX, startY, moveX, moveY,
			matrix, m41 = 0,
			shouldOpenMenu = false,
			shouldCloseMenu = false,
			menuOpen = false,
			menuOpening = false,
			menuClosing = false;

		page.bind('touchstart', function (e) {
			startX = e.touches[0].pageX;
			startY = e.touches[0].pageY;

			shouldOpenMenu = false;
			shouldCloseMenu = false;

			matrix = new WebKitCSSMatrix(page.style.webkitTransform)
			m41 = matrix['m41'];
		});

		page.bind('touchmove', function (e) {
			var moveX = e.changedTouches[0].pageX,
				moveY = e.changedTouches[0].pageY,
				distanceX = moveX - startX,
				distanceY = moveY - startY,
				movedLeft = distanceX < 0,
				movedRight = distanceX > 5,
				movedVert = Math.abs(distanceY) > 15;

			if (!movedVert) {
				if (!menuOpen && movedRight || menuOpen && movedLeft) {
					event.preventDefault();
					addClass(body, 'menu-moving');
					page.style.webkitTransform = 'translate3d(' + (distanceX + m41) + 'px, 0 , 0)';
				}

				if (!menuOpen && movedRight) {
					shouldOpenMenu = (Math.abs(distanceX) > (body.clientWidth / 2.5));
					shouldCloseMenu = !shouldOpenMenu;
				}

				if (menuOpen && movedLeft) {
					shouldCloseMenu = (Math.abs(distanceX) > (body.clientWidth / 4));
					shouldOpenMenu = !shouldCloseMenu;
				}
			}
		});

		page.bind('touchend', function (e) {
			if (shouldOpenMenu) {
				openMenu();
			}

			if (shouldCloseMenu) {
				closeMenu();
			}
		});

		setTimeout(function () {
			window.scrollTo(0, 0);
		}, 100);
	}

	function openMenu() {
		page.css('webkitTransform', 'translate3d(' + viewportMove + 'px, 0 , 0)');
		menuOpening = true;
		htmlBody.addClass('menu-opening');
		page.bind(transitionEnd, function () {
			menuOpen = true;
			menuOpening = false;

			htmlBody.addClass('menu-open');
			htmlBody.removeClass('menu-opening');
			htmlBody.removeClass('menu-moving');

			this.unbind(transitionEnd, arguments.callee, false);
		});
	}

	function closeMenu() {
		page.css('webkitTransform', 'translate3d(0, 0 , 0)');
		menuClosing = true;
		htmlBody.addClass('menu-closing');
		page.bind(transitionEnd, function () {
			menuOpen = false;
			menuClosing = false;
			htmlBody.removeClass('menu-open');
			htmlBody.removeClass('menu-closing');
			htmlBody.removeClass('menu-moving');
			this.unbind(transitionEnd, arguments.callee, false);
		});
	}

	function whichTransitionEvent() {
		var t, el = document.createElement('fakeelement');
		var transitions = {
			'transition': 'transitionEnd',
			'OTransition': 'oTransitionEnd',
			'MSTransition': 'msTransitionEnd',
			'MozTransition': 'transitionend',
			'WebkitTransition': 'webkitTransitionEnd'
		};

		for (t in transitions) {
			if (el.style[t] !== undefined) {
				return transitions[t];
			}
		}
	}
}

$(document).ready(function () {
	searchTabs();
});