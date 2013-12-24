/*
 * Flag 0: First Search Hit
 * Flag 1: Previous/Next - hide
 * Flag 2: Sort - show
 * Flag 3: Brand Selection - show
 * Flag 4: Product Selection - show
 * Flag 5: SYW Brand
 * Flag 6: SYW Product
 * */

function clearAll(){
	$("#searchCountDown").empty();
	$(".pageCountResults").empty();
    $("#searchCountTotal").empty();
    $("#searchCountSYW").empty();
    $("#pageCountSYW").empty();
    $("#searchCountDown").empty();
    $(".pageCountResults").empty();
}

function sywHide(){
    $("#SYWHeader").hide();
    $("#SYW1").hide();
    $("#SYW2").hide();
    $("#SYW3").hide();
    $("#searchResultsUp").hide();
}

function sywShow(){
    $("#SYWHeader").show();
    $("#SYW1").show();
    $("#SYW2").show();
    $("#SYW3").show();
    $("#searchResultsUp").show();
}

function searchShow(){
	$("#searchCountTotal").show();
	if($("#SYWHeader").is(":visible")){
    	$("#search1").show();
    }
    $("#search2").show();
    $("#search3").show();
    $("#searchResultsDown").show();
	$("#footer").show();
    $("#notsure").show();
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
            		$("#"+param2).empty();
            		$("#"+param2).append("<option value=\"Select\">--Select--</option>");
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
	        $("#searchResultsUp").empty();	// SYW results should be empty when you click on next/previous
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
    	else if(flag == 3 || flag == 5){
            // Brand select >> change product
        	clearAll();
            if(flag ==3){$('#searchResultsDown').empty();}
        	urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&brand="+index+"&flag=3";
        	if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&productType="+selectedValue;
        	}
        }
    	else if(flag == 4 || flag == 6){
            // Product select >> change brand
        	clearAll();
            if(flag ==4){$('#searchResultsDown').empty();}
            urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&productType="+index+"&flag=4";
            if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&brand="+selectedValue;
        	}
        }
	    else{
			urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&flag=0";
	    }

    	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
                success : function(data) {

                  	var jsonResponse = data;
					var jsonLength = Object.keys(jsonResponse).length;
                    var searchResultExist = false;
                    var sywResultExist = false;

                    if(jsonLength != 0){

						var totalCount = jsonResponse[Object.keys(jsonResponse)[0]];
        				var sywCount = jsonResponse[Object.keys(jsonResponse)[1]];

                        if(flag == 0){
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

                       	/*if(flag == 2 || flag == 3 || flag == 4){
                            sywShow();
                        }else{
                            sywHide();
                        }*/

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
                            var searchResultCount = 0;
    
                            for(var i = 0; i < searchResults.length; i++) {
    
                                var resultDetail = searchResults[Object.keys(searchResults)[i]];
    
                                if((flag == 0 || flag == 1 || flag == 2 || flag == 3 || flag == 4) && resultDetail.sywModel == false) {
    
                                    if(searchResultExist == false){
                                        searchResultExist = true;
                                    }
    
                                    if (searchResultCount < 25) {
    
    
                                        if (searchResultCount % 10 == 0 && searchResultCount != 0){
    
                                            $("#searchResultsDown").append("<div class=\"row-fluid modelSearchResultsNotSureMsg\">"
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
                                                            + "</div></div>");
                                    }
                                    searchResultCount++;
                                }
                                else if((flag == 0 || flag == 5 || flag == 6 || (flag == 1 && index == 0)) && resultDetail.sywModel == true) {
                                    /*
                                     * Normal Result
                                     * Flag 5: Brand Selection
                                     * Flag 6: Product Selection
                                     * Flag 1, index 0: Clicking on Previous and comes to first page
                                     * */
                                    if(sywResultExist == false){
                                        sywResultExist = true;
                                    }


                                    /*if(!$("#SYWHeader").is(":visible")){
                                        $("#SYWHeader").show();
                                        $("#SYW1").show();
                                        $("#SYW2").show();
                                        $("#SYW3").show();
                                        $("#temp").show();
                                    }*/
    
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
                    }
					if(sywResultExist == true){
                        sywShow();
                    }
                    if(searchResultExist == true){
                        searchShow();
                    }

                },				
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}

function populateBrandProductDetails(modelNumber) {
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&flag=7";
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
						$(".brand").append("<option value=\""+resultDetail.name+"\">"+resultDetail.seoFormattedName+"</option>");
					}

					searchResults = jsonResponse[Object.keys(jsonResponse)[1]];
        			searchResults = JSON.parse(searchResults);

					brandArr = [];

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