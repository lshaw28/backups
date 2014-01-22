/*global window:true, $:true, Class:true, mainSitePath:true */
var mervRatingHelp = Class.extend(function () {
    "use strict";
    
    return {
        /**
        * @class addToCart
        * Creates an instance of an add to cart button
        * Uses AJAX to add item then updates the cart drop down
        *
        * init: On page load events to fire
        * @param {object} el jQuery element to attach to
        * @param {object} qf jQuery element for corresponding quantity field
        */
        init: function (el) {
            // Properties
            this.el = el;

            this.bindEvents();
        },
        /**
        * Bind events to button
        * @return {void}
        */
        bindEvents: function () {
            $('.collapse').on('shown.bs.collapse', function(){
            $(this).parent().find(".icon-chevron-down").removeClass("icon-chevron-down").addClass("icon-chevron-up");
        }).on('hidden.bs.collapse', function(){
            $(this).parent().find(".icon-chevron-up").removeClass("icon-chevron-up").addClass("icon-chevron-down");
        });
        }
    }
}());









