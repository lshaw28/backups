
function modelSearchResults(searchModPar,pathTaken){


    var urlName = "http://api.developer.searsPartsDirect.com/v1/productdetails?store=Sears&partNumber="
		+ searchModPar+"&pathTaken="+pathTaken+ "&apiVersion=v3&contentType=json";

 	 //	var cqresponse = CQ.HTTP.get("urlName");
	 //	var json = eval(cqresponse);
	 //	var jsonResponse = json.responseText;
     //	var JSONObj = JSON.parse(jsonResponse);
	 //	var jsonLength = Object.keys(JSONObj).length;


    var jsonResponse=[{"id":0,"modelNumber":"Model0","formattedModelNumber":"Model0","modelDescription":"ModelDescription0","brandId":"brandId0","brandName":"Brand0","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand0","categoryId":"CategoryId0","categoryName":"Category0","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType0","pages":0,"parts":0,"soldCount":0,"addedDate":null,"updatedDate":null,"hasManuals":true,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="},{"id":1,"modelNumber":"Model1","formattedModelNumber":"Model1","modelDescription":"ModelDescription1","brandId":"brandId1","brandName":"Brand1","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand1","categoryId":"CategoryId1","categoryName":"Category1","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType1","pages":1,"parts":1,"soldCount":10,"addedDate":null,"updatedDate":null,"hasManuals":true,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="},{"id":2,"modelNumber":"Model2","formattedModelNumber":"Model2","modelDescription":"ModelDescription2","brandId":"brandId2","brandName":"Brand2","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand2","categoryId":"CategoryId2","categoryName":"Category2","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType2","pages":2,"parts":2,"soldCount":20,"addedDate":null,"updatedDate":null,"hasManuals":false,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="},{"id":3,"modelNumber":"Model3","formattedModelNumber":"Model3","modelDescription":"ModelDescription3","brandId":"brandId3","brandName":"Brand3","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand3","categoryId":"CategoryId3","categoryName":"Category3","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType3","pages":3,"parts":3,"soldCount":30,"addedDate":null,"updatedDate":null,"hasManuals":false,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="},{"id":4,"modelNumber":"Model4","formattedModelNumber":"Model4","modelDescription":"ModelDescription4","brandId":"brandId4","brandName":"Brand4","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand4","categoryId":"CategoryId4","categoryName":"Category4","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType4","pages":4,"parts":4,"soldCount":40,"addedDate":null,"updatedDate":null,"hasManuals":false,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="},{"id":5,"modelNumber":"Model5","formattedModelNumber":"Model5","modelDescription":"ModelDescription5","brandId":"brandId5","brandName":"Brand5","formattedBrandName":null,"seoFormattedBrandName":"SEOBrand5","categoryId":"CategoryId5","categoryName":"Category5","formattedCategoryName":null,"seoFormattedProductTypeName":"SEOProductType5","pages":5,"* Connection #0 to host localhost left intact parts":5,"soldCount":50,"addedDate":null,"updatedDate":null,"hasManuals":false,"modelSubComponentsLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700?searchedModel=1230&blt=","modelDiagramLink":"http://www.searspartsdirect.com/partsdirect/part-model/Peerless-Parts/Faucet-Parts/Model-1230/0796/0909700/00053310/00001?blt=06&prst=&shdMod="}];
    var len = Object.keys(jsonResponse).length;
    for(var i=0; i< len; i++){
        //alert(Object.keys(jsonResponse)[i]);
        //alert(jsonResponse[Object.keys(jsonResponse)[i]].modelNumber);
       var resultDetail=jsonResponse[Object.keys(jsonResponse)[i]];
       $( ".modelSearchResultsItemBkg" ).append(""+
			"<div class=\"row-fluid modelSearchResultsItem\">"+
				"<div class=\"span4 modelSearchResultsItemLeft\">"+
					"<p>Model <span><a href=\""+resultDetail.modelSubComponentsLink+"\">"+resultDetail.modelNumber+"</a></span> ("+resultDetail.parts+" parts)</p>"+
					"<span class=\"hidden-phone\"><a>View manuals</a><a>Repair	Help</a></span>"+
				"</div>"+
				"<div class=\"span3 modelSearchResultsItemCenter\">"+
					"<p>Kenmore Refrigerator</p>"+
					"<p>"+resultDetail.modelDescription+"</p>"+
				"</div>"+
				"<div class=\"span3 hidden-phone modelSearchResultsItemRight\">"+
					"<button class=\"new-btn new-btn-search\">Shop Parts</button>"+
				"</div>"+
			"</div>");
    }


}