function modelSearchResults(modelNumber, pathTaken) {

    $("#SYWHeader").hide();
    $("#SYW1").hide();
    $("#SYW2").hide();
    $("#SYW3").hide();
    $("#temp").hide();
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber,
				success : function(data) {
					var jsonResponse = data;
					var jsonLength = Object.keys(jsonResponse).length;

					var totalCount = jsonResponse[Object.keys(jsonResponse)[0]];
        			var sywCount = jsonResponse[Object.keys(jsonResponse)[1]];
					$("#searchCountTotal").append(parseInt(totalCount)+parseInt(sywCount));
        			$("#searchCountSYW").append(sywCount);
        			$("#pageCountSYW").append("1-"+sywCount+" of " + sywCount);
        			$("#searchCountDown").append(totalCount);
        			$("#pageCountResults").append("1-"+((totalCount < 25) ? totalCount : "25")+" of " + totalCount);

        			for ( var i = 2; i < jsonLength; i++) {
						var searchResults = jsonResponse[Object.keys(jsonResponse)[2]];
        				searchResults = JSON.parse(searchResults);
        				
        				for(var i = 0; i < searchResults.length; i++) {
        					var searchResultCount = 0;
                            var resultDetail = searchResults[Object.keys(searchResults)[i]];
                         	
                            if(resultDetail.sywModel == false){
                         		
                         		searchResultCount++;
                         		
                         		if (searchResultCount < 25) {
                         			if (searchResultCount % 10 == 0 && searchResultCount != 0){
                             			$("#searchResultsDown").append(
                             										+ "<div class=\"row-fluid modelSearchResultsNotSureMsg\">"
                                                                    + "<div><h4>Not sure which model is yours?</h4>"
                                                                    + "<p>Check your product for its unique model number or contact us at <span><a>1-800-252-1698.</a></span></p>"
                                                                    + "</div>" + "</div>");
                             		}
                             		$("#searchResultsDown").append(""
                            			 				+ "<div class=\"row-fluid modelSearchResultsItem\">"
                                                        + "<div class=\"span5 modelSearchResultsItemLeft\">"
                                                        + "<p>Model <span><a href=\""
                                                        + resultDetail.modelComponentsLink
                                                        + "\">"
                                                        + resultDetail.modelNumber
                                                        + "</a></span> ("
                                                        + resultDetail.parts
                                                        + " parts)</p>"
                                                        + "<span class=\"hidden-phone\"><a>View manuals</a><a>Repair Help</a></span>"
                                                        + "</div>"
                                                        + "<div class=\"span3 modelSearchResultsItemCenter\">"
                                                        + "<p>"
                                                        + resultDetail.brandName
                                                        + "</p>"
                                                        + "<p>"
                                                        + resultDetail.categoryName
                                                        + "</p>"
                                                        + "</div>"
                                                        + "<div class=\"span3 hidden-phone modelSearchResultsItemRight\">"
                                                        + "<button class=\"new-btn new-btn-search\">Shop Parts</button>"
                                                        + "</div>" + "</div>");
            					}
                        	}
                            else {
                                if(!$("#SYWHeader").is(":visible")){
                                    $("#SYWHeader").show();
                                    $("#SYW1").show();
                                    $("#SYW2").show();
                                    $("#SYW3").show();
                                    $("#temp").show();
                                }

                        		$("#searchResultsUp").append(""
                                                    + "<div class=\"row-fluid modelSearchResultsItem\">"
                                                    + "<div class=\"span5 modelSearchResultsItemLeft\">"
                                                    + "<p>Model <span><a href=\""
                                                    + resultDetail.modelComponentsLink
                                                    + "\">"
                                                    + resultDetail.modelNumber
                                                    + "</a></span> ("
                                                    + resultDetail.parts
                                                    + " parts)</p>"
                                                    + "<span class=\"hidden-phone\"><a>View manuals</a><a>Repair Help</a></span>"
                                                    + "</div>"
                                                    + "<div class=\"span3 modelSearchResultsItemCenter\">"
                                                    + "<p>"
                                                    + resultDetail.brandName
                                                    + "</p>"
                                                    + "<p>"
                                                    + resultDetail.categoryName
                                                    + "</p>"
                                                    + "</div>"
                                                    + "<div class=\"span3 hidden-phone modelSearchResultsItemRight\">"
                                                    + "<button class=\"new-btn new-btn-search\">Shop Parts</button>"
                                                    + "</div>" + "</div>");
                        	}
                        }
        			}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}

function populateBrandProductDetails(modelNumber, API, divID) {
	var urlName = API + modelNumber;
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
					var len = Object.keys(jsonResponse).length;
					var brandArr = [];
					 for(var i=0; i < len; i++){
						 var resultDetail = jsonResponse[Object.keys(jsonResponse)[i]];
						 $("."+divID).append("<option value=\""+resultDetail.name+"\">"+resultDetail.seoFormattedName+"</option>");
					 }
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}