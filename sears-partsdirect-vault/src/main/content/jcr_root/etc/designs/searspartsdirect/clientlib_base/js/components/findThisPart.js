/*global window:true, $:true, Class:true */
var findThisPart = Class.extend(function () {
    "use strict";

    return {
        /**
         * Initializes category101 class
         * @param {object} el Target element
         */
        init: function (el) {
            // Parameters
            this.el = $(el);
            // Properties
            this.uniqueID = window.SPDUtils.getGUID();
            this.parentID = "parent_"+this.uniqueID;
            // set uniqueIDs on Accordion;
            this.setAccordionIDs();

        },

        /**
         * sets unique ids in case of multiple
         * find this part components on page
         * @return {void}
         */
        setAccordionIDs: function () {
           var self = this;

            $('div.accordion', self.el).attr('id', self.parentID);
            $('div.accordion-body', self.el).attr('id', self.uniqueID);
            $('a.accordion-toggle', self.el).attr('data-parent', "#"+self.parentID);
            $('a.accordion-toggle', self.el).attr('href', "#"+self.uniqueID);
        }
    }
}());