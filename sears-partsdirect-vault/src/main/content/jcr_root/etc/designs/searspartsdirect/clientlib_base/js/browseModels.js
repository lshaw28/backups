   function hideAll(){
		$(".productType").hide();
         $(".brand").hide();
         $("#findModels").hide();
    }
    function fillProductType(categoryType){
        if(selectedValue=="Select"){
            //do nothing}
        }
        else{
			populateProductDetails(categoryType)
        }
    }

    function populateProductDetails(categoryType) {
	// divID parameter -- in case we want to fill only one dropdown.
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="
			+ categoryType + "&flag=6";
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
					var len = Object.keys(jsonResponse).length;

						searchResults = jsonResponse[Object.keys(jsonResponse)[1]];

						searchResults = JSON.parse(searchResults);

						brandArr = [];
						$("#productType").append(
								"<option value=\"Select\">Select</option>");
						for ( var i = 0; i < searchResults.length; i++) {
							var resultDetail = searchResults[Object
									.keys(searchResults)[i]];
							$("#productType").append(
									"<option value=\"" + resultDetail.id
											+ "\">" + resultDetail.name
											+ "</option>");
						}

				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
    }

function fillBrandType(categoryTypeSelected, selectedValue, queryParam, dropDown) {
	// queryParam: brand or productType (Servlet will accept this parameter)
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="
			+ categoryTypeSelected + "&" + queryParam + "=" + selectedValue + "&flag=5";
	$
			.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
					var len = Object.keys(jsonResponse).length;
					var searchResults = jsonResponse[Object.keys(jsonResponse)[0]];
					searchResults = JSON.parse(searchResults);

					var brandArr = [];
					$("#" + dropDown).empty();
					$("#" + dropDown).append(
							"<option value=\"Select\">Select</option>");
					for ( var i = 0; i < searchResults.length; i++) {

						var resultDetail = searchResults[Object
								.keys(searchResults)[i]];

						$("#" + dropDown).append(
								"<option value=\"" + resultDetail.id + "\">"
										+ resultDetail.name + "</option>");
					}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}
