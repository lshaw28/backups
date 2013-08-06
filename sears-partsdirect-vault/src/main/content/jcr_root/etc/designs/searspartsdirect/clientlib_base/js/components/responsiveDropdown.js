/*global window:true, $:true, Class:true */
var responsiveDropdown = Class.extend(function () {
	"use strict";

	return {
		/**
		 * Initializes responsiveDropdown class
		 * @param {object} el Target element
		 */
		init: function (el) {
			// Parameters
			this.el = $(el);
			// Elements
			this.buttonGroup = $('<div />');
			this.button = $('<div />');
			this.dropdownItems = $('<ul />');
			// Properties
			this.buttonClass = 'new-btn-select';
			this.buttonContent = 'Select';
			this.groupClass = '';
			this.hiddenField = null;
			this.link = false;
			this.navigate = false;
			// Retrieve data
			this.setProperties();
			// Render
			this.render();
		},
		/**
		 * Retrieves data from attributes
		 * @return {void}
		 */
		setProperties: function () {
			var self = this,
				su = window.SPDUtils;

			// Set button class
			if (su.validString(self.el.data('buttonclass')) !== '') {
				self.buttonClass = self.el.data('buttonclass');
			}
			// Set button content
			if (su.validString(self.el.data('buttoncontent')) !== '') {
				self.buttonContent = self.el.data('buttoncontent');
			}
			// Set group class
			if (su.validString(self.el.data('groupclass')) !== '') {
				self.groupClass = self.el.data('groupclass');
			}
			// Set optional hidden field to update
			if (self.el.data('hiddenfield')) {
				self.hiddenfield = $(self.el.data('hiddenfield'));
			}
			// Enable selection hyperlink
			if (su.validString(self.el.data('link')) !== '') {
				self.link = true;
			}
			// Enable selection navigation
			if (su.validString(self.el.data('navigate')) !== '') {
				self.navigate = true;
			}
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
				e.preventDefault();
				self.handleButton();
			});
			self.buttonGroup.append(self.button);
			// Setup dropdown items
			self.renderItems();
			self.buttonGroup.append(self.dropdownItems);
			// Hide the select element
			self.el.attr('multiple', 'false');
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
				li = $('<li />'),
				a = $('<a />');

			a.html(text);
			a.attr('data-value', value);

			// Make hyperlinks function
			if (value !== '' && value !== '#') {
				a.attr('href', value);
			}
			a.bind('click', function (e) {
				self.dropdownItems.toggleClass('active');
			});
			self.bindItem(a);
			li.append(a);
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
				self.selectValue($(this).data('value'));
			});
		},
		/**
		 * Handle button
		 * @return {void}
		 */
		handleButton: function () {
			var self = this;
			self.el.focus();
			self.dropdownItems.toggleClass('active');
		},
		/**
		 * Make a selection
		 * @param {object} val Selected value
		 * @param {boolean} sel Optional boolean to denote that the select made the call
		 * @return {void}
		 */
		selectValue: function (val, sel) {
			var self = this,
				valStripped = val.replace('#', ''),
				scrollPos = $('a[name="' + valStripped + '"]')[0].offsetTop;

			// Update the Bootstrap dropdown items
			$('li', self.dropdownItems).removeClass('selected');
			$('li[data-value="' + val + '"]', self.dropdownItems).addClass('selected');
			// Update the select element
			$('option', self.el).attr('selected', false);
			$('option[data-value="' + val + '"]', self.el).attr('selected', 'selected');
			// Update the optional hidden field
			if (self.hiddenField !== null) {
				self.hiddenField.attr('value', val);
			}
			// Hyperlink
			if (self.link === true) {
				document.location.href = val;
			}
			// Navigate
			if (self.navigate === true) {
				window.scrollTo(scrollPos - self.button.height());
			}
			// Close the dropdown
			self.dropdownItems.removeClass('active');
		},
		bindEvent: function () {
			var self = this;

			self.el.one('blur change', function () {
				var val = $('option:selected', self.el).attr('value');
				self.selectValue(val, true);
			}).bind('focus', function () {
			});
		}
	};
}());