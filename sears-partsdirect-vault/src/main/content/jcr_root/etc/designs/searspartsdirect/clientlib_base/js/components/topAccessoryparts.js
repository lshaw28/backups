function topAccessoryparts(productGroupId, supplierId, partNumber, div, partsUrl) {
	$('#' + div).addClass('hidden');
	if(partNumber.length != 0){
		urlName = apiPath.replace('v1', 'parts') + partNumber + "?productGroupId=" + productGroupId + "&supplierId=" + supplierId;
		
		$.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				
				if(typeof data !== 'undefined'){
				
					var jsonResponse = data;
				
					var len = Object.keys(jsonResponse).length;	
					var searchResults = jsonResponse[Object.keys(jsonResponse)[0]];
					searchResults = JSON.parse(searchResults);
					
					$('#' + div).removeClass('hidden');
					
					if (len != 0) {
						var status = jsonResponse.priceAndAvailability.availabilityStatus;
						// In stock or back-order
						if (status == "INST" || status == "BORD" ) {
							var priceStr = jsonResponse.priceAndAvailability.sellingPrice.toString();
							priceStr.split('.')[1].length == 1 ? priceStr+= '0' : priceStr;
							if (typeof jsonResponse.partImage.imageURL !== 'undefined') {
								$('#' + div + ' .partImage img').attr('src', jsonResponse.partImage.imageURL + '?hei=164&wid=164');
							} else {
								$('#' + div + ' .partImage img').attr('src', mainSitePath + '/partsdirect/assets/img/images/no_part.gif');
							}
							$('#' + div + ' .partImage a').attr('href', partsUrl + '/partsdirect/part-number/' + partNumber + '/' + jsonResponse.partCompositeKey.productGroupId + '/' + jsonResponse.partCompositeKey.supplierId);
							$('#' + div + ' .partDescription strong a').html(jsonResponse.description).attr({ href:"partsUrl + '/partsdirect/part-number/' + partNumber + '/' + jsonResponse.partCompositeKey.productGroupId + '/' + jsonResponse.partCompositeKey.supplierId", title: jsonResponse.description});
							$('#' + div + ' .partNo strong span').text(jsonResponse.partCompositeKey.partNumber);
							$('#' + div + ' .partListItemPrice strong').text('$' + priceStr);
							status == "INST" ? $('#' + div + ' .partAvailability strong').text('In stock') : $('#' + div + ' .partAvailability strong').text('Back Order');
							$('#' + div + ' .partListItemAdd button').attr('data-partnumber', partNumber).attr('data-divid', jsonResponse.partCompositeKey.productGroupId).attr('data-plsid', jsonResponse.partCompositeKey.supplierId);
							$('#' + div + ' a.mobile-curtain').attr('href', partsUrl + '/partsdirect/part-number/' + partNumber + '/' + jsonResponse.partCompositeKey.productGroupId + '/' + jsonResponse.partCompositeKey.supplierId);
							
							var newapiPath;
	                        if(apiPath.indexOf('v1/') > -1){
	                          newapiPath = apiPath.replace('v1/', '');
	                        }
	
							//Separate API call for Shop Your Way points
							$.ajax({
								type: "GET",
								cache: false,
								dataType: "json",
								data: {
									partNumber: partNumber,
									divId: jsonResponse.partCompositeKey.productGroupId,
									plsNumber: jsonResponse.partCompositeKey.supplierId
								},
								url: newapiPath + 'syw/points',
								success: function(response) {
									$('#' + div + ' .partPts span').text(response.points);
								},
								error: function(response) {
									//console.log("SYW API fail");
								}
							});
						} else {
							$('#' + div).addClass('hidden');
						}
					} else {
						//console.log("API returns no results");
						$('#' + div).addClass('hidden');
					}
				} else {
					$('#' + div).addClass('hidden');
				}
			},
			error : function() {
			
				//console.log("Top Accessory Parts  -- API Failure");
				$('#' + div).addClass('hidden');
			}
		});
		
		var quantityField = $('#'+div+' .addToCartQuantity_js');
		quantityField.keydown(function(e){
			numbersOnly(e);
		});

		numbersOnly = function(evt) {
		    if (evt.keyCode === 46 || evt.keyCode === 8 || evt.keyCode === 9 || evt.keyCode === 27 || evt.keyCode === 13 || (evt.keyCode === 65 && evt.ctrlKey === true) || (evt.keyCode >= 35 && evt.keyCode <= 39)) {

		    } else if (evt.shiftKey || (evt.keyCode < 48 || evt.keyCode > 57) && (evt.keyCode < 96 || evt.keyCode > 105)) {
		      return evt.preventDefault();
		    }
		}
	}
}