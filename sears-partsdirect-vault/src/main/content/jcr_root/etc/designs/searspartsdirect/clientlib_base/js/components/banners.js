/*global window:true, $:true, Class:true, mainSitePath:true */
var banners = Class.extend(function() {
    "use strict";
    return {        
        init : function(el) { 
            this.el = el; 
       		this.template = Handlebars.compile( $('#js_filterBannerTemplate').html() );
            this.bindEvent(); 
        },
        renderFilterData : function() {
            var el = $('#bannerContainer');
            var airFilterData ={ 
                pageUrl: "/content/searspartsdirect/en/replacement-parts/hvac-air-filters/dimensions.html",
                bannerHeading: "Air filters when you want them",
                filterImageUrl: "/etc/designs/searspartsdirect/clientlib_base/img/banners/airFilterBanner.png"
            };
            var waterFilterData ={ 
                pageUrl: "/content/searspartsdirect/en/replacement-parts/hvac-air-filters/dimensions.html",
                bannerHeading: "Water filters when you want them",
                filterImageUrl: "/etc/designs/searspartsdirect/clientlib_base/img/banners/waterFilterBanner.png"
            };
			el.html( this.template( airFilterData ) );
        },
        bindEvent : function() {
            var self = this;
            self.renderFilterData(); 
        }
    }
}());