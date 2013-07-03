/* COLLAPSE101 PUBLIC CLASS DEFINITION
 * ================================ */

var Collapse101 = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.collapse101.defaults, options)

    if (this.options.parent) {
        this.$parent = $(this.options.parent)
    }

    this.options.toggle && this.toggle()
}

Collapse101.prototype = {

    constructor: Collapse101

    , dimension: function () {
        var hasWidth = this.$element.hasClass('width')
        return hasWidth ? 'width' : 'height'
    }

    , show: function () {
        var dimension
            , scroll
            , actives
            , hasData

        if (this.transitioning || this.$element.hasClass('in')) return

        dimension = this.dimension()
        scroll = $.camelCase(['scroll', dimension].join('-'))
        actives = this.$parent && this.$parent.find('> .accordion-group > .in')

        if (actives && actives.length) {
            hasData = actives.data('collapse')
            if (hasData && hasData.transitioning) return
            actives.collapse('hide')
            hasData || actives.data('collapse', null)
        }

        this.$element[dimension](0)
        this.transition('addClass', $.Event('show'), 'shown')
        $.support.transition && this.$element[dimension](this.$element[0][scroll])
    }

    , hide: function () {
        var dimension
        if (this.transitioning || !this.$element.hasClass('in')) return
        dimension = this.dimension()
        this.reset(this.$element[dimension]())
        this.transition('removeClass', $.Event('hide'), 'hidden')
        this.$element[dimension](0)
    }

    , reset: function (size) {
        var dimension = this.dimension()

        this.$element
            .removeClass('collapse')
            [dimension](size || 'auto')
            [0].offsetWidth

        this.$element[size !== null ? 'addClass' : 'removeClass']('collapse')

        return this
    }

    , transition: function (method, startEvent, completeEvent) {
        var that = this
            , complete = function () {
                if (startEvent.type == 'show') that.reset()
                that.transitioning = 0
                that.$element.trigger(completeEvent)
            }

        this.$element.trigger(startEvent)

        if (startEvent.isDefaultPrevented()) return

        this.transitioning = 1

        this.$element[method]('in')

        $.support.transition && this.$element.hasClass('collapse') ?
            this.$element.one($.support.transition.end, complete) :
            complete()
    }

    , toggle: function () {
        this[this.$element.hasClass('in') ? 'hide' : 'show']()
    }

}


/* COLLAPSE101 PLUGIN DEFINITION
 * ========================== */

var old = $.fn.collapse101

$.fn.collapse101 = function (option) {
    return this.each(function () {
        var $this = $(this)
            , data = $this.data('collapse')
            , options = $.extend({}, $.fn.collapse101.defaults, $this.data(), typeof option == 'object' && option)
        if (!data) $this.data('collapse', (data = new Collapse101(this, options)))
        if (typeof option == 'string') data[option]()
    })
}

$.fn.collapse101.defaults = {
    toggle: false
}

$.fn.collapse101.Constructor = Collapse101


/* COLLAPSE101 NO CONFLICT
 * ==================== */

$.fn.collapse101.noConflict = function () {
    $.fn.collapse101 = old
    return this
}


/* COLLAPSE101 DATA-API
 * ================= */

$(document).on('click.collapse101.data-api', '[data-toggle=collapse101]', function (e) {

    // check if we're on desktop...if so, eat the event and exit
    var isMobileBreakpoint = window.SPDUtils.isMobileBreakpoint();
    if (!isMobileBreakpoint) {
        e.stopImmediatePropagation();
        e.preventDefault();
        return;
    }

    var $this = $(this), href
        , target = $this.attr('data-target')
            || e.preventDefault()
            || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '') //strip for ie7
        , option = $(target).data('collapse') ? 'toggle' : $this.data()
    $this[$(target).hasClass('in') ? 'addClass' : 'removeClass']('collapsed')
    $(target).collapse(option)
});