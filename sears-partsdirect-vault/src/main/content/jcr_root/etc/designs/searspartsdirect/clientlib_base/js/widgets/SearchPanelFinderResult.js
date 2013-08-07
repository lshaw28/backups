NS('shc.pd.base.widgets').SearchPanelFinderResult = Class.extend(function() {
	var API_PREFIX = $('meta[name="global-mainSitePath"]').attr('content') + '/partsdirect/newModelLocatorAction!',
		PRODUCT_URI = API_PREFIX + 'fetchNewFilterOptionsBasedOnProductType.pd',
		PRODUCT_SWITCHER_URI = API_PREFIX + 'fetchPlateLocationsAndImageForStyle.pd',
		MODEL_BRANDS = API_PREFIX + 'fetchBrandsListBasedOnStyle.pd',
		MODEL_NUMBERS_URI = API_PREFIX + 'fetchSampleModelNumbersBasedOnBrand.pd';

	return {
		/**
		 * @type {String}
		 */
		productType: null,
		/**
		 * @type {String}
		 */
		styleType: null,
		/**
		 * Init widget config
		 * @param {jQuery} parent container element
		 * @returns {undefined}
		 */
		init: function (parent) {
			var _this = this;

			// container reference
			this.parent = parent;
			// UI that handles going from model brand search and product output
			this.$paneManager = $('.search-critera-pane-manager', this.parent);
			// UI that contains the product search
			this.$productOutput = $('.search-critera-output', this.parent);
			// UI that contains both the 3 images and model helper search
			this.$modelHelper = $('.search-critera-helper', this.parent);
			// UI that handles the final dropdown
			this.$modelHelperSearch = $('.search-critera-plate-finder', this.parent);
			// UI that outputs model numbers
			this.$modelNumbersResult = $('.search-critera-plate-output', this.parent);
			// brand selector
			this.$modelHelperBrandSelect = $('select', this.$modelHelperSearch);
			// bind manager anchor links
			this.bindViewManagerEvents();
			// event for brand select
			this.$modelHelperBrandSelect.change(function () {
				var value = $(this).val();

				if (value !== 0) {
					_this.requestModelNumbers($(this).val(), function (html) {
						_this.showModelNumbers(html);
					});
				}
			});
		},
		/**
		 * Set style type
		 * @param {String} styleType
		 * @returns {undefined}
		 */
		setStyleType: function (styleType) {
			this.styleType = styleType;
		},
		/**
		 * Set product type. Anytime the product type changes, all UIs get reset
		 * @param {String} productType
		 * @returns {undefined}
		 */
		setProductType: function (productType) {
			// clear past results
			this.clearProductSearch();
			this.clearModelHelperSearch();
			this.$paneManager.hide();

			// unset style type
			this.setStyleType(null);

			// set product
			this.productType = productType;
		},
		/**
		 * Request product data
		 * @param {String} product
		 * @param {String} style optional
		 * @returns {undefined}
		 */
		requestProductData: function (product, style) {
			var _this = this,
				data = {},
				callback,
				url = PRODUCT_URI;

			this.setProductType(product);
			data.productType = product;

			// this assumes the initial UI has been established
			if (typeof style !== 'undefined') {
				url = PRODUCT_SWITCHER_URI;
				data.selectedStyle = style;

				this.setStyleType(style);

				// set UI response callback
				callback = function (data) {
					_this.clearProductSearch(true);
					_this.$productOutput.prepend(data);

					// ui show/hide dance
					_this.$productOutput.show();
					_this.$paneManager.show();
					_this.$modelHelper.hide();
				};
			} else {
				callback = function (data) {
					_this.clearProductSearch();
					_this.$productOutput.html(data);

					// tango!
					_this.$productOutput.show();
					_this.$paneManager.show();
					_this.$modelHelper.hide();
				};
			}

			$.ajax({
				url: url,
				type: 'GET',
				data: data,
				dataType: 'html'
			}).done(function (data) {
				// a check if you request then close the modal during a request
				if (this.productType !== null) {
					callback(data);
				}
			}).fail(function () {
				_this.logRequestError(url);
			});
		},
		/**
		 * Request for model brands and normalize result
		 * @param {Function} callback
		 * @returns {undefined}
		 */
		requestModelBrands: function (callback) {
			var _this = this;

			$.ajax({
				url: MODEL_BRANDS,
				type: 'GET',
				data: {
					productType: _this.productType,
					selectedStyle: _this.styleType // this does not appear to change the result if null
				},
				dataType: 'html'
			}).done(function (data) {
				var output = [];

				$(data).find('a').each(function () {
					var a = $(this);
					output.push({key: a.text(), value: a.text()});
				});

				callback(output);
			}).fail(function () {
				_this.logRequestError(MODEL_BRANDS);
			});
		},
		/**
		 * @param {String} brand
		 * @param {Function} callback
		 * @returns {undefined}
		 */
		requestModelNumbers: function (brand, callback) {
			var _this = this;

			$.ajax({
				url: MODEL_NUMBERS_URI,
				type: 'GET',
				data: {
					productType: _this.productType,
					selectedStyle: _this.styleType, // this does not appear to change the result if null
					selectedBrand: brand
				},
				dataType: 'html'
			}).done(function (data) {
				callback(data);
			}).fail(function () {
				_this.logRequestError(MODEL_NUMBERS_URI);
			});
		},
		/**
		 * Log request error
		 * @param {String} msg
		 * @returns {undefined}
		 */
		logRequestError: function (msg) {
			console.error('Request failed on [GET] ' + msg);
		},
		/**
		 * Clears model helper search
		 * @returns {undefined}
		 */
		clearModelHelperSearch: function () {
			this.$modelHelperBrandSelect.remove('option');
			this.$modelNumbersResult.empty();
			this.$modelHelperSearch.hide();
		},
		/**
		 * @param {Boolean} partial
		 * @return {undefined}
		 */
		clearProductSearch: function (partial) {
			if (typeof partial !== 'undefined' && partial === true) {
				$('.CMNP_left, .CMNP_center', this.parent).remove();
			} else {
				$('[class*="CMNP"]', this.parent).remove();
			}
		},
		/**
		 * Sets options on model brand search
		 * @param {Array} options
		 * @returns {undefined}
		 */
		setModelHelperSearch: function (options) {
			var i;

			this.$modelHelperBrandSelect.empty('option');

			// add select
			options.unshift({
				key: 'Select',
				value: 0
			});

			// append new items
			for (i = 0; i < options.length; ++i) {
				this.$modelHelperBrandSelect.append('<option value="' + options[i].value + '">' + options[i].key + '</option>');
			}

			// reset selected index
			this.$modelHelperBrandSelect[0].selectedIndex = 0;
		},
		/**
		 * Shows model numbers HTML
		 * @param {String} html
		 * @returns {undefined}
		 */
		showModelNumbers: function (html) {
			this.$modelNumbersResult.html(html);
		},
		/**
		 * Binds model and help panes triggers
		 * @returns {undefined}
		 */
		bindViewManagerEvents: function () {
			var _this = this;

			// @TODO use class names
			$('div:first-child a', this.$paneManager).click(function (e) {
				e.preventDefault();

				$('a', this.$paneManager).removeClass('active');
				$(this).addClass('active');

				// show the helper search dropdown
				_this.$modelHelper.show();
				_this.$modelHelperSearch.show();
				_this.requestModelBrands(function (data) {
					_this.setModelHelperSearch(data);
				});

				_this.$productOutput.hide();
			});

			// @TODO use class names
			$('div:last-child a', this.$paneManager).click(function (e) {
				e.preventDefault();

				$('a', this.$paneManager).removeClass('active');
				$(this).addClass('active');

				// clear model search
				_this.clearModelHelperSearch();

				// open result
				_this.$productOutput.show();
				_this.$modelHelperSearch.hide();
				_this.$modelHelper.hide();
			});
		}
	};
}());