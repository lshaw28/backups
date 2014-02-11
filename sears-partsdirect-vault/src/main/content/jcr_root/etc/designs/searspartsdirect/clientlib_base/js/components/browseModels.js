/*mingle story id: 7922
 * 
 * Flag=1 Gets list of All Categories
 * Flag=2 Gets list of Product Types for selected Category
 * Flag=3 Gets list of Brands for selected Category and Product Type
*/
   function hideAll(){
		$("#productType").hide();
         $("#brand").hide();
         $("#findModels").hide();
    }
   
   function fillAllCategory(){
	   var urlName = "/bin/searspartsdirect/search/nomodelsservlet?flag=1";
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
					var len = Object.keys(jsonResponse).length;
                    searchResults = jsonResponse[Object.keys(jsonResponse)[0]];
					searchResults = JSON.parse(searchResults);
						$("#categoryType").append(
								"<option value=\"Select\">Category</option>");
						for ( var i = 0; i < searchResults.length; i++) {
							var resultDetail = searchResults[Object.keys(searchResults)[i]];
							$("#categoryType").append(
									"<option value=\"" + resultDetail.id
											+ "\">" + resultDetail.browseCategory
											+ "</option>");
						}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
	   
   }
   
    function fillProductType(categoryType){
        if(categoryType!="Category"){
			populateProductDetails(categoryType);
        }
    }

    function populateProductDetails(categoryType) {
	var urlName = "/bin/searspartsdirect/search/nomodelsservlet?flag=2&category="+encodeURIComponent(categoryType);
		$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
					var len = Object.keys(jsonResponse).length;
                    searchResults = jsonResponse[Object.keys(jsonResponse)[0]];
					searchResults = JSON.parse(searchResults);
					$("#productType").append(
					"<option value=\"Select\">Product Type</option>");
						for ( var i = 0; i < searchResults.length; i++) {
							var resultDetail = searchResults[Object.keys(searchResults)[i]];
							$("#productType").append(
									"<option value=\"" + resultDetail.seoFormattedName
											+ "\">" + resultDetail.name
											+ "</option>");
						}

				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
    }

function fillBrandType(categoryType, productType) {
	var urlName = "/bin/searspartsdirect/search/nomodelsservlet?flag=3&category="+encodeURIComponent(categoryType)+"&productType="+encodeURIComponent(productType);
	$.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				var jsonResponse = data;
				var len = Object.keys(jsonResponse).length;
                searchResults = jsonResponse[Object.keys(jsonResponse)[0]];
				searchResults = JSON.parse(searchResults);
				$("#brand").append(
				"<option value=\"Select\">Brand</option>");
					for ( var i = 0; i < searchResults.length; i++) {
						var resultDetail = searchResults[Object.keys(searchResults)[i]];
						$("#brand").append(
								"<option value=\"" + resultDetail.seoFormattedName
										+ "\">" + resultDetail.name
										+ "</option>");
					}

			},
			error : function() {
				console.log("Failed to retrieve data from server");
			}
		});
}
