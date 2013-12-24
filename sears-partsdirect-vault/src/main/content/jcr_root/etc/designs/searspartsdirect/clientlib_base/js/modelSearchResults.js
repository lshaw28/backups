/*
 * Flag 0: First Search Hit 
 * Flag 1: Previous/Next
 * Flag 2: Sort
 * Flag 3: Brand Selection
 * Flag 4: Product Selection
 * Flag 5: SYW Previous/Next
 * */

function clearAll(){
	$('#searchResultsDown').empty();
    $("#searchCountDown").empty();
	$(".pageCountResults").empty();
    $("#searchCountTotal").empty();
    $("#searchCountSYW").empty();
    $("#pageCountSYW").empty();
    $("#searchCountDown").empty();
    $(".pageCountResults").empty();
}

function fillDropdown(modelNumber, param, param1, param2){
    var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&"+param1+"="+param;
	$.ajax({
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
					for(var i=0; i < searchResults.length; i++){
						var resultDetail = searchResults[Object.keys(searchResults)[i]];
						$("#"+param2).append("<option value=\""+resultDetail.name+"\">"+resultDetail.seoFormattedName+"</option>");
					}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}

function modelSearchResults(modelNumber, pathTaken, flag, index, selectedValue) {
	 var urlName="";
	 var offset=0;

	    if(flag == 1){
            // page change
	        clearAll();
	        $("#searchResultsUp").empty();
		    offset=index*25;
		    urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset="+offset+"&limit=25&sortType=revelence&flag=1";
	    }
        else if(flag == 2){
            // sort result
        	clearAll();
        	urlName="/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&flag=2";
            if(index == 0){
            	urlName = urlName + "&sortType=revelence";
            }else if(index == 1){
            	urlName = urlName + "&sortType=model-asc";
            }else if(index == 2){
            	urlName = urlName + "&sortType=model-desc";
            }
        }
    	else if(flag == 3){
            // Brand select >> change product
        	clearAll();
        	urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&brand="+index+"&flag=3";
        	if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&productType="+selectedValue;
        	}
        }
    	else if(flag == 4){
            // Product select >> change brand
        	clearAll();
            urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&productType="+index+"&flag=4";
            if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&brand="+selectedValue;
        	}
        }
	    else{
			urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&flag=0";
	    }


    $("#SYWHeader").hide();
    $("#SYW1").hide();
    $("#SYW2").hide();
    $("#SYW3").hide();
    $("#temp").hide();
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
                success : function(data) {

                    var jsonResponse = data;
					var jsonLength = Object.keys(jsonResponse).length;

					var totalCount = jsonResponse[Object.keys(jsonResponse)[0]];
        			var sywCount = jsonResponse[Object.keys(jsonResponse)[1]];


                   if(flag==0){
	                    for( var i = 1; i <= Math.ceil(totalCount/25); i++){
	                        if(i==1){
                                $("#pageNumber").append("<option value=\""+i+"\" selected>Page "+i+"</option>");
                            }
	                        else{
                                $("#pageNumber").append("<option value=\""+i+"\" >Page "+i+"</option>");
                            }
	                    }
                    }
                   $("#searchCountTotal").append(parseInt(totalCount)+parseInt(sywCount));
                   $("#searchCountSYW").append(sywCount);
                   $("#pageCountSYW").append("1-"+sywCount+" of " + sywCount);
                   $("#searchCountDown").append(totalCount);

                    var toshow = 0;
                    if(totalCount < 25){
                        toshow = totalCount;
                    }else{
                        if(totalCount - offset < 25){
                            toshow = totalCount;
                        }else{
                        	toshow = offset + 25;
                        }
                    }
                    $(".pageCountResults").append((offset+1)+"-"+toshow+" of " + totalCount);

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
                            else if((flag == 0 || (flag == 1 && index == 0)) && resultDetail.sywModel == true) {
                            	// Search Your Way section
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

function populateBrandProductDetails(modelNumber) {
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber;
	$.ajax({
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
					$(".brand").append("<option value=\"Select\">--Select--</option>");
					for(var i=0; i < searchResults.length; i++){
						var resultDetail = searchResults[Object.keys(searchResults)[i]];
						$(".brand").append("<option value=\""+resultDetail.name+"\">"+resultDetail.seoFormattedName+"</option>");
					}
					
					searchResults = jsonResponse[Object.keys(jsonResponse)[1]];
        			searchResults = JSON.parse(searchResults);

					brandArr = [];
					$(".product").append("<option value=\"Select\">--Select--</option>");
					for(var i=0; i < searchResults.length; i++){
						var resultDetail = searchResults[Object.keys(searchResults)[i]];
						$(".product").append("<option value=\""+resultDetail.name+"\">"+resultDetail.seoFormattedName+"</option>");
					}
				},
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}