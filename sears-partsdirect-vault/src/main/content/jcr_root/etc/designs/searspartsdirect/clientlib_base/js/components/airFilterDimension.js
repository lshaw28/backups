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
            this.width = this.height = this.depth = false;

        },
        /**
        * toggle airFilter section
        * 
        * @return {void}
        */

        // api call and setters need to be in a model
        setWidth : function( width ){
            this.width = width || false;
            return this;
        },
        setHeight : function( height ){
            this.height = height || false;
            return this;
        },
        setDepth : function( depth ) {
            this.depth = depth || false;

            return this;
        },

        resultsFromApi : function(callback){
            var self = this;
            $.ajax({
                url : 'http://partsapivip.qa.ch3.s.com/pd-services/v1/air-filters/list',
                data :{
                    w : this.width,
                    h : this.height,
                    d : this.depth
                },
                context : this,
                success : function( data ){
                   if(typeof callback === "function"){
                       callback.call( self, data );
                   }
                }
            });
        },

        getResults : function(){
            if(this.width && this.height && this.depth){
                this.resultsFromApi( this.renderResults );
            }else{

            }
        },

        coalesceData:function(subSet){
            var returnSet = {};
            var prototype = function(obj){
                return {
                    "manufacturer" : obj.manufacturer,
                    "subscribable" : obj.subscribable,
                    "quality" : obj.quality,
                    "basePartNumber" : obj.basePartNumber,
                    "mervRating" : obj.mervRating,
                    "inStock" : obj.inStock,
                    "backOrdered" : obj.backOrdered,
                    "packs" : {}
                }
            };
            for( var x in subSet){
                // get possible existing , or setup with new prototype
                returnSet[ subSet[x]['basePartNumber']] = returnSet[subSet[x]['basePartNumber']] || prototype(subSet[x]);
                returnSet[ subSet[x]['basePartNumber'] ]['packs'][subSet[x]['packSize']] = {
                    "price" : subSet[x]['priceForParts'],
                    "partNumber" : subSet[x]['partNumber']
                }
            }
            return returnSet;
        },

        // to view or template

        renderDimensionString : function(){
            return this.width+'x'+this.height+'x'+this.depth
        },

        renderTitle : function(maker, string, merv){
            return maker+' '+this.renderDimensionString()+' '+string+' - MERV '+merv;
        },

        renderResultRow : function(rowData){
            var el = $('<li/>');
            el.html( this.renderTitle(rowData.manufacturer, 'Pleated Air Filter Replacement', rowData.mervRating));

            return el;
        },

        renderResultType : function(resultSet, setGroupSelector){
            if( resultSet ) {
                var frag = [];
                for( var x in resultSet ) {
                    frag.push( this.renderResultRow( resultSet[x] ) );
                }
                $(setGroupSelector).find('.setList').append(frag).end().removeClass('hide');
                $('#noResults').addClass('hide');
            }else{
                $(setGroupSelector).addClass('hide');
            }
        },

        renderResults : function(resultSet){

            $('.initialDiagrams').addClass('hide');

            // show no found and the result set will clear it very quickly
            // less ideal as it forces a repaint
            $('#noResults').removeClass('hide');

            if(typeof resultSet != 'object') return false;
            // else render result sets

            if( resultSet.bestAirFilters && resultSet.bestAirFilters.part ){
                resultSet.bestAirFilters.part = this.coalesceData ( resultSet.bestAirFilters.part );
                this.renderResultType ( resultSet.bestAirFilters.part, '#bestAirfilters' );
            }

            if( resultSet.betterAirFilters && resultSet.betterAirFilters.part ){
                resultSet.betterAirFilters.part = this.coalesceData( resultSet.betterAirFilters.part );
                this.renderResultType ( resultSet.betterAirFilters.part,'#betterAirFilters' );
            }

            if( resultSet.goodAirFilters && resultSet.goodAirFilters.part ) {
                resultSet.goodAirFilters.part = this.coalesceData( resultSet.goodAirFilters.part );
                this.renderResultType ( resultSet.goodAirFilters.part, '#goodAirFilters' );
            }

        },

        // end to view or template
        bindEvent : function() {          
          var self = this;
          $('#airFilterWidth').on("change", function(){
              self.width = ($(this).val());
              self.getResults();
          });
          $('#airFilterHeight').on("change", function(){
              self.height = ($(this).val());                  
              self.getResults();
          });
          $('#airFilterDepth').on("change", function(){
              self.depth = ($(this).val());
              self.getResults();
          });
        }

    }
}());