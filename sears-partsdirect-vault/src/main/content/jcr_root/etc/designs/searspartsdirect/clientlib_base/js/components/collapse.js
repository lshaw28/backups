function handleAccordionToggleClick(e) {
    var isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();
    if (!isMobileBreakpoint) {
        e.stopImmediatePropagation();
    }
}

$('.accordion-toggle').on('click', handleAccordionToggleClick);