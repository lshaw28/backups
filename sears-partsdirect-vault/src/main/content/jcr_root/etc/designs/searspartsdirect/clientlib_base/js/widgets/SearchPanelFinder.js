NS('shc.pd.base.widgets').SearchPanelFinder = (function () {
	/**
	 * @type {Object}{String} Enum
	 */
	var VisibilityState = {
		Close: 0,
		Open: 1
	},
	/**
	 * @type {String}
	 */
	OPEN_STATE_CLASSNAME = 'is-open',
	/**
	 * @type {Number}
	 */
	ANIMATION_DURATION = 500;

	return {
		/**
		 * Init widget config
		 * @param {jQuery} parent
		 * @returns {undefined}
		 */
		init: function (parent) {
			var i,
				products = this.getProductTypeSelection(),
				item,
				_this = this;

			this.parent = parent;
			this.wrapper = $('.modelFinderWrapper');
			this.visibilityState = VisibilityState.Close;
			this.productTypeSelect = $('.modelFinderProductSelect select', parent);
			this.results = new shc.pd.base.widgets.SearchPanelFinderResult(this.parent);

			// Set initial states
			$('.modelFinderClose').show();
			$('.modelFinderOpen').hide();

			// append selection node
			this.productTypeSelect.append($('<option value="0">Select</option>'));

			for (i = 0; i < products.length; ++i) {
				// set option node
				item = $('<option>').
					attr('value', products[i].value).
					text(products[i].name);

				// append node
				this.productTypeSelect.append(item);
			}

			// bind open/close triggers
			this.bindTriggers();

			// bind dropdown change event
			this.productTypeSelect.change(function () {
				var value = $(this).val();

				if (value !== 0) {
					_this.results.requestProductData(value);
				}
			});

			// get wrapper height
			this.wrapper.height('auto');
			this.wrapperHeight = this.wrapper.height();

			// set back height to 0, this flash shouldn't be visible on browsers
			this.wrapper.height(0);
		},
		/**
		 * Produce product type selections
		 * @returns {Array}
		 */
		getProductTypeSelection: function () {
			return [
				{name: 'Cooktops', value: 'cooktop'},
				{name: 'Dishwashers', value: 'dishwasher'},
				{name: 'Dryers', value: 'dryer'},
				{name: 'Freezers', value: 'freezer'},
				{name: 'Microwaves', value: 'microwave'},
				{name: 'Ovens', value: 'wall oven'},
				{name: 'Ranges', value: 'range'},
				{name: 'Refrigerators', value: 'refrigerator'},
				{name: 'Washers', value: 'washer'}
			];
		},
		/**
		 * Opens search panel (not using slideDown/slideUp for border purposes)
		 * @returns {undefined}
		 */
		open: function () {
			var _this = this;

			this.visibilityState = VisibilityState.Open;
			this.parent.addClass(OPEN_STATE_CLASSNAME);

			$('.modelFinderClose').hide();
			$('.modelFinderOpen').show();
			$('.modelFinderHelper').show();

			// animate
			// @TODO remove hard coded padding properties
			this.wrapper.stop(true).animate({height: _this.wrapperHeight, paddingTop: 10}, ANIMATION_DURATION, function () {
				$(this).height('auto');
			});
		},
		/**
		 * Closes search panel
		 * @returns {undefined}
		 */
		close: function () {
			var _this = this;

			this.visibilityState = VisibilityState.Close;

			$('.modelFinderClose').show();
			$('.modelFinderOpen').hide();

			// animate
			this.wrapper.stop(true).animate({height: 0, paddingTop: 0, paddingBottom: 0}, ANIMATION_DURATION / 1.5, function () {
				_this.parent.removeClass(OPEN_STATE_CLASSNAME);
				_this.results.setProductType(null);
				_this.productTypeSelect[0].selectedIndex = 0;
			});
		},
		/**
		 * Bind open/close triggers
		 * @returns {undefined}
		 */
		bindTriggers: function () {
			var _this = this;

			// event handler for open/close triggers
			$('.searchPanelFinder_js').click(function (e) {
				// determine action based on current visibiltiy state
				if (_this.visibilityState === VisibilityState.Close) {
					// open when closed
					_this.open();
				} else {
					// close when opened
					_this.close();
				}
			});
		}
	};
}());