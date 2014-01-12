/*global $:true, Class:true */
var airFilterPartDetails = Class.extend(function () {
	"use strict";
	var pack4 = {
			number: '',
			div: '',
			pls: '',
			weight: '',
			price: '',
			sub: [false, false, false],
			avail: '',
			syw: 0,
			sDate: '',
			pDate: '',
			eDate: ''
        },
		pack6 = {
			number: '',
			div: '',
			pls: '',
			weight: '',
			price: '',
			sub: [false, false, false],
			avail: '',
			syw: 0,
			sDate: '',
			pDate: '',
			eDate: ''
		},
		pack12 = {
			number: '',
			div: '',
			pls: '',
			weight: '',
			price: '',
			sub: [false, false, false],
			avail: '',
			syw: 0,
			sDate: '',
			pDate: '',
			eDate: ''
		};

	return {
		/**
		 * Initializes guideNavigation class
		 */
        baseHistoryUrl : "/replacement-parts/hvac-air-filters/part-number/",
		init: function () {

           // History.pushState(null,null,'/testUrl');
            this.setFromUrl();
            this.detailsFromApi({
               success: this.renderView
            });

			this.bindEvents();
		},

        setFromUrl : function(){
            var path = window.location.pathname.replace(this.baseHistoryUrl,'').split('/');
            this.setPartNumber(path[0]);
            this.setDivId(path[1]);
            this.setPls(path[2].replace('.html',''));
        },
        setToUrl : function(){
            var path = this.baseHistoryUrl+this.partNumber+'/'+this.divId+'/'+this.pls+'.html';
            History.replaceState(null,'Part Detail',path);
        },
        // model
        setPartNumber : function(partNo){
          this.partNumber = partNo;
          this.currentPack = partNo.slice(partNo.lastIndexOf('-')+1);
          return this;
        },

        setDivId : function(divId){
            this.divId = divId;
            return this;
        },

        setPls : function(pls){
            this.pls = pls;
            return this;
        },

        detailsFromApi : function(ajaxOpts){
            var ajaxObj = {
                type: "GET",
                cache: false,
                dataType: "json",
                url: apiPath + 'air-filters/part-details',
                context:this,
                data: {
                    number: this.partNumber,
                    div: this.divId,
                    pls: this.pls
                }
            }
            ajaxObj = $.extend(ajaxObj, ajaxOpts);
            $.ajax(ajaxObj);
        },

        generateUrl : function(partNum, pls, divId){
            return this.baseHistoryUrl+'/'+partNum+'/'+divId+'/'+pls+'.html';
        },

        // view

        renderView : function(response) {
            var filter = {
                    manufacturer: response[0].manufacturer,
                    desc: response[0].partDesc,
                    number: response[0].basePartNumber,
                    merv: response[0].mervRating,
                    image: response[0].imageUrl,
                    cat: response[0].productGroupDescription,
                    length: response[0].shippingLength,
                    width: response[0].shippingWidth,
                    height: response[0].shippingHeight
                },
                packs = response[0].availablePacks.length;
                this.basePartNumber = response[0].basePartNumber;
            for (var i = 0; i < packs; i++) {
                //Special formatting for price because it's stored as a float
                var stringPrice = (response[0].availablePacks[i].priceForParts * 100).toString();
                stringPrice = stringPrice.substring(0, stringPrice.length - 2) + '.' + stringPrice.substring(stringPrice.length - 2);
                var temp = {
                    number: response[0].availablePacks[i].packPartNumber,
                    div: response[0].availablePacks[i].packPartDivId,
                    pls: response[0].availablePacks[i].packPartPls,
                    weight: response[0].availablePacks[i].shippingWeight,
                    price: response[0].availablePacks[i].priceForParts,
                    sub: [false, false, false],
                    avail: response[0].availablePacks[i].availabilityStatus,
                    syw: 0,
                    sDate: '',
                    pDate: '',
                    eDate: ''
                };
                var periods = response[0].availablePacks[i].subscriptions.length;
                $.grep(response[0].availablePacks[i].subscriptions, function(e) {
                    switch(e.renewalPeriod) {
                        case 3:
                            temp.sub[0] = true;
                            break;
                        case 6:
                            temp.sub[1] = true;
                            break;
                        case 12:
                            temp.sub[2] = true;
                            break;
                        default:
                        //Error
                    }
                });
                switch(response[0].availablePacks[i].packSize) {
                    case 4:
                        pack4 = temp;
                        break;
                    case 6:
                        pack6 = temp;
                        break;
                    case 12:
                        pack12 = temp;
                        break;
                    default:
                    //Error
                }
            }
            switch(response[0].packSize) {
                case 6:
                    $('#fourPack').removeClass('active');
                    $('#sixPack').addClass('active');
                    break;
                case 12:
                    $('#fourPack').removeClass('active');
                    $('#twelvePack').addClass('active');
                    break;
                default:
                //Error
            }
            this.displayFilter(filter);
            this.displayPack(response[0].packSize);
            //Get Shop Your Way points and estimated delivery dates for packs this part has
            if (pack4.number != '') {
                this.getSYW(filter, pack4, 4);
                this.getDates(filter, pack4, 4);
            }
            if (pack6.number != '') {
                this.getSYW(filter, pack6, 6);
                this.getDates(filter, pack6, 6);
            }
            if (pack12.number != '') {
                this.getSYW(filter, pack12, 12);
                this.getDates(filter, pack12, 12);
            }
        },
		/**
		 * Bind events
		 * @return {void}
		 */
		bindEvents: function () {
			var self = this;
			$('#fourPack').on('click', function (e) {
				$('#sixPack').removeClass('active');
				$('#twelvePack').removeClass('active');
				$(this).addClass('active');
				self.displayPack(4);
			});
			$('#sixPack').on('click', function (e) {
				$('#fourPack').removeClass('active');
				$('#twelvePack').removeClass('active');
				$(this).addClass('active');
				self.displayPack(6);
			});
			$('#twelvePack').on('click', function (e) {
				$('#fourPack').removeClass('active');
				$('#sixPack').removeClass('active');
				$(this).addClass('active');
				self.displayPack(12);
			});
			$('#oneTime').on('click', function (e) {
				$('.packBox.wide').removeClass('active');
				$(this).addClass('active');
				$('#addFilterToCart').attr('data-subper', 0);
                this.showGetShipping();
			});
			$('.js_automatic').on('click', function (e) {
				$('.packBox.wide').removeClass('active');
				$(this).addClass('active');
				$('#addFilterToCart').attr('data-subper', $(this).attr('data-subper'));
                self.showHasShipping();
			});

		},
        showGetShipping : function(){
            $('#js_getFreeShipping').removeClass('hide');
            $('#js_hasFreeShipping').addClass('hide');
        },
        showHasShipping : function(){
            $('#js_getFreeShipping').addClass('hide');
            $('#js_hasFreeShipping').removeClass('hide');
        },


		/**
		 * Get Shop Your Way points
		 * @return {void}
		 */
		getSYW: function (filter, pack, size) {
			$.ajax({
				type: "GET",
				cache: false,
				dataType: "json",
				data: {
					partNumber: filter.number + '-' + size,
					divId: pack.div,
					plsNumber: pack.pls
				},
				url: apiPath.replace('v1/','') + 'syw/points',
				success: function(response) {
					if ($('#packNumber').html() == size) {
						$('#swyPoints').html(response.points);
					}
					switch (size) {
						case 4:
							pack4.syw = response.points;
							break;
						case 6:
							pack6.syw = response.points;
							break;
						case 12:
							pack12.syw = response.points;
							break;
						default:
							//Error
					}
				},
				error: function(response) {
					//console.log('fail');
				}
			});
		},
		/**
		 * Get estimated delivery dates
		 * @return {void}
		 */
		getDates: function (filter, pack, size) {
			$.ajax({
				type: "GET",
				cache: false,
				dataType: "json",
				data: {
					partNumber: filter.number + '-' + size,
					divId: pack.div,
					plsNumber: pack.pls
				},
				url: apiPath.replace('v1/','') + 'ead/prioritydates',
				success: function(response) {
					var standard = '',
						priority = '',
						expedited = '';
					
					if (response.standardEAD != undefined) {
						standard = response.standardEAD;
					}
					if (response.priorityEAD != undefined) {
						priority = response.priorityEAD;
					}
					if (response.expeditedEAD != undefined) {
						expedited = response.expeditedEAD;
					}
					if ($('#packNumber').html() == size) {
						//Sets delivery dates and hides if date is missing
						if (standard == '') {
							$('#standardLabel, #standardDelivery').addClass('hidden');
						} else {
							$('#standardLabel, #standardDelivery').removeClass('hidden');
							$('#standardDelivery').html(standard);
						}
						if (expedited == '') {
							$('#expeditedLabel, #expeditedDelivery').addClass('hidden');
						} else {
							$('#expeditedLabel, #expeditedDelivery').removeClass('hidden');
							$('#expeditedDelivery').html(expedited);
						}
						if (priority == '') {
							$('#priorityLabel, #priorityDelivery').addClass('hidden');
						} else {
							$('#priorityLabel, #priorityDelivery').removeClass('hidden');
							$('#priorityDelivery').html(priority);
						}
					}
					
					switch (size) {
						case 4:
							pack4.sDate = standard;
							pack4.pDate = priority;
							pack4.eDate = expedited;
							break;
						case 6:
							pack6.sDate = standard;
							pack6.pDate = priority;
							pack6.eDate = expedited;
							break;
						case 12:
							pack12.sDate = standard;
							pack12.pDate = priority;
							pack12.eDate = expedited;
							break;
						default:
							//Error
					}
				},
				error: function(response) {
					//console.log('fail');
				}
			});
		},
		/**
		 * Display air information (constant between packs)
		 * @return {void}
		 */
		displayFilter: function (filter) {
            // strtolower -> ucwords : should be a util? 
            filter.desc = filter.desc.toLowerCase().replace(/(^([a-zA-Z\p{M}]))|([ -][a-zA-Z\p{M}])/g,
                function($1){
                    return $1.toUpperCase();
                });

			$('.partName').html(filter.desc + ' Pleated Replacement Air Filter - MERV ' + filter.merv);

			$('#partNumber').html(filter.number);
			//Add code for images
			if (filter.image == null) {
				$('.responsivePinchImage').addClass('hidden');
				$('.responsivePinchImage').after('<img src="http://partsbetavip.qa.ch3.s.com/partsdirect/assets/img/images/no_part.gif" />');
			} else {
				$('.responsivePinchImage [data-toggle=pinch-image]').attr('data-desktopimage', filter.image);
				$('.responsivePinchImage [data-toggle=pinch-image]').change();
			}
			
			//Check if it lacks packs
			if (pack4.number == '') {
				$('#fourPack').addClass('hidden');
			}
			if (pack6.number == '') {
				$('#sixPack').addClass('hidden');
			}
			if (pack12.number == '') {
				$('#twelvePack').addClass('hidden');
			}
			
			$('#mervRating').html(filter.merv);
			$('#filterCat').html(filter.cat);
			$('#shippingLength').html(filter.length);
			$('#shippingWidth').html(filter.width);
			$('#shippingHeight').html(filter.height);
		},
		/**
		 * Display current pack
		 * @return {void}
		 */
		displayPack: function(e) {
			var current;
            this.currentPack = e;
			switch(e) {
				case 4:
					current = pack4;
					break;
				case 6:
					current = pack6;
					break;
				case 12:
					current = pack12;
					break;
				default:
					//Error
			}
			$('#packNumber').html(e);

			$('#price').html('$' + current.price);
			//Modify message based on availability
			if (current.avail == 'BORD') {
				$('#inStock').html('Back Order');
				$('.pdpQuantityLine').removeClass('hidden');
				$('.sameDayShip').removeClass('hidden');
			} else if (current.avail == 'NLA') {
				$('#inStock').html('Not Available');
				$('.pdpQuantityLine').addClass('hidden');
				$('.sameDayShip').addClass('hidden');
			} else {
				$('#inStock').html('In stock');
				$('.pdpQuantityLine').removeClass('hidden');
				$('.sameDayShip').removeClass('hidden');
			}
			//Check for missing subscription periods
			if (!current.sub[0]) {
				$('#threeMonths').addClass('hidden');
			}
			if (!current.sub[1]) {
				$('#sixMonths').addClass('hidden');
			}
			if (!current.sub[2]) {
				$('#twelveMonths').addClass('hidden');
			}
			$('#addFilterToCart').attr('data-partnumber', current.number).attr('data-divid', current.div).attr('data-plsid', current.pls);
			
			//Sets delivery dates and hides if date is missing
			if (current.sDate == '') {
				$('#standardLabel, #standardDelivery').addClass('hidden');
			} else {
				$('#standardLabel, #standardDelivery').removeClass('hidden');
				$('#standardDelivery').html(current.sDate);
			}
			if (current.eDate == '') {
				$('#expeditedLabel, #expeditedDelivery').addClass('hidden');
			} else {
				$('#expeditedLabel, #expeditedDelivery').removeClass('hidden');
				$('#expeditedDelivery').html(current.eDate);
			}
			if (current.pDate == '') {
				$('#priorityLabel, #priorityDelivery').addClass('hidden');
			} else {
				$('#priorityLabel, #priorityDelivery').removeClass('hidden');
				$('#priorityDelivery').html(current.pDate);
			}
			$('#swyPoints').html(current.syw);
			$('#shippingWeight').html(current.weight);
            this.setPartNumber(this.basePartNumber+'-'+this.currentPack);
            this.setToUrl();
		}
	};
}());