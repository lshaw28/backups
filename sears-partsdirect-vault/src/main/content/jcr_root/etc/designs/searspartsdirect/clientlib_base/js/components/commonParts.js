/*global window:true, $:true, Class:true */
var commonParts = Class.extend(function () {
    "use strict";

    return {
        /**
         * Initializes commonParts class
         * @param {object} el Target element
         */
        init: function (el) {
            // Parameters
            this.el = $(el);
            // default accordions to closed
            this.initAccordion();

        },

        /**
         * removes the class 'in' from the accordion-body
         * to ensure that the accordion defaults to closed.
         * @return {void}
         */
        initAccordion: function () {
            var self = this;

           // $('div.accordion-body', self.el).each().removeClass('in');
            $('div.accordion-body', self.el).each( function() {
                $(this).removeClass('in');
            })
        }
    }
}());