/*! slider v1.0.0 2014-03-02 */
/* global jQuery, $, Utils, Prefixr */

(function(zem, $) {
  'use strict';

  /**
   * Adds css prefixes depending on user's browser
   * @return {Object} CSS prefixed properties
   */
  var Prefixr = (function() {
    var _cssProperties = {
      textShadow: "textShadow",
      borderRadius: "borderRadius",
      transform: "transform",
      transitionDuration: "transitionDuration",
      boxShadow: "boxShadow",
      transition: "transition",
      delay: "transitionDelay"
    };

    var _vendorsArray = ['', 'webkit', 'Webkit', 'moz', 'Moz', 'o', 'ms', 'Ms'],
        _eventsArray = {
          'WebkitTransition': 'webkitTransitionEnd',
          'MozTransition': 'transitionend',
          'transition': 'transitionend'
        };

    (function() {
      var i,
        tempProp,
        vendorsLength = _vendorsArray.length;

      //  looping into css properties object  
      for (var prop in _cssProperties) {
        //  looping into vendor types
        for (i = 0; i <= vendorsLength; ++i) {
          _cssProperties[prop] = null;
          tempProp = prop;
          //  capitalize CSS property
          if (_vendorsArray[i] !== '') {
            tempProp = prop.replace(/(^[a-z]{0,1})([\w])/g, replaceKey);
          }
          //  property found
          if (typeof document.documentElement.style[_vendorsArray[i] + tempProp] !== 'undefined') {
            _cssProperties[prop] = _vendorsArray[i] + tempProp;
            break;
          }
        }
      }

      _cssProperties.transitionend = _eventsArray[_cssProperties.transition];

    }());

    function replaceKey(m, key, value) {
      return key.toString().toUpperCase() + value;
    }

    return _cssProperties;
  }());

  /**
   * Represents a Slider instance
   * @constructor
   * @return {Object} Exposed methods
   */
  zem.Slider = function(selector, options) {

    /**
     * Slider container
     * @type {HTMLElement}
     */
    var element,

      /**
       * Items container (strip)
       * @type {HTMLElement}
       */
      itemsWrapper,

      /**
       * Slider viewport
       * @type {HTMLElement}
       */
      viewport,

      /**
       * Items collection
       * @type {NodeList}
       */
      items,

      /**
       * Total of items
       * @type {Number}
       */
      numItems,

      /**
       * Steps to reach the end of the slider
       * @type {Number}
       */
      numSteps,

      /**
       * Current Item
       * @type {Number}
       */
      index = 0,

      /**
       * Transition size
       * @type {Number}
       */
      size,

      /**
       * Initial drag position
       * @type {Number}
       */
      initialPos = 0,

      /**
       * Previous drag position
       * @type {Number}
       */
      lastPos = 0,

      /**
       * Initial dragging coordinates (x,y)
       */
      initialCoords = {
        x: 0,
        y: 0
      },

      /**
       * Dragging enabled
       * @type {Boolean}
       */
      isDragging = false,

      /**
       * Flag to determine if touch moved
       * @type {Boolean}
       */
      moved = false,

      /**
       * Bool flag to specify if slider is active or not
       */
      active = true,

      /**
       * Default settings
       * @type {Enum}
       */
      SETTINGS = {
        wrapper: '.slider-mask',
        viewport: '.slider-viewport',
        items: 'li',
        slides: 0,
        time: 0.3,
        delta: 50,
        forceTouch: false,
        onRelease: false
      };

    /**
     * @construcs jda.Carousel
     */
    (function() {

      SETTINGS = $.extend(SETTINGS, options);
      
      element = $(selector)[0];

      if ($(selector).hasClass('sliderjs')) {
        return;
      }

      $(selector).addClass('sliderjs');

      itemsWrapper = element.querySelector(SETTINGS.wrapper);

      var tempViewport = $('<div class="slider-viewport"></div>').html(itemsWrapper).appendTo(element);

      viewport = tempViewport[0];

      items = itemsWrapper.querySelectorAll(SETTINGS.items);

      numItems = items.length;
      init();

      on();

    }());

    function init() {
      resizeItems();
      $(window).on('resize', resizeItems);
    }

    /**
     * Add DOM listeners
     * @private
     */
    function addEventListeners() {
      if (SETTINGS.forceTouch) {
        viewport.addEventListener(ZUIEvent.START, startHandler, false);
        viewport.addEventListener(ZUIEvent.MOVE, moveHandler, false);
        viewport.addEventListener(ZUIEvent.END, endHandler, false);
        viewport.addEventListener(ZUIEvent.CLICK, clickHandler, false);
      }

      if (!Utils.touch()) {
        document.body.addEventListener(ZUIEvent.END, releaseDragging, false);
      }
    }

    /**
     * Resize carousel items
     */
    function resizeItems() {
      // If the slider container has a static width (fixed), stop repainting
      if (SETTINGS.single && typeof size !== 'undefined' && size === $(element).width()) {
        return;
      }

      // remove navigation
      removeNavigation();
      
      size = $(element).width();

      // set viewport width
      viewport.style.width = size + 'px';

      // expand items to the container width
      for (var i = 0 ; i < numItems; i++) {
        items[i].style.width = size + 'px';
      }
      
      
      numSteps = numItems;

      // enable event listeners
      addEventListeners();

     index = 0;
      // disable transition
      changeTransition(0);
      // maintain slide position
      goTo(-size * index);
    
      // restart transition time (500ms)
      setTimeout(changeTransition, SETTINGS.time*1000, SETTINGS.time);
    }

    /**
     * Remove event listeners, arrows and pagination buttons
     */
    function removeNavigation() {
     
      if (SETTINGS.forceTouch) {
        // remove drag&drop handlers
        viewport.removeEventListener(ZUIEvent.START, startHandler, false);
        viewport.removeEventListener(ZUIEvent.MOVE, moveHandler, false);
        viewport.removeEventListener(ZUIEvent.END, endHandler, false);
        viewport.removeEventListener(ZUIEvent.CLICK, clickHandler, false);
      }
      
      // fix for desktop dragging
      if (!Utils.touch() && SETTINGS.forceTouch) {
        document.body.removeEventListener(ZUIEvent.END, releaseDragging, false);
      }
    }

    function off() {
      if (active){
        itemsWrapper.style[Prefixr.transition] = 'none';
        itemsWrapper.style[Prefixr.transform] = 'none';
        itemsWrapper.style.width = 'auto';
        active = false;
      }
    }

    function on() {
      active = true;
      itemsWrapper.style.width = (itemsWrapper.querySelectorAll(SETTINGS.items).length * 100) + "%";
    }

    /**
     * Go to next item
     */
    function next() {
      index = (index + 1 < numSteps - 1) ? index + 1 : numSteps - 1;
      
      goTo(-size * index);
    }

    /**
     * Go to prev item
     */
    function prev() {
      index = (index - 1 >= 0) ? index - 1 : 0;
      
      goTo(-size * index);
    }

    /**
     * Go to a selected index
     * @param  {Number} pos - New position
     */
    function goTo(pos) {
      itemsWrapper.style[Prefixr.transform] = 'translate3d(' + pos + 'px, 0, 0)';
    }

    /**
     * Change transition time
     * @param  {Number} time - The transition time
     */
    function changeTransition(time) {
      itemsWrapper.style[Prefixr.transition] = 'all ' + time + 's';
    }

    /**
     * Start dragging
     * @event
     */
    function startHandler(e) {
      e.stopPropagation();
      initialCoords.x =  e.touches ? e.touches[0].pageX : e.clientX;
      initialCoords.y =  e.touches ? e.touches[0].pageY : e.clientY;

      isDragging = true;
      moved = false;
      initialPos = initialCoords.x;
      
      lastPos = initialPos;
    }

    /**
     * Move wrapper
     * @event
     */
    function moveHandler(e) {
      if (!isDragging) {
        return;
      } else {
        var currentDragPos = e.touches ? e.touches[0].pageX : e.clientX,
          currentDragPosY = e.touches ? e.touches[0].pageY : e.clientY,
          isScrolling = Math.abs(currentDragPos - initialCoords.x) < Math.abs(currentDragPosY - initialCoords.y);
        
        // user is scrolling window? (stop slider)
        if (isScrolling) {
          return;
        }

        e.preventDefault();
        e.stopPropagation();
      
        changeTransition(0);
        var pos = Utils.getTranslateCoordinate(itemsWrapper.style[Prefixr.transform], 'x');
          

        goTo(pos - lastPos + currentDragPos);
        lastPos = currentDragPos;
        moved = true;
      }
    }

    /**
     * Stop dragging
     * @event
     */
    function endHandler(e) {

      if (!moved) {
        return;
      }
      e.stopPropagation();
      e.preventDefault();
      
      if (!isDragging) {
        return;
      }
      
      var currentDragPos = e.changedTouches ? e.changedTouches[0].pageX : e.clientX;
      

      changeTransition(SETTINGS.time);

      // move to next item
      if (initialPos - currentDragPos > SETTINGS.delta) {
        next();
        // move to prev item
      } else if (initialPos - currentDragPos < -SETTINGS.delta) {
        prev();
        // go to current item
      } else {
        goTo(-size * index);
      }

      if (typeof SETTINGS.onRelease === 'function'){
        SETTINGS.onRelease();
      }
      isDragging = false;
    }

    function clickHandler(e) {
      if (!moved) {
        return;
      }
      e.preventDefault();
      e.stopPropagation();
      
      isDragging = false;
    }

    /**
     * Stop dragging (body)
     * @event
     */
    function releaseDragging(e) {
      e.preventDefault();
      e.stopPropagation();

      if (!isDragging) {
        return;
      }
      
      changeTransition(SETTINGS.time);
      goTo(-size * index);
    }

    // public methods and properties
    return {
      prev: prev,
      next: next,
      off: off,
      on: on
    };
  };

  var ZUIEvent = ('ontouchstart' in window) ? {
    START: 'touchstart',
    MOVE: 'touchmove',
    END: 'touchend',
    CLICK: 'touchend'
  } : {
    START: 'mousedown',
    MOVE: 'mousemove',
    END: 'mouseup',
    CLICK: 'click'
  };

  var Utils = {
    /**
     * matches a translate3D coordinate (from translate3D CSS3 property)
     * @param value {String} The translate3D property string: 'translate3D(10px,0,-50px)'
     * @param coordinate {String} The coordinate needed: 'x' || 'y' || 'z'
     * @returns {Number}  Gets the selected coordinate value
     */
    getTranslateCoordinate: function(value, coordinate) {
      var coordinateValue = 0,
        arrMatches = value.toString().match(/([0-9\-]+)+(?![3d]\()/gi);

      //  matches all the 3D coordinates (from translate3D CSS3 property)
      if (arrMatches && arrMatches.length) {
        //  Gets the array position: [x, y, z]
        var coordinatePosition = coordinate === 'x' ? 0 : coordinate === 'y' ? 1 : 2;
        coordinateValue = parseFloat(arrMatches[coordinatePosition]);
      }

      return coordinateValue;
    },
    touch: function() {
      return ('ontouchstart' in window);
    }
  };

}(window.zem = window.zem || {}, jQuery || $));


/**
 * handle events invoking directly a method inside the DOM Element
 */
if (!Element.prototype.addEventListener) {
  Element.prototype.addEventListener = function(type, handler, useCapture) {
    if (this.attachEvent) {
      this.attachEvent('on' + type, function(event) {
        event.preventDefault = function() {
          event.returnValue = false;
          return false;
        };

        event.stopPropagation = function() {
          window.event.cancelBubble = true;
          return false;
        };

        event.target = event.srcElement;
        event.currentTarget = event.srcElement;


        handler(event);
      });
    }
    return this;
  };
}

if (!Element.prototype.removeEventListener) {
  Element.prototype.removeEventListener = function(type, handler, useCapture) {
    if (this.detachEvent) {
      this.detachEvent('on' + type, handler);
    }
    return this;
  };
}