/*global window:true, $:true, Class:true, mainSitePath:true */

var airFilterDimension = Class.extend(function() {
	"use strict";

	return {
		/**
		* Initializes airFilterDimension class
		* @param {object} el Target element
		*/
		init : function(el) {
			if( $('#js_AirFilterDimensionSelection').length != 1 ) return false;
			//var width,height,depth;
			// Parameters
			this.el = el;
			// Events
			this.bindEvent();
			this.width = this.height = this.depth = false;
			this.template = Handlebars.compile( $('#js_airFilterResultTemplate').html() );
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

		resultsFromApi : function(ajaxOpts){
			var self = this;
			var ajaxObj = {
				url : apiPath + 'air-filters/list',
				data :{
					w : this.width,
					h : this.height,
					d : this.depth
				},
				dataType : 'json',
				context : this,
				success : function( data ){

					if(typeof callback === "function"){
						callback.call( self, data );
					}
				}
			};
			ajaxObj = $.extend(ajaxObj, ajaxOpts);
			$.ajax(ajaxObj);
		},

		getResults : function(){
			if(this.width && this.height && this.depth){
				$('#goodAirFilters,#betterAirFilters,#bestAirFilters').find('.setList').empty();
				this.resultsFromApi( {success: this.renderResults, error : this.showErrorState} );
			}else{

			}
		},

		showErrorState : function(){
			// hide stuff
			$('.initialDiagrams').addClass('hide');
			$('#afLandingMobileImage').css('display', 'none');
			$('.airfilterDimensionSection').css("border", "none" );
			$('.toolTip').addClass('hide');
			// show "not found" msg
			$('.filterResults').addClass('hide');
			$('#noAirFilters').removeClass('hide');
			SPDUtils.trackEvent({event: 'searchFail', values: {searchTerm: $('#airFilterWidth').val() + 'x' + $('#airFilterHeight').val() + 'x' + $('#airFilterDepth').val(), searchType: 'Air Filter Dimensions', searchTotal: '0', resultType: 'Air Filter 0 Results'}, componentPath: $('#js_AirFilterDimensionSelection').attr('data-component')}, 'Air_Filter_Dimensions_#templateName');
		},

		coalesceData:function(subSet){
			var returnSet = {};
			var prototype = function(obj){
				return {
					"manufacturer" : obj.manufacturer,
					"subscribable" : obj.subscribable,
					"quality" : obj.quality,
					"basePartNumber" : obj.basePartNumber,
					"partDivId" : obj.partDivId,
					"partPls" : obj.partPls,
					"mervRating" : obj.mervRating,
					"inStock" : obj.inStock,
					"backOrdered" : obj.backOrdered,
					"imageUrl" : obj.imageUrl,
					"packs" : []
				}
			};
			for( var x in subSet){

				// get possible existing , or setup with new prototype
				returnSet[ subSet[x]['basePartNumber']] = returnSet[subSet[x]['basePartNumber']] || prototype(subSet[x]);
				returnSet[ subSet[x]['basePartNumber'] ]['packs'].push( {
					"size" : subSet[x]['packSize'],
					"price" : subSet[x]['priceForParts'],
					"partNumber" : subSet[x]['partNumber']
				});
			}
			return returnSet;
		},

		// to view or template

		renderDimensionString : function(){
			return this.width+' x '+this.height+' x'
		},

		renderTitle : function(maker, string, merv){
			return maker+' '+this.renderDimensionString()+' '+string+' - MERV '+merv;
		},

		renderURL : function(partNum, partDiv, partPls ) {
			return '/replacement-parts/hvac-air-filters/part-number/' + partNum + '/' + partDiv + '/' + partPls + '.html';
		},

		renderResultRow : function(rowData, lastChild){
			// falsiness rules on rowdata
			if( !rowData.manufacturer || !rowData.mervRating ) return false;

			var el = $('<li/>');

			var tempData = {
				title : this.renderTitle(rowData.manufacturer, 'Pleated Air Filter Replacement', rowData.mervRating),
				imageUrl : (typeof rowData.imageUrl === "string" && rowData.imageUrl.length > 1) ? rowData.imageUrl+'?wid=100&hei=100' : '/etc/designs/searspartsdirect/clientlib_base/img/SPD-Airfilter-NoImageAvailableThumb.png',
				packSizes : rowData.packs.sort(function(a,b){return a.size - b.size}),
				pdpURL : this.renderURL(rowData.packs[0].partNumber,rowData.partDivId,rowData.partPls)
			};

			el.html( this.template( tempData ) );
			if (!lastChild) {
				el.addClass('airFilterItemBottomBorder');
			}

			return el;
		},

		renderResultType : function(resultSet, setGroupSelector){

			if( resultSet ) {
				var frag = [],
					numRows = 0,
					count = 0,
					lastChild = false;

				// first, loop thru to get num of rows
				for (var y in resultSet) {
					numRows++;
				}

				for( var x in resultSet ) {
					count++;
					if (count === numRows) lastChild = true;
					frag.push( this.renderResultRow( resultSet[x], lastChild ) );
				}
				$(setGroupSelector).find('.setList').empty().append(frag).end().removeClass('hide');
				$('#noAirFilters').addClass('hide');
			}else{
				$(setGroupSelector).addClass('hide');

			}
		},

		renderResults : function(resultSet){

			$('.initialDiagrams').addClass('hide');
			$('#afLandingMobileImage').css('display', 'none');
			$('.airfilterDimensionSection').css("border", "none" );
			$('.toolTip').addClass('hide');

			// show no found and the result set will clear it very quickly
			// less ideal as it forces a repaint
			$('#noAirFilters').removeClass('hide');

			if(typeof resultSet != 'object') return false;
			// else render result sets
			if( resultSet.bestAirFilters && resultSet.bestAirFilters.part ){
				resultSet.bestAirFilters.part = this.coalesceData ( resultSet.bestAirFilters.part );
				this.renderResultType ( resultSet.bestAirFilters.part, '#bestAirFilters' );
			}

			if( resultSet.betterAirFilters && resultSet.betterAirFilters.part ){
				resultSet.betterAirFilters.part = this.coalesceData( resultSet.betterAirFilters.part );
				this.renderResultType ( resultSet.betterAirFilters.part,'#betterAirFilters' );
			}

			if( resultSet.goodAirFilters && resultSet.goodAirFilters.part ) {
				resultSet.goodAirFilters.part = this.coalesceData( resultSet.goodAirFilters.part );
				this.renderResultType ( resultSet.goodAirFilters.part, '#goodAirFilters' );
			}
			
			var filterTypes = '';
			if (!$('#bestAirFilters').hasClass('hide')) {
				filterTypes = 'Best';
			}
			if (!$('#betterAirFilters').hasClass('hide')) {
				if (filterTypes == '') {
					filterTypes = 'Better';
				} else {
					filterTypes += ':Better';
				}
			}
			if (!$('#goodAirFilters').hasClass('hide')) {
				if (filterTypes == '') {
					filterTypes = 'Good';
				} else {
					filterTypes += ':Good';
				}
			}
			SPDUtils.trackEvent({event: 'searchSuccess', values: {searchTerm: $('#airFilterWidth').val() + 'x' + $('#airFilterHeight').val() + 'x' + $('#airFilterDepth').val(), searchType: 'Air Filter Dimensions', searchTotal: $('.airFilterSearchResultsItemLeft').length, resultType: filterTypes}, componentPath: $('#js_AirFilterDimensionSelection').attr('data-component')}, 'Air_Filter_Dimensions_#templateName');
		},

		// end to view or template
		bindEvent : function() {
		  var self = this;
		  $('#airFilterWidth').on("change", function(){
			  var w = $(this).val();
			  if(w != self.width){
				self.setWidth( w );
				self.getResults();
			  }
		  });
		  $('#airFilterHeight').on("change", function(){
			  var h = $(this).val();
			  if( h != self.height){
				  self.setHeight( h );
				  self.getResults();
			  }
		  });
		  $('#airFilterDepth').on("change", function(){
			  var d = $(this).val();
			  if(d != self.depth){
				self.setDepth( d );
				self.getResults();
			  }
		  });
		}
	}
}());