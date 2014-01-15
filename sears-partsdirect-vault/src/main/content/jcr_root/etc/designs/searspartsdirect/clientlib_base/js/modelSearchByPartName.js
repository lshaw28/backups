function modelSearchByPartName(modelNumber, brandId, categoryId, description) {
	
	var urlName = "http://partsapivip.qa.ch3.s.com/pd-services/models/"+modelNumber+"/components/parts"
			+"?description="+description+"&brandId="+brandId+"&productCategoryId="+categoryId;
	console.log(urlName);
	$.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				console.log("Success");
				var jsonResponse = data.parts;
				var jsonLength = jsonResponse.length;
				if (jsonLength != 0) {
					for ( var j = 0; j < jsonResponse.length; j++) {
						var topPartsList = "";
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
						
						if(j ==0 && typeof jsonResponse[j].typeOfPart !== 'undefined' && jsonResponse[j].typeOfPart == "water_filter_part"){
							$("#partListItems").append("<div class=\"partListItem row-fluid\">"
										+ "<div class=\"new-span-general partListItemDescription\">"
										+ "* Official water filter for this model"
										+ (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
										+ "<p><a href=\"http://www.urlforthepart.com\">"+jsonResponse[j].description+"</a><br />"
											+ "Part #: "+jsonResponse[j].partCompositeKey.partNumber
										+ "</p>"
									+ "</div>"
									+ "<div class=\"new-span-general partListItemCart\">"
									+ "<div class=\"partListItemPrice\">"
									+ "<strong>$"+jsonResponse[j].priceAndAvailability.sellingPrice+"</strong> In stock"
									+ "</div>"
									+ "<div class=\"partListItemAdd\">"
									+ "<button type=\"button\" data-partnumber=\"partNumber\" data-divid=\"productGroupID\" data-plsid=\"supplierID\" class=\"new-btn new-btn-search addToCart_js\">Product Details</button>"
									+ "</div>"
									+ "</div>"
									+ "<br/><div>Set up Automatic Reorder on this product and get FREE Standard Shipping today! Details</div>"
									+ "</div>");
						}else{
							$("#partNameResults").append("<div class=\"partListItem row-fluid\">"
													+ "<div class=\"new-span-general diagramPosition\">"
														+ "<p><span>"+jsonResponse[j].keyId+"</span><br />on diagram</p>"
													+ "</div>"
													+ "<div class=\"new-span-general partListItemDescription\">"
														+ (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
																								
														+ "<p><a href=\"http://www.urlforthepart.com\">"+jsonResponse[j].description+"</a><br />"
															+ "Part #: "+jsonResponse[j].partCompositeKey.partNumber
															+ ((jsonResponse[j].partRestrictions.length > 0 && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><small><i class=\"icon-share flip-vertical\">&nbsp;</i> Substitution: YYYYYYYY</small>" : "")
															+ ((jsonResponse[j].priceAndAvailability.partReturnable == false && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><span class=\"error\">This item is not returnable</span>" : "")
														+ "</p>"
													+ "</div>"
													+ "<div class=\"new-span-general partListItemCart\">"
														+ topPartsList
													+ "</div></div>");
							}
						}
					}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}