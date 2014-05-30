function modelDiagramPartList(modelNumber, formattedModelNumber, brandId, categoryId, diagramPageId, documentId, flagMessage, brandName, modelDescription) {
	
	var urlName = "/bin/searspartsdirect/model/partlist?modelnumber=" + formattedModelNumber + "&brandId=" + brandId + "&productCategoryId="
			+ categoryId + "&diagramPageId=" + diagramPageId + "&documentId=" + documentId;
	console.log(urlName);
    $.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				if(typeof data.jsonPartList !== 'undefined'){
					showPartList(data, flagMessage, modelNumber, brandId, categoryId, brandName, modelDescription);
				}else{
					console.log("Model Diagram - Part List -- JSON not formed correctly");
				}
			},
			error : function() {
				console.log("Model Diagram - Part List -- API Failure");
			}
		});
}

function showPartList(data, flagMessage, modelNumber, brandId, categoryId, brandName, modelDescription){
	var jsonPartList = data.jsonPartList;
	jsonPartList = JSON.parse(jsonPartList);
	var jsonResponse = jsonPartList.parts;
	var jsonLength = jsonResponse.length;
	
	flagMessage = flagMessage.replace("&#39;","'");
    var flagJson = JSON.parse(flagMessage);
	
	if (jsonLength != 0) {
		
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
			
			var description = (isDescriptionClickable == true) ? "<a href=\"/content/searspartsdirect/en/partdetail.html?modelNumber="+modelNumber+"&brandId="+brandId+"&categoryId="+categoryId+"&brandName="+brandName+"&modelDescription="+modelDescription+"\">"+jsonResponse[j].description+"</a>" : jsonResponse[j].description;
			
			if(j ==0 && typeof jsonResponse[j].typeOfPart !== 'undefined' && jsonResponse[j].typeOfPart == "water_filter_part"){
				$("#partListItems").append("<div class=\"partListItem row-fluid\">"
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
				$("#partListItems").append("<div class=\"partListItem row-fluid\">"
						+ "<div class=\"new-span-general diagramPosition\">"
						+ "<p><span>"+jsonResponse[j].keyId+"</span><br />on diagram</p>"
						+ "</div>"
						+ "<div class=\"new-span-general partListItemDescription\">"
						+ (typeof jsonResponse[j].partImage.imageURL !== 'undefined' ? "<div class=\"partListItemImage\"><img src=\""+jsonResponse[j].partImage.imageURL+"\" /></div>" : "")
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
	}
}
var modelPartListDiagram = new stickyItem('#partListDiagramImage');
modelPartListDiagram.setBreakPoint('top');
modelPartListDiagram.setClassToggles('fixed','sticky');
$(window).scroll(function(){
    modelPartListDiagram.checkState( $(window).scrollTop() );
});