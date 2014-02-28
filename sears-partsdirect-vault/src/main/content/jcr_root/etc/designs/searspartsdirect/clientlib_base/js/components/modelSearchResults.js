var modelSearchResults = {

    flag: 0,
    selectedValue: '',
    pageIndex: 0,
    offset: 0,
    modelNumber: '111',
    limit: 25,
    sortType: 'revelence',//sic: that's the way the other guys spelled it.
    brandId: '',
    productType: '',
    flagTypes: {'modelSearch':0, 'changeBrand':3, 'changeProductType':4}

};

modelSearchResults.vent = _.extend({}, Backbone.Events);
/**************************
 *
 * COLLECTIONS
 *
 **************************/
/**
 * Collection for brands select element
 */
modelSearchResults.Brands = Thorax.Collection.extend({
    url: "/bin/searspartsdirect/search/searchservlet",

    parse: function(response) {
        var parsedData = $.parseJSON(response.brand);
        //TODO: insert 'select' as first choice
        return parsedData;

    },

    fetch: function(options) {
        console.log("modelSearchResults.Brands fetch called flag="+modelSearchResults.flag);
        options = options || {};
        options.dataType = "json";
        return Backbone.Collection.prototype.fetch.call(this, options);
    },

    setUrlParams: function(urlParams) {

        this.url +="?modelnumber="+modelSearchResults.modelNumber;
        this.url +="&flag="+modelSearchResults.flag;
    }

});

/**
 * Collection for brands select element
 */
modelSearchResults.Products = Thorax.Collection.extend({
    url: "/bin/searspartsdirect/search/searchservlet",

    parse: function(response) {
        var parsedData = $.parseJSON(response.product);
        //TODO: insert 'select' as first choice
        return parsedData;
    },

    fetch: function(options) {
        console.log("modelSearchResults.Products fetch called flag="+modelSearchResults.flag);
        options = options || {};
        options.dataType = "json";
        return Backbone.Collection.prototype.fetch.call(this, options);
    },

    setUrlParams: function() {

        this.url +="?modelnumber="+modelSearchResults.modelNumber;
        this.url +="&flag="+modelSearchResults.flag;
    }

});

/**
 * The search items returned
 */
modelSearchResults.Items = Thorax.Collection.extend({

    url: "/bin/searspartsdirect/search/searchservlet",

    parse: function(response) {
        var data,
            parseFunc = null,
            parsedData;
        //TODO: based on response, call different parsing functions
        switch (modelSearchResults.flag) {
            case 3:
                // filtered by brand name
                parseFunc = this.parseBrandResults;
                data = response.modelResults;
                break;
            case 4:
                // filtered by product type
                parseFunc = this.parseProductTypeResults;
                data = response.modelResults;
                break;
            case 6:

                data = response.modelResults;
                break;
            default:
                data = response.modelResults;
        }

        if (parseFunc != null) {
            parsedData = parseFunc(data);
        } else {
            parsedData = $.parseJSON(data),
                len = parsedData.length;
            // hack: assign unique id param, since
            // api does not and that confuses backbone
            for (var i=0; i < len; i++) {
                parsedData[i].id = i;
            };
        }



        return parsedData;
    },

    fetch: function(options) {
        console.log("modelSearchResults.Items fetch called. flag="+modelSearchResults.flag);
        options = options || {};
        options.dataType = "json";
        //options.reset = true;
        return Backbone.Collection.prototype.fetch.call(this, options);
    },

    setUrlParams: function() {

        this.url = "/bin/searspartsdirect/search/searchservlet";
        this.url += "?modelnumber="+modelSearchResults.modelNumber;
        this.url += "&offset="+modelSearchResults.offset;
        this.url += "&limit="+modelSearchResults.limit;
        this.url += "&sortType="+modelSearchResults.sortType;
        if (modelSearchResults.productType != '') {
            this.url += "&productType="+modelSearchResults.productType;
        }
        if (modelSearchResults.brandId != '') {
            this.url += "&brand="+modelSearchResults.brandId;
        }
        this.url += "&flag="+modelSearchResults.flag;

    },

    filterByBrandId: function(id) {

        return this.filter(function(item) {
            return item.get('brandId') == id;
        });
    },

    filterByCategoryId: function(id) {
        return this.models.filter(
            function(c) {
                return _.contains(id, c.categoryId);
            }
        )
    },

    parseProductTypeResults: function(data) {
        var parsed;
        
        return parsed;
    },

    parseBrandResults: function(data) {
        var parsed;

        return parsed;
    }

});
/**************************
 *
 * VIEWS
 *
 **************************/

/**
 * View for the Brands select
 * Child view of Filter View
 */
modelSearchResults.BrandsSelectView = new Thorax.View({
    collection: new modelSearchResults.Brands(),
    template: Handlebars.compile($('#brands-template').html()),

    initialize: function() {
        // for testing: set props manually
        modelSearchResults.modelNumber = "111";
        modelSearchResults.flag = "6";
        this.collection.setUrlParams();
        this.collection.fetch();
    },

    events: {
        'change': 'brandChange'
    },

    brandChange: function(event) {
        var data = {value: event.target.value};
        modelSearchResults.vent.trigger('BrandsSelectView.brandChange', data);
    }


});

/**
 * View for the Products select
 * Child view of Filter View
 */
modelSearchResults.ProductsSelectView = new Thorax.View({
    collection: new modelSearchResults.Products(),
    template: Handlebars.compile($('#products-template').html()),

    initialize: function() {
        // for testing: set props manually
        modelSearchResults.modelNumber = "111";
        modelSearchResults.flag = "6";
        this.collection.setUrlParams();
        this.collection.fetch();
    },

    events: {
        'change': 'productTypeChange'
    },

    productTypeChange: function(event) {
        var data = {value: event.target.value};
        modelSearchResults.vent.trigger('ProductsSelectView.productTypeChange', data);
    }
});

/**
 * View that will eventually hold filtering select elements
 *

modelSearchResults.FilterView = new Thorax.View({

    brandDDView: brandDDView,
    productDDView: productDDView

});
 */
/**
 * View that holds the search results
 *
 */
modelSearchResults.SearchResultsView = new Thorax.View({

    collection: new modelSearchResults.Items(),
    template: Handlebars.compile($('#msr-template').html()),

    initialize: function() {
        modelSearchResults.vent.on('BrandsSelectView.brandChange', this.handleBrandChange, this);
        modelSearchResults.vent.on('ProductsSelectView.productTypeChange', this.handleProductTypeChange, this);
        modelSearchResults.vent.on('SearchResultsFooter.pageIndexChange', this.handlePageIndexChange, this);
        modelSearchResults.flag = modelSearchResults.flagTypes.modelSearch;
        this.collection.setUrlParams();
        this.collection.fetch();

    },

    handleBrandChange: function(data) {
       console.log('filter the collection based on the brand selected: '+data.value);
       modelSearchResults.brandId = data.value;
       modelSearchResults.flag = modelSearchResults.flagTypes.changeBrand;
       this.collection.setUrlParams();
       this.collection.fetch();
    },

    handleProductTypeChange: function(data) {
        console.log('filter the collection based on the productType selected: '+data.value);
        modelSearchResults.productType = data.value;
        modelSearchResults.flag = modelSearchResults.flagTypes.changeProductType;
        this.collection.setUrlParams();
        this.collection.fetch();
    },

    handlePageIndexChange: function() {
        this.collection.setUrlParams();
        this.collection.fetch();
    }

});

modelSearchResults.SearchResultsFooterView = new Thorax.View({

    template: Handlebars.compile($('#footer-template').html()),

    initialize: function() {

    },

    events: {
        'click .resultsFooterButtonLeft': 'handlePageDecrement',
        'click .resultsFooterButtonRight': 'handlePageIncrement'
    },

    handlePageDecrement: function() {
        (modelSearchResults.pageIndex > 0) ? modelSearchResults.pageIndex -- : modelSearchResults.pageIndex = 0;
        modelSearchResults.offset = modelSearchResults.pageIndex * 25;
        modelSearchResults.vent.trigger('SearchResultsFooter.pageIndexChange');
    },

    handlePageIncrement: function() {
        modelSearchResults.pageIndex++;
        modelSearchResults.offset = modelSearchResults.pageIndex * 25;
        modelSearchResults.vent.trigger('SearchResultsFooter.pageIndexChange');
    }
});

modelSearchResults.MasterView = new Thorax.View({

});
/**
 * append the views to the page
 *
 */
modelSearchResults.SearchResultsView.appendTo('.modelSearchResultsMain');
modelSearchResults.ProductsSelectView.appendTo('.productType');
modelSearchResults.BrandsSelectView.appendTo('.brand');
modelSearchResults.SearchResultsFooterView.appendTo('#footer');


