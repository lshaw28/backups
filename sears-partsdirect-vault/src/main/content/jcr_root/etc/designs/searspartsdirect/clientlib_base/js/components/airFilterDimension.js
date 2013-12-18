/*global window:true, $:true, Class:true, mainSitePath:true */
var airFilterDimension = Class.extend(function() {
    "use strict";

    return {
        /**
        * Initializes airFilterDimension class
        * @param {object} el Target element
        */
        init : function(el) {            
            //var width,height,depth;
            // Parameters
            this.el = el;            
            // Events
            this.bindEvent();            
        },
        /**
        * toggle airFilter section
        * 
        * @return {void}
        */
        bindEvent : function() {          
          var self = this;

          $('#airFilterWidth').on("change", function(){
              self.width = ($(this).val());                  
               if(typeof self.width != 'undefined' && typeof self.height != 'undefined' && typeof self.depth != 'undefined'){
                alert(self.width +" "+ self.height +" "+ self.depth);            
                $('#afLandingImage').hide();
                $('#afBrandImage').show();
                $('#noDataFound').show();
              }
          });
          $('#airFilterHeight').on("change", function(){
              self.height = ($(this).val());                  
               if(typeof self.width != 'undefined' && typeof self.height != 'undefined' && typeof self.depth != 'undefined'){
                alert(self.width +" "+ self.height +" "+ self.depth);            
                $('#afLandingImage').hide();
                $('#afBrandImage').show();
                $('#noDataFound').show();
              }
          });
          $('#airFilterDepth').on("change", function(){
              self.depth = ($(this).val());              
              if(typeof self.width != 'undefined' && typeof self.height != 'undefined' && typeof self.depth != 'undefined'){                         
                $('#afLandingImage').hide();
                $('#afBrandImage').show();
                $('#noDataFound').show();
              }
          });          
          if(typeof self.width != '' && typeof self.height != '' && typeof self.depth != ''){
            //alert(self.width +" "+ self.height +" "+ self.depth);            
            $('#afLandingImage').show();
            $('#afBrandImage').show();
            $('#noDataFound').hide();
           }

        }
    }
}());