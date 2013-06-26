$(document).ready(function () {
	// desktop carousel initialization
	$('.desktop-carousel').each(function () {
		var carouselElement = $(this);
		
		shc.pd.base.render.WidgetBreakpointRegistry.add(new shc.pd.base.render.BreakpointConfig({
			min: 1024,
			max: 100000,
			obj: new shc.pd.base.widgets.DesktopCarousel(carouselElement)
		}));
	});
});