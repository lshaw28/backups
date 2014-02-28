function partSearchResults(partNumber, flagMessage) {
	var urlName = "/bin/searspartsdirect/search/searchservlet?partnumber="+partNumber;
    flagMessage = flagMessage.replace("&#39;","'");
    var flagJson = JSON.parse(flagMessage);
	
    $.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				var jsonResponse = data.partResults;
				jsonResponse = JSON.parse(jsonResponse);
				var jsonLength = jsonResponse.length;
				
				var modelCount = 0;
				
				if(typeof data.totalCount !== 'undefined'){
					modelCount = parseInt(data.totalCount);
				}
				if (jsonLength != 0) {
					$("#partCountHeader").show();
					$("#partCount").empty();
					$("#partCount").append(jsonLength);
					
					if(modelCount != 0){
						$("#modelCountHeader").show();
						$("#modelCount").empty();
						$("#modelCount").append(modelCount);
					}
					for ( var j = 0; j < jsonLength; j++) {
						var topPartsList = "";
						var isDescriptionClickable = false;
						
						if(jsonResponse[j].priceAndAvailability.availabilityStatus == "INST" || jsonResponse[j].priceAndAvailability.availabilityStatus == "PNF" || jsonResponse[j].priceAndAvailability.availabilityStatus == "BORD"){
							isDescriptionClickable = true;
						}
						if(jsonResponse[j].priceAndAvailability.availabilityStatus == "INST"){
							// In Stock -- Displaying Quantity & Add To Cart
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
					console.log("Part Search Results -- API Failure");
				}
			});
}

function showFlagMessage(flagJson, flagStatus){
    return (typeof flagStatus !== 'undefined' ? flagJson[flagStatus] : "");
}