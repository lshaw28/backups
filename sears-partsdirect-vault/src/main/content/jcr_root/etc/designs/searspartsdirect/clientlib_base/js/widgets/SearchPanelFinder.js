NS('shc.pd.base.widgets').SearchPanelFinder = (function () {
	
	/**
	 * @type {Object}{String} Enum
	 */
	var VisibilityState = {
		Close: 0,
		Open: 1
	}
	
	return {
		/**
		 * Init widget config
		 * @param {jQuery} parent
		 * @returns {undefined}
		 */
		init: function (parent) {
			var i,
				products = this.getProductTypeSelection(),
				item;
			
			this.parent = parent;
			this.visibilityState = VisibilityState.Close;
			this.productTypeSelect = $('.product-type-selection select', parent);
			
			// append selection node
			this.productTypeSelect.append($('<option value="">Select</option>'));
			
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
		 * Opens search panel
		 * @returns {undefined}
		 */
		open: function () {
			this.visibilityState = VisibilityState.Open;
			this.parent.stop(true).slideDown(500);
		},
		/**
		 * Closes search panel
		 * @returns {undefined}
		 */
		close: function () {
			this.visibilityState = VisibilityState.Close;
			this.parent.stop(true).slideUp(300);
		},
		/**
		 * Bind open/close triggers
		 * @returns {undefined}
		 */
		bindTriggers: function () {
			var _this = this;
			
			// event handler for open/close triggers
			$('.search-panel-finder-trigger').click(function (e) {
				e.preventDefault();
				
				// determine action based on current visibiltiy state
				if (_this.visibilityState === VisibilityState.Close) {
					// open when closed
					_this.open();
				} else {
					// close when opened
					_this.close();
				}
			});
		},
		/**
		 * Set selection change event which then calls the API for the product model finder
		 * @returns {undefined}
		 */
		bindProductChangeEvent: function () {
			
		}
	};
}());