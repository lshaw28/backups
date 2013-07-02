function handleAccordionToggleClick(e) {
    console.log('i heard that');
    var isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();
    if (!isMobileBreakpoint) {
        e.stopImmediatePropagation();
    }
}

$('.accordion-toggle').on('click', handleAccordionToggleClick);