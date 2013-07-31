NS('shc.pd.base.widgets').SearchPanelFinder = (function () {
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
		}
	};
}());