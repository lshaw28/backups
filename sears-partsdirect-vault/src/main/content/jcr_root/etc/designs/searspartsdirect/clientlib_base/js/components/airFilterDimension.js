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
                       console.log ( data ) ;
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

        // to view or template


        renderResultRow : function(resultSetData){
            var el = $('li');
            el.html(rowData.basePartNumber);
            console.log('--element--',el);
            return el;
        },

        renderResultType : function(resultSet, setGroupSelector){
            if( resultSet.part.length >= 1) {
                var frag = [];
                for( var x in resultSet.part ) {
                    console.log( '--render row:' , resultSet.part[x] );
                    frag.push( this.renderResultRow( resultSet.part[x] ) );
                }
                console.log( '--to render--', resultSet.part );
                $(setGroupSelector).find('.setList').append(resultSet.part).end().removeClass('hide');
            }else{
                $(setGroupSelector).addClass('hide');
            }
        },

        renderResults : function(resultSet){
            if( resultSet.bestAirFilters != "undefined" ) this.renderResultType ( resultSet.bestAirFilters, '#bestAirfilters' );
            if( resultSet.betterAirFilters != "undefined" ) this.renderResultType ( resultSet.betterAirFilters,'#betterAirFilters' );
            if( resultSet.goodAirFilters != "undefined" ) this.renderResultType ( resultSet.goodAirFilters, '#goodAirFilters' );
        },

        // end to view or template
        bindEvent : function() {          
          var self = this;
          $('#noDataFound').hide();

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