function modelSearchByPartName(modelNumber, brandId, categoryId, description, flagMessage) {
	
	var urlName = "/bin/searspartsdirect/model/searchbypartname?modelnumber="+modelNumber
			+"&brandId="+brandId+"&productCategoryId="+categoryId+"&partDescription="+description;
	
	flagMessage = flagMessage.replace("&#39;","'");
    var flagJson = JSON.parse(flagMessage);
	
	$.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				var jsonResponse = data.searchByPartName;
				jsonResponse = JSON.parse(jsonResponse);
				var jsonLength = jsonResponse.length;
				if (jsonLength != 0) {
					$("#searchCountDiv").show();
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
								+ "<strong>$"+jsonResponse[j].priceAndAvailability.sellingPrice+"</strong> " + showFlagMessage(flagJson, jsonResponse[j].priceAndAvailability.availabilityStatus)
								+ "</div>"
								+ "<div class=\"partListItemQuantity\">"
								+ "<label>Qty</label>"
								+ "<input type=\"text\" class=\"addToCartQuantity_js\" value=\"1\" />"
								+ "</div>"
								+ "<div class=\"partListItemAdd\">"
								+ "<button type=\"button\" data-partnumber=\"partNumber\" data-divid=\"productGroupID\" data-plsid=\"supplierID\" class=\"new-btn new-btn-search addToCart_js\">Add to Cart</button>"
								+ "</div>";
						}else {
							// Other Flags -- BORD, TECH, PNF, NLA -- Need to show only Flag Message
							topPartsList = showFlagMessage(flagJson, jsonResponse[j].priceAndAvailability.availabilityStatus);
						}
						
						var description = (isDescriptionClickable == true) ? "<a href=\"http://www.urlforthepart.com\">"+jsonResponse[j].description+"</a>" : jsonResponse[j].description;
						
						if(j ==0 && typeof jsonResponse[j].typeOfPart !== 'undefined' && jsonResponse[j].typeOfPart == "water_filter_part"){
							$("#partNameResults").append("<div class=\"partListItem row-fluid\">"
									+ "<div class=\"new-span-general partListItemDescription\">"
									+ "* Official water filter for this model"
									+ (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
									+ "<p>" + description + "<br />"
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
									+ "<div class=\"new-span-general partListItemDescription\">"
                                    + (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img style=\"width:100px; height:100px;\" src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
									+ "<p>" + description + "<br />"
									+ "Part #: "+jsonResponse[j].priceAndAvailability.originalPartNumber
                                    + "<br/>Found in diagram: "+jsonResponse[j].foundInDiargamDescription
									+ (((jsonResponse[j].priceAndAvailability.originalPartNumber != jsonResponse[j].partCompositeKey.partNumber) && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><small><i class=\"icon-share flip-vertical\">&nbsp;</i> Substitution: "+jsonResponse[j].partCompositeKey.partNumber+"</small>" : "")
									+ ((jsonResponse[j].priceAndAvailability.partReturnable == false && jsonResponse[j].priceAndAvailability.availabilityStatus == "INST") ? "<br /><br/><span class=\"error\">This item is not returnable</span>" : "")
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
					console.log("Search By Part Name -- API Failure");
				}
			});
}