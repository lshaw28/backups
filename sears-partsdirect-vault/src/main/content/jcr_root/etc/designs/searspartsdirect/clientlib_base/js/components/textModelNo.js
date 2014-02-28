 function checkModelPartCount(searchModPar,pathTaken,textToDisplay){

        var displaySearchModPar="model no. not found";
        var displaySearchType="";
        var displayText=textToDisplay;
        var urlName="";
        var alsoSearch="";

        if(searchModPar!=null){
            displaySearchModPar=searchModPar;
            urlName="/bin/searspartsdirect/search/searchservlet?partnumber="+searchModPar;
        }

        if(pathTaken=="modelSearch"){
            displaySearchType="model";
            alsoSearch="part";
        }
        else{
            displaySearchType="part";
            alsoSearch="model";  
        }

        $('.displaySearchType').text(displaySearchType);
        $('.displaySearchModPar').text(displaySearchModPar);
        $('.textToDisplay').text(textToDisplay);

        $.ajax({
			type : "GET",
			cache : false,
			dataType : "json",
			url : urlName,
			success : function(data) {
                var moreCount=0;


                if(displaySearchType=="model"){
                    if(typeof data.partResults !== 'undefined'){
                        var jsonResponse = data.partResults;
                        jsonResponse = JSON.parse(jsonResponse);
                        moreCount=jsonResponse.length;
                        $('.alsoFound').attr("href", "/content/searspartsdirect/en/partsearchresults.html?searchModPar="+displaySearchModPar+"&pathTaken=partSearch&shdMod=&shdPart="+displaySearchModPar);
                    }
                    else{$('#weAlsoFound').hide();}
                }
                else if(displaySearchType=="part"){
                    if(typeof data.totalCount !== 'undefined'){
						moreCount = parseInt(data.totalCount);
                        $('.alsoFound').attr("href", "/content/searspartsdirect/en/modelsearchresults.html?searchModPar="+displaySearchModPar+"&pathTaken=modelSearch&shdMod="+displaySearchModPar+"&shdPart=");
				    }
                    else{$('#weAlsoFound').hide();}
                }

				$('.alsoFound').append("("+moreCount+") "+alsoSearch+" number" );
            }
        });


    }