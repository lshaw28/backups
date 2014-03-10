function getDynamicTabs(modelNumber,brandId,categoryId){
	if(modelNumber!="" && brandId!="" && categoryId!=""){
    var urlName = "/bin/searspartsdirect/model/dynamictabs?modelNumber="+encodeURIComponent(modelNumber)+"&brandId="+encodeURIComponent(brandId)+"&categoryId="+encodeURIComponent(categoryId);
    $("#headerPD").show();
    $("#dynamicTabs").empty();
    $.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					var jsonResponse = data;
                    results = jsonResponse[Object.keys(jsonResponse)[0]];
                    if(!JSON.stringify(results)){return;}
                    else{
                        results = JSON.parse(results);
                        $("#dynamicTabs").append("<li class=\"active\">Shop Parts</li>");
                        for(var i=0;i<results.length;i++){
                            if(results[i].tabName == "Manuals"){ 
                                $("#dynamicTabs").append("<li><a href=\""+results[i].legacyTabUrl+"\">"+results[i].tabName+" ("+results[i].itemCount+")</a></li>");
                            }
                            else{
                                $("#dynamicTabs").append("<li><a href=\""+results[i].legacyTabUrl+"\">"+results[i].tabName+"</a></li>");
                            }
                        }
                    }

				},
				error : function() {
					console.log("Get Dynamic Tabs -- API Failure");
				}
			});
	}
	else{
		$("#headerPD").hide();
		console.log("Get Dynamic Tabs -- parameters not valid");
	}
}


function checkCookie(modelNumber, brandId, categoryId){

	if(modelNumber!="" && brandId!="" && categoryId!=""){
    var modelsCookie=getCookie("myProfileModels");
    if (modelsCookie != ""){
        // if cookie exists, send cookie details with API
        var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+encodeURIComponent(modelNumber)+"&brandId="+brandId+"&productCategoryId="+categoryId+"&cookieId="+modelsCookie+"&flag=108";
        $.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
				// TODO: IF ANY
				console.log("STATUS 201 CREATED");
			},
			error : function() {
				console.log("Model All Diagram: checkCookie() -- API Failure");
			}
      });
    }else {      
        //if cookie doesn't exist, create new one
    	setDataForCookies(modelNumber, brandId, categoryId);
    }
	}
	else{
		console.log("check cookie-- parameters not valid");
	}
}

function setDataForCookies(modelNumber,brandId,categoryId){
	// Setting Cookie for User
	var urlName = "/bin/searspartsdirect/search/searchservlet?modelnumber="+encodeURIComponent(modelNumber)+"&brandId="+brandId+"&productCategoryId="+categoryId+"&flag=107";
	$.ajax({
		type : "GET",
		cache : false,
		dataType : "json",
		url : urlName,
		success : function(data) {
			if(typeof data.myProfileModels !== 'undefined'){
				jsonResponse = data.myProfileModels;
				setCookie("myProfileModels",jsonResponse);
			}else{
				console.log("Failed to set cookie for User -- Model All Diagram -- setDataForCookies()");
			}
		},
		error : function() {
			console.log("Model All Diagram: setDataForCookies() -- API Failure");
		}
	});
}

function setCookie(cname,cvalue){
    document.cookie = cname + "=" + cvalue + ";";
    console.log("I Own This - Cookie set successfully");
}

function getCookie(cname){
	// Get Cookie Detail from browser
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++){
		var c = ca[i].trim();
		if (c.indexOf(name)==0){ 
			console.log(c.substring(name.length,c.length));
			return c.substring(name.length,c.length);
		}
	}
	return "";
}