/*global $:true, Class:true */
var guideNavigation = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes guideNavigation class
		 */
		init: function (el) {
			// Parameters
			this.el = el;
                        this.classOn = "";
                        this.classOff = "";
                        this.breakPoint = "";
                        this.dir = "";
                        this.maxScroll = false;
                        this.callback = "";
                        
		},
                
                /**
		 * Given scroll position of parent window,
                 * applies and removes "sticky" css classes
                 * @param {number} val Required current scrolltop of window
                 * 
		 */
                checkState: function(val) {
                    var self=this,
                        delta;

                    if (self.el !== false) {
                      if (val > self.breakPoint && !self.el.hasClass(self.classOn)) {
                        self.el.removeClass(self.classOff).addClass(self.classOn);
                      } else if (val <= self.breakPoint && !self.el.hasClass(self.classOff)) {
                        self.el.removeClass(self.classOn).addClass(self.classOff);
                      }
                      /*
                      if (typeof self.callback === 'function') {
                        self.callback();
                      }*/
                    }
                },
                
                /**
		 * returns bool value for set property
                 * @return {bool}
		 */
                isset: function () {
                    var self=this;
                    if (self.el !== false) {
                        return true;
                    } else {
                        return false;
                    }
                },
                
                /**
		 * sets max scrolling distance for 
                 * sticky nav functionality.
                 * @param {int} max Optional maxscroll value
		 */
                maxScroll: function (max) {
                    var self=this;
                    
                    self.maxScroll = max !== null ? maxScroll : false;
                },
                        
                 /**
		 * sets breakpoint for
                 * sticky nav functionality.
                 * @param {string} breakpoint Optional css class name for sticky nav off
		 */
                setBreakPoint: function (breakpoint) {
                    var self = this;
                    
                    self.breakPoint = breakpoint !== null ? breakpoint : false;
                    if (self.breakPoint === false && self.el !== false) {
                      self.breakPoint = self.el.offset()['top'];
                    }
                    
                    //self.breakPoint = self.el.offset()['top'];
                },
                
                /**
		 * sets direction for
                 * sticky nav functionality.
                 * NOTE: currently not used.
                 * @param {string} dir Optional direction of scrolling
		 */
                setDir: function (dir) {
                    var self = this;
                    self.dir = dir !== null ? dir : 'top';
                },
                
                 /**
		 * specifies an optional callback fn
                 * @param {string} callback Optional function to call on window scroll
		 */
                setCallback: function (callback) {
                    var self = this;
                    if (callback === null) {
                        callback = false;
                    }
                    if (callback !== false && typeof callback === "function") {
                        self.callback = callback;
                    }
                },
                
                /**
		 * sets css class names to activate/deactivate
                 * sticky nav functionality.
                 * @param {string} classOn Optional css class name for sticky nav on
                 * @param {string} classOff Optional css class name for sticky nav off
		 */
                setClassToggles: function (classOn, classOff) {
                    var self = this;
                    self.classOn = classOn !== null ? classOn : 'active';
                    self.classOff = classOff !== null ? classOff : 'inactive';
                }
	};
}());