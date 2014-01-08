    function fillDropdowns(){

    var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber=0+&flag=99";
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
   		 $("#brand").append("<option value=\"Select\">--Select--</option>");

        for(var i=0; i < searchResults.length; i++){
                  var resultDetail = searchResults[Object.keys(searchResults)[i]];
                  $("#brand").append("<option value=\""+resultDetail.seoFormattedName+"\">"+resultDetail.name+"</option>");
        }




        searchResults = jsonResponse[Object.keys(jsonResponse)[1]];
		searchResults = JSON.parse(searchResults);
		brandArr = [];
		$("#productType").append("<option value=\"Select\">--Select--</option>");
        for(var i=0; i < searchResults.length; i++){
              var resultDetail = searchResults[Object.keys(searchResults)[i]];
              $("#productType").append("<option value=\""+resultDetail.seoFormattedName+"\">"+resultDetail.name+"</option>");
        }
	},
	error : function() {
	console.log("Failed to retrieve data from server");
	}
	});
	}
