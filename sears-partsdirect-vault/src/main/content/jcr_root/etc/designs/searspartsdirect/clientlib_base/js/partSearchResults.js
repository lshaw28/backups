function partSearchResults(partNumber) {
	var urlName = "http://partsapivip.qa.ch3.s.com/pd-services/parts?partNumber="+partNumber;
	
	$.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				console.log("Success");
				var jsonResponse = data;
				var jsonLength = jsonResponse.length;
				if (jsonLength != 0) {
					$("#searchCount").append(jsonLength);
					for ( var j = 0; j < jsonResponse.length; j++) {
						var topPartsList = "";
						var isDescriptionClickable = false;
						if(jsonResponse[j].priceAndAvailability.availabilityStatus == "INST" || jsonResponse[j].priceAndAvailability.availabilityStatus == "PNF" || jsonResponse[j].priceAndAvailability.availabilityStatus == "BORD"){
							isDescriptionClickable = true;
						}
						if(jsonResponse[j].priceAndAvailability.availabilityStatus == "INST"){
							// In Stock
							topPartsList = "<div class=\"partListItemPrice\">"
								+ "<strong>$"+jsonResponse[j].priceAndAvailability.sellingPrice+"</strong> In stock"
								+ "</div>"
								+ "<div class=\"partListItemQuantity\">"
								+ "<label>Qty</label>"
								+ "<input type=\"text\" class=\"addToCartQuantity_js\" value=\"1\" />"
								+ "</div>"
								+ "<div class=\"partListItemAdd\">"
								+ "<button type=\"button\" data-partnumber=\"partNumber\" data-divid=\"productGroupID\" data-plsid=\"supplierID\" class=\"new-btn new-btn-search addToCart_js\">Add to Cart</button>"
								+ "</div>";
						}else if(jsonResponse[j].priceAndAvailability.availabilityStatus == "BORD"){
							// Back Ordered
							// do nothing
						}else if(jsonResponse[j].priceAndAvailability.availabilityStatus == "PNF"){
							// Part Not Found
							topPartsList = "<p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>";
						}else if(jsonResponse[j].priceAndAvailability.availabilityStatus == "NLA"){
							// No Longer Available
							topPartsList = "<p>We're sorry, this item is no longer available.</p>";
						}else if(jsonResponse[j].priceAndAvailability.availabilityStatus == "TECH"){
							// Technician Required
							// do nothing
						}
						
						var description = (isDescriptionClickable == true) ? "<a href=\"http://www.urlforthepart.com\">"+jsonResponse[j].description+"</a>" : jsonResponse[j].description;
						
						$("#partSearchResults").append("<div class=\"partListItem row-fluid\">"
													+ "<div class=\"new-span-general partListItemDescription\">"
                                                       + (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img style=\"width:100px; height:100px;\" src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
																								
														+ "<p>" + description + "<br />"
															+ "Part #: "+jsonResponse[j].priceAndAvailability.originalPartNumber
															+ (((jsonResponse[j].priceAndAvailability.originalPartNumber != jsonResponse[j].partCompositeKey.partNumber) && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><small><i class=\"icon-share flip-vertical\">&nbsp;</i> Substitution: "+jsonResponse[j].partCompositeKey.partNumber+"</small>" : "")
															+ ((jsonResponse[j].priceAndAvailability.partReturnable == false && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><span class=\"error\">This item is not returnable</span>" : "")
														+ "</p>"
													+ "</div>"
													+ "<div class=\"new-span-general partListItemCart\">"
														+ topPartsList
													+ "</div></div>");
						}
					}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}