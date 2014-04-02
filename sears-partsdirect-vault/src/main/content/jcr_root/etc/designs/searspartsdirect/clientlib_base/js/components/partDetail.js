// story 7812,7964,7844
function partDetail(productGroupId, supplierId, partNumber) {
	urlName = "/bin/searspartsdirect/search/partdetailservlet?partNumber="
			+ partNumber + "&productGroupId=" + productGroupId + "&supplierId="
			+ supplierId + "&flag=1";
	$
			.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					console.log("Success");
					var jsonResponse = JSON.parse(data.partDetail);
					if (jsonResponse != "undefined") {
						var partDetail = "";
						var substitution = "";
						var availability = "";
						var status = jsonResponse.priceAndAvailability.availabilityStatus;
						var isDescriptionClickable = false;
						$(".partName").text(jsonResponse.description);

						if (jsonResponse.priceAndAvailability.nonReturnableValue != "true") {
							$("#refundStatus").text("This item is returnable");
						} else {
							$("#refundStatus").text(
									"This item is non returnable");
						}
						if (status == "INST") {
							if (jsonResponse.priceAndAvailability.originalPartNumber != jsonResponse.partCompositeKey.partNumber) {
								substitution = "<br /><small><i class=\"icon-share flip-vertical\">&nbsp;</i> Substitution: "
										+ jsonResponse.partCompositeKey.partNumber
										+ "</small>";
							}
							// In Stock
							availability = "<h4>In Stock</h4>";

						} else if (status == "BORD") {
							// Back Ordered
							// do nothing
						} else if (status == "PNF") {
							// Part Not Found
							availability = "<p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>";
						} else if (status == "NLA") {
							// No Longer Available
							availability = "<p>We're sorry, this item is no longer available.</p>";
						} else if (status == "TECH") {
							// Technician Required
							// do nothing
						}

						$("#inStock").html(availability);
						$("#partNumber").html(
								jsonResponse.partCompositeKey.partNumber
										+ substitution);

						$("#price")
								.text(
										"$"
												+ jsonResponse.priceAndAvailability.sellingPrice);

						if (jsonResponse.featureCopyAvailable != "flase") {
							var partInfo = "";
							for ( var i = 0; i < jsonResponse.partAttributes.length; i++) {
								var partName = jsonResponse.partAttributes[i].name
										.toString();
								if (partName.indexOf("FeatureCopy") != -1) {
									partInfo = partInfo
											+ jsonResponse.partAttributes[i].value;
								}
								if (partName == "ShippingWeight") {
									$("#shippingWeight")
											.text(
													jsonResponse.partAttributes[i].value);
								}
								if (partName == "ShippingLength") {
									$("#shippingLength")
											.text(
													jsonResponse.partAttributes[i].value);
								}
								if (partName == "ShippingWidth") {
									$("#shippingWidth")
											.text(
													jsonResponse.partAttributes[i].value);
								}
								if (partName == "ShippingHeight") {
									$("#shippingHeight")
											.text(
													jsonResponse.partAttributes[i].value);
								}
							}
							$("#featureCopyVal").html(partInfo);
						}

						$("#filterCat").text(
								jsonResponse.productGroupDescription);
						// bundle Zoom

						if (jsonResponse.waterFilterBundle != undefined) {
							var partNumber = jsonResponse.waterFilterBundle.partCompositeKey.partNumber;
							var productGroupId = jsonResponse.waterFilterBundle.partCompositeKey.productGroupId;
							var supplierId = jsonResponse.waterFilterBundle.partCompositeKey.supplierId;
							var pageURL = document.URL.substring(0,
									document.URL.indexOf("?"));
							var bundleURL = pageURL + "?partNumber="
									+ partNumber + "&productGroupId="
									+ productGroupId + "&supplierId="
									+ supplierId;

							$('.bundleLink').attr('href', bundleURL);

							$('#bundleImage').attr('src',
									jsonResponse.partImage.imageURL);
						} else {

							$("#zoom").css({
								"display" : "none"
							});
						}
						// Cross Sale Component
						crossSale(jsonResponse);

					} else {
						console.log("API returns no results");
					}
				},
				error : function() {
					console.log("Part detail  -- API Failure");
				}
			});
}

function crossSale(jsonResponse) {
	if (jsonResponse.commonPairs != "") {
		$("#commonPairs").append(
				"<h1>Customer's who bought this part also bought :</h1>");
		var pairLen = jsonResponse.commonPairs.length;
		if (pairLen > 2) {
			pairLen = 2;
		}
		for ( var i = 0; i < pairLen; i++) {
			var desc = jsonResponse.commonPairs[i].description;
			var partNo = jsonResponse.commonPairs[i].partCompositeKey.partNumber;
			var substitution = "";
			var imageUrl = jsonResponse.commonPairs[i].partImage.imageURL;
			var price = jsonResponse.commonPairs[i].priceAndAvailability.sellingPrice;
			var status = jsonResponse.commonPairs[i].priceAndAvailability.availabilityStatus;
			var isDescriptionClickable = "";
			var availability = "";

			if (status == "INST" || status == "PNF" || status == "BORD") {
				isDescriptionClickable = true;
			}
			var description = (isDescriptionClickable == true) ? "<a href=\"http://www.urlforthepart.com\">"
					+ desc + "</a>"
					: desc;

			if (status == "INST") {
				if (jsonResponse.commonPairs[i].priceAndAvailability.originalPartNumber != jsonResponse.commonPairs[i].partCompositeKey.partNumber) {
					substitution = "<br /><small><i class=\"icon-share flip-vertical\">&nbsp;</i> Substitution: "
							+ jsonResponse.commonPairs[i].partCompositeKey.partNumber
							+ "</small>";
				}
				// In Stock
				availability = "<h4>In Stock</h4>"
						+ "<div class=\"partListItemQuantity\">"
						+ "<label>Qty</label>"
						+ "<input type=\"text\" class=\"addToCartQuantity_js\" value=\"1\" />"
						+ "</div>"
						+ "<div class=\"partListItemAdd\">"
						+ "<button type=\"button\" data-partnumber=\"partNumber\" data-divid=\"productGroupID\" data-plsid=\"supplierID\" class=\"new-btn new-btn-search addToCart_js\">Add to Cart</button>"
						+ "</div>";
			} else if (status == "BORD") {
				// Back Ordered
				// do nothing
			} else if (status == "PNF") {
				// Part Not Found
				availability = "<p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>";
			} else if (status == "NLA") {
				// No Longer Available
				availability = "<p>We're sorry, this item is no longer available.</p>";
			} else if (status == "TECH") {
				// Technician Required
				// do nothing
			}

			$("#commonPairs").append(
					"<img src=\"" + imageUrl
							+ "\" height=\"100px\" width=\"100px\"/>" + "<h4>"
							+ description + "</h4>" + "<h4>Part Number: "
							+ partNo + "</h4>" + "<h4>" + substitution
							+ "</h4>" + "<h4>$" + price + "</h4>"
							+ availability);
		}
	}
}