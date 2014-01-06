/*
 * Flag 0: First Search Hit
 * Flag 1: Previous/Next - hide
 * Flag 2: Sort - show
 * Flag 3: Brand Selection - show
 * Flag 4: Product Selection - show
 * */

function clearAll(){
$("#searchCountDown").empty();
$(".pageCountResults").empty();
    $("#searchCountTotal").empty();
    $("#searchCountSYW").empty();
    $("#pageCountSYW").empty();
    $(".pageCountResults").empty();
    $('#searchResultsUp').empty();
    $('#searchResultsDown').empty();
}

function headerHide(){
	$("#totalResultCount").hide();
	$("#SYWHeader").hide();
	$("#brandRefinement").hide();
}

function headerShow(){
	$("#totalResultCount").show();
	$("#SYWHeader").show();
	$("#brandRefinement").show();
}

function sywHide(){
    $("#SYW1").hide();
    $("#SYW2").hide();
    $("#searchResultsUp").hide();
}

function sywShow(){
    $("#SYW1").show();
    $("#SYW2").show();
    $("#searchResultsUp").show();
}

function searchHide(){
	$("#search1").hide();
	$("#search2").hide();
    $("#searchResultsDown").hide();
    //if($("#SYWHeader").is(":visible")){
    	//$("#search1").show();
    //}
}

function searchShow(){
	$("#search1").show();
	$("#search2").show();
    $("#searchResultsDown").show();
}

function footerHide(){
	$("#footer").hide();
    $("#notsure").hide();
}

function footerShow(){
	$("#footer").show();
    $("#notsure").show();
}

function allModelDiagram(modelNumber, brandId, categoryId){
	var urlName = "http://partsapivip.qa.ch3.s.com/pd-services/models/"+modelNumber+"/components?brandId="+brandId+"&productCategoryId="+categoryId;
    
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "json",
		url : urlName,
        success : function(data) {
          	var jsonResponse = data.components;
			var jsonLength = jsonResponse.length;
            if(jsonLength != 0){
				     for(var j = 0; j < jsonResponse.length; j++) {

                                $("#allDiagramContainer").append("<a class=\"disableDesktop\" href=\"#\">"
											+ "<li class=\"grid-item\">"
											+ "<div class=\"diagramContainer model\">"
											+ "<img src=\""+jsonResponse[j].diagramImageUrl+"\" />"
											+ "<p class=\"diagramTitle\"><a class=\"disableMobile\" href=\"#\">"+jsonResponse[j].componentDescription+"</a></p></a>"
											+ "</div></li></a>");
                            }
                        }
        },				
		error : function() {
			console.log("Failed to retrieve data from server");
		}
	});
}

function fillDropdown(modelNumber, selectedValue, queryParam, dropDown){
	// queryParam: brand or productType (Servlet will accept this parameter)
    var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&"+queryParam+"="+selectedValue+"&flag=5";
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
            		$("#"+dropDown).empty();
            		$("#"+dropDown).append("<option value=\"Select\">--Select--</option>");
					for(var i=0; i < searchResults.length; i++){

						var resultDetail = searchResults[Object.keys(searchResults)[i]];

						$("#"+dropDown).append("<option value=\""+resultDetail.id+"\">"+resultDetail.name+"</option>");
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
	 
	 headerHide();
	 sywHide();
	 searchHide();
	 footerHide();

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
    	else if(flag == 3){
            // Brand select >> change product
        	clearAll();
        	urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&flag=3";
        	if(index != 0){
        		// if some other value is selected in dropdown, other than SELECT, then only we will pass BRAND parameter.
        		urlName = urlName + "&brand="+index;
        	}
            if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&productType="+selectedValue;
        	}
        }
    	else if(flag == 4){
            // Product select >> change brand
        	clearAll();
            urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&flag=4";
            if(index != 0){
        		// if some other value is selected in dropdown, other than SELECT, then only we will pass productType parameter.
        		urlName = urlName + "&productType="+index;
        	}
            if(typeof selectedValue !== 'undefined'){
        		urlName = urlName + "&brand="+selectedValue;
        	}
        }
	    else{
            clearAll();
			urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&offset=0&limit=25&sortType=revelence&flag=0";
	    }
//console.log(urlName);
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
                    	// these two will always present in json response whether ZERO
						var totalCount = jsonResponse[Object.keys(jsonResponse)[0]];
        				var sywCount = jsonResponse[Object.keys(jsonResponse)[1]];

        				/* Set Pagination Start */
                        if(flag == 0){
                        	// Normal Search / First Time hit
                            $("#pageNumber").empty();
	                    	for( var i = 1; i <= Math.ceil(totalCount/25); i++){
	                        	if(i == 1){
                                	$("#pageNumber").append("<option value=\""+i+"\" selected>Page "+i+"</option>");
                            	}
	                        	else{
                                	$("#pageNumber").append("<option value=\""+i+"\" >Page "+i+"</option>");
                            	}
	                    	}
                    	}
                        /* Set Pagination End */

                        /* Set Total Count Start */
                       $("#searchCountTotal").append(parseInt(totalCount)+parseInt(sywCount));
                       $("#searchCountSYW").append(sywCount);
                       $("#searchCountDown").append(totalCount);
                       $("#pageCountSYW").append("1-"+sywCount+" of " + sywCount);

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
                        /* Set Total Count Start */

                        /* Show Results Start */
                        for ( var i = 2; i < jsonLength; i++) {
                            var searchResults = jsonResponse[Object.keys(jsonResponse)[2]];
                            searchResults = JSON.parse(searchResults);
                            var searchResultCount = 0;
                            console.log("search count: "+searchResults.length);

                            for(var j = 0; j < searchResults.length; j++) {

                                var resultDetail = searchResults[Object.keys(searchResults)[j]];
    
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
                                                            + "<p>Model <span><a href=\"/content/searspartsdirect/en/modelalldiagram.html?modelNumber="+resultDetail.modelNumber+"&brandId="+resultDetail.brandId+"&categoryId="+resultDetail.categoryId+"\""
                                                            + resultDetail.modelComponentsLink
                                                            + "\">"
                                                            + resultDetail.modelNumber
                                                            + "</a></span> ("
                                                            + resultDetail.partCount
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
                                else if((flag == 0 || flag == 3 || flag == 4 || (flag == 1 && index == 0)) && resultDetail.sywModel == true) {
                                    /*
                                     * Normal Result
                                     * Flag 3 - Brand Selection
                                     * Flag 4 - Product Selection
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
                                                        + resultDetail.partCount
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
                        /* Show Results End */
                    }else{
                    	// It should get redirected to No Models Found Page
                    	$(window.location).attr('href', 'no-models-found.html?modelNum='+modelNumber);
                    }
                    if(sywResultExist == true || searchResultExist == true){
                        headerShow();
                        footerShow();
                    }
                    if(sywResultExist == true){
                        sywShow();
                    }else{
                    	if(flag == 2){
                    		// sorting
                    		sywHide();
                    	}
                    }
                    if(searchResultExist == true){
                        searchShow();
                    }else{
                    	searchHide();
                    }
                    if(sywCount == 0){
                        $("#SYWHeader").hide();
                        $("#search1").hide();
                    }
                },				
				error : function() {
					console.log("Failed to retrieve data from server");
				}
			});
}

function populateBrandProductDetails(modelNumber, divID, isSelf) {
	// divID parameter -- in case we want to fill only one dropdown.
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+modelNumber+"&flag=6";
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
	if(typeof divID === 'undefined'){
	                        $("#brand").append("<option value=\"Select\">--Select--</option>");
	for(var i=0; i < searchResults.length; i++){
	var resultDetail = searchResults[Object.keys(searchResults)[i]];
	$("#brand").append("<option value=\""+resultDetail.id+"\">"+resultDetail.name+"</option>");
	}

	searchResults = jsonResponse[Object.keys(jsonResponse)[1]];
	       
	searchResults = JSON.parse(searchResults);

	brandArr = [];
	                        $("#productType").append("<option value=\"Select\">--Select--</option>");
	for(var i=0; i < searchResults.length; i++){
	var resultDetail = searchResults[Object.keys(searchResults)[i]];
	$("#productType").append("<option value=\""+resultDetail.id+"\">"+resultDetail.name+"</option>");
	}
	}
	else if(typeof divID !== 'undefined'){
	                        if(divID == 'brand'){
	                            var selected = $("#brand").val();
	                            $("#brand").empty();
	                            $("#brand").append("<option value=\"Select\">--Select--</option>");
	for(var i=0; i < searchResults.length; i++){
	var resultDetail = searchResults[Object.keys(searchResults)[i]];
	                                $("#brand").append("<option value=\""+resultDetail.id+"\">"+resultDetail.name+"</option>");
	}
	                            if(typeof isSelf !== 'undefined' && isSelf == 'false'){
	                            
	$("#brand > [value='"+selected+"']").attr("selected", "true");
	                            }
	}
	if(divID == 'productType'){
	var selected = $("#productType").val();
	                            $("#productType").empty();
	                            $("#productType").append("<option value=\"Select\">--Select--</option>");

	                            searchResults = jsonResponse[Object.keys(jsonResponse)[1]];
	       
	searchResults = JSON.parse(searchResults);
	for(var i=0; i < searchResults.length; i++){
	var resultDetail = searchResults[Object.keys(searchResults)[i]];
	$("#productType").append("<option value=\""+resultDetail.id+"\">"+resultDetail.name+"</option>");
	}
	if(typeof isSelf !== 'undefined' && isSelf == 'false'){
	                            
	$("#productType > [value='"+selected+"']").attr("selected", "true");
	                            }
	}
	}
	},
	error : function() {
	console.log("Failed to retrieve data from server");
	}
	});
	}