/**
 * Created by dmcgov0 on 1/2/14.
 */
/*global window:true, $:true, Class:true */
var responsiveFilterDropdown = Class.extend(function () {
    "use strict";

    return {
        /**
         * Initializes responsiveDropdown class
         * @param {object} el Target element
         */
        init: function (el, index) {
            // Parameters
            this.el = $(el);
            this.zIndex = 1000 * (5-Number(index));
            console.log('z-index: '+this.zIndex);
            // Elements
            this.buttonGroup = $('<div />');
            this.button = $('<div />');
            this.dropdownItems = $('<ul />');
            // Properties
            this.buttonClass = 'new-btn-select';
            this.buttonContent = 'Select';
            this.groupClass = '';
            this.dimAbbrev = '';
            this.hiddenField = null;
            this.link = false;
            this.navigate = false;
            this.display = false;
            this.guid = window.SPDUtils.getGUID();
            this.unitString = " in.";
            // Retrieve data
            this.setProperties();
            // Render
            this.render();
            this.bindEvent();

        },
        /**
         * Retrieves data from attributes
         * @return {void}
         */
        setProperties: function () {
            var self = this,
                su = window.SPDUtils;

            // Kill multiple selections
            self.el.removeAttr('multiple');
            // Set up GUID for iOS detection
            self.el.data('guid', self.guid);
            window['rD' + self.guid];
            // Set button class
            if (su.validString(self.el.data('buttonclass')) !== '') {
                self.buttonClass = self.el.data('buttonclass');
            }
            // Set button content
            if (su.validString(self.el.data('buttoncontent')) !== '') {
                self.buttonContent = self.el.data('buttoncontent');
                // Set dimension abbreviation
                self.dimAbbrev = self.buttonContent.slice(0,1).toLowerCase();
            }
            // Set group class
            if (su.validString(self.el.data('groupclass')) !== '') {
                self.groupClass = self.el.data('groupclass');
            }

            // Set optional hidden field to update
            if (self.el.data('hiddenfield')) {
                self.hiddenfield = $(self.el.data('hiddenfield'));
            }
            // Update display text on selection
            self.display = su.validBoolean(self.el.data('display'));
            // Enable selection hyperlink
            self.link = su.validBoolean(self.el.data('link'));
            // Enable selection navigation
            self.navigate = su.validBoolean(self.el.data('navigate'));
        },
        /**
         * Render the Bootstrap-style dropdown for desktop
         * @return {void}
         */
        render: function () {
            var self = this,
                su = window.SPDUtils,
                i = 0;

            // Generate the Bootstrap-style dropdown
            // Setup group
            self.buttonGroup.addClass('responsiveDropdown');
            self.buttonGroup.addClass(self.groupClass);
            // Setup button
            self.button.html(self.buttonContent +  '<i class="icon-chevron-sign-down">&nbsp;</i>');
            self.button.addClass(self.buttonClass);
            self.button.bind('click', function (e) {

                if(e.stopPropagation) {
                    e.stopPropagation();
                } else {
                    e.cancelBubble=true;
                }
                self.handleButton();
            });
            self.buttonGroup.append(self.button);
            // Setup dropdown items
            self.renderItems();
            self.buttonGroup.append(self.dropdownItems);
            // Hide the select element
            self.el.addClass('responsiveDropdownHidden');
            self.buttonGroup.insertBefore(self.el);
        },
        /**
         * Render Bootstrap dropdown items
         * @return {void}
         */
        renderItems: function () {
            var self = this,
                su = window.SPDUtils;

            $('option', self.el).each(function () {
                var val = $(this).attr('value');

                if (su.validString(val) !== '') {
                    $(this).attr('data-value', val);
                    self.renderItem($(this));
                }
            });
        },
        /**
         * Render a Bootstrap dropdown item
         * @param {object} option jQuery option element
         * @return {void}
         */
        renderItem: function (option) {
            var self = this,
                su = window.SPDUtils,
                text = su.validString(option.text()),
                value = su.validString(option.attr('value')),
                li = $('<li />');

            li.attr('data-value', value);
            li.html(text);

            li.bind('click', function (e) {
                self.dropdownItems.toggleClass('active');
            });
            self.bindItem(li);
            self.dropdownItems.append(li);
        },
        /**
         * Bind item event
         * @param {object} el jQuery element
         * @return {void}
         */
        bindItem: function (el) {
            var self = this;

            el.bind('click', function (e) {
                if(e.stopPropagation) {
                    e.stopPropagation();
                } else {
                    e.cancelBubble=true;
                }
                self.selectValue($(this).data('value'), $(this).text());
            });
        },
        /**
         * Handle button
         * @return {void}
         */
        handleButton: function () {
            var self = this,
                isMobile = window.SPDUtils.isMobileBreakpoint(),
                isMobileBrowser = window.SPDUtils.isMobileBrowser();

            if (isMobile && isMobileBrowser) {
                self.el.focus();
            } else if (isMobile) {
                // adjust z-index
                self.buttonGroup.css({'z-index' : self.zIndex});
                //self.dropdownItems.css({'z-index' : self.zIndex-1});
                self.dropdownItems.toggleClass('active');
                setTimeout(function() {
                    self.dropDownClickOutside();
                }, 500);
            } else {
                self.dropdownItems.toggleClass('active');
                setTimeout(function() {
                    self.dropDownClickOutside();
                }, 500);

            }

        },

        dropDownClickOutside: function() {
            var self = this;
            // only attach event handler if
            // the dropdown is currently open

                $( "body" ).one( "click", function(e) {
                    // if open, close the dropdown
                    if (self.dropdownItems.hasClass('active')) {
                        self.dropdownItems.removeClass('active');
                    }
                });


        },
        /**
         * Make a selection
         * @param {object} val Selected value
         * * @param {object} text Selected text
         * @return {void}
         */
        selectValue: function (val, text) {
            var self = this,
                isMobile = window.SPDUtils.isMobileBrowser(),
                valStripped = '',
                scrollPos = 0,
                targetEl = null,
                buttonTxt = '';

            val = val.toString();
            valStripped = val.replace('#');
            buttonTxt = text+self.unitString+ ' ('+self.dimAbbrev+')';

            // Make sure the anchor exists
            try {
                targetEl = $('a[name="' + valStripped + '"]');
                scrollPos = targetEl.offset().top;
            } catch (e) {
            }
            // Navigate
            if (self.navigate === true || val.indexOf('#') > -1) {
                window.scrollTo(0, parseInt(scrollPos - self.button[0].offsetHeight - 20, 10));
            }
            // Hyperlink
            if (self.link === true) {
                document.location.href = val;
            }
            // Update the Bootstrap dropdown items
            $('li', self.dropdownItems).removeClass('selected');
            $('li[data-value="' + val + '"]', self.dropdownItems).addClass('selected');
            // Update the select element
            $('option', self.el).attr('selected', false);
            $('option[data-value="' + val + '"]', self.el).attr('selected', 'selected');
            // Fire on change event on desktop
            if (isMobile === false) {
                self.el.change();
            }
            // Update the optional hidden field
            if (self.hiddenField !== null) {
                self.hiddenField.attr('value', val);
            }
            // Display text
            if (self.display === true && buttonTxt !== '') {
                self.button.html(buttonTxt +  '<i class="icon-chevron-sign-down">&nbsp;</i>');
            }
            // Close the dropdown
            self.dropdownItems.removeClass('active');
            // update the button styling now that a value has been selected
            self.button.addClass('filterValSelected');
        },
        bindEvent: function () {
            var self = this;

            self.el.bind('blur', function (e) {
                var selection = e.currentTarget,
                    val = $(selection.options[selection.selectedIndex]).attr('value'),
                    text = $(selection.options[selection.selectedIndex]).text();

                self.selectValue(val, text);
            });
        }
    };
}());