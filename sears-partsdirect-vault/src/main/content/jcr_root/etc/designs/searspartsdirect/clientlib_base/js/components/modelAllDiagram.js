function allModelDiagram(modelNumber, formattedModelNumber, brandId, productCategoryId, topSellingImagePath, brandName, modelDescription) {
	var urlName = "/bin/searspartsdirect/model/modelalldiagram?modelnumber="+formattedModelNumber+"&brandId="+brandId+"&productCategoryId="+productCategoryId;
	
	$.ajax({
				type : "GET",
				cache : false,
				dataType : "json",
				url : urlName,
				success : function(data) {
					if(typeof data.jsonAllDiagram !== 'undefined'){
						var jsonAllDiagram = data.jsonAllDiagram;
                		jsonAllDiagram = JSON.parse(jsonAllDiagram);
						var jsonResponse = jsonAllDiagram.components;
						var jsonLength = jsonResponse.length;
						var jsonEngine = (typeof jsonAllDiagram.engineModel !== 'undefined') ? jsonAllDiagram.engineModel : "";
						var engineLength = jsonEngine.length;
						
						var waterFilterPart = (typeof data.waterFilterPart !== 'undefined') ? data.waterFilterPart : "";
			            var topSellingParts = (typeof data.parts !== 'undefined') ? data.parts : "";
			            var topSellingDiv="";
			            
			            if(topSellingParts.length !=0){
			                for(var j = 0; j < topSellingParts.length; j++) {
			                    topSellingDiv = topSellingDiv + "<li><a href=\"http://www.searspartsdirect.com/partsdirect/part-number/"+topSellingParts[j].partCompositeKey.partNumber+"/"+topSellingParts[j].partCompositeKey.productGroupId+"/"+topSellingParts[j].partCompositeKey.supplierId+"\">"+topSellingParts[j].description+"</a></li>";
			                }
			            }

			            var engineDiv = "";
			            if(engineLength != 0){
			            	engineDiv = "<div> <a href=\http://www.searspartsdirect.com/partsdirect/part-model/" + jsonEngine.brandName + "-Parts/All-Products-Parts/Model-" + jsonEngine.engineModel + "/" + jsonEngine.brandId + "/" + jsonEngine.categoryId  + ">View all engine parts </a>for " + jsonEngine.brandName + " Engine Model Number " + jsonEngine.engineModel + "</div>";
			            	 $("#engineModelsContainer").append(engineDiv);
						}
			           
			            if(jsonLength != 0){
						     for(var j = 0; j < jsonResponse.length; j++) {
						    	 var topPartsList = "";

		                         if(jsonResponse[j].parts.length > 0){
						    		 topPartsList = "<ul class=\"topParts-list\">"
											+ "<p>Top parts in this diagram</p>";
						    		 for(var i = 0; i<jsonResponse[j].parts.length; i++){
						    			 var jsonTopParts = jsonResponse[j].parts[i].description;
						    			 topPartsList = topPartsList + "<li><a class=\"disableMobile\" href=\"#\">"+jsonTopParts+"</a></li>";
						    		 }
						    		 topPartsList = topPartsList + "</ul>";
						    	 }

		                         $("#allDiagramContainer").append("<li class=\"grid-item\">"
                                         +"<a class=\"disableDesktop\" href=\"/content/searspartsdirect/en/modelpartlist.html"
                                         + "?modelNumber="+modelNumber+"&formattedModelNumber="+formattedModelNumber+"&brandId="+brandId+"&categoryId="+productCategoryId+"&brandname="+brandName+"&modelDescription="+modelDescription+"&diagramPageId="+jsonResponse[j].diagramPageId+"&documentId="+jsonResponse[j].documentId+"&diagramUrl="+jsonResponse[j].diagramImageUrl+"\">"
                                         + "<span class=\"diagramContainer model\">"
                                         + "<img height=\"100px\" width=\"100px\" src=\""+jsonResponse[j].diagramImageUrl+"\" />"
                                         + "<p class=\"diagramTitle\">"+jsonResponse[j].componentDescription+"</p>"
                                         + topPartsList
                                         + "</span></a></li>");

								if(waterFilterPart.length!=0 && topSellingParts.length!=0){
									if(j==0){
										/* Water Filter Part */
										addWaterFilterPart(waterFilterPart.partImage.imageURL, waterFilterPart.description);
									}
									else if(j==1){
										/* Top Selling Part */
										addTopSellingPart(topSellingImagePath, topSellingDiv);
									}
								 }
								 else if(j==0 && waterFilterPart.length!=0 && topSellingParts.length==0){
									 addWaterFilterPart(waterFilterPart.partImage.imageURL, waterFilterPart.description);
		                         }
		                         else if(j==0&&topSellingParts.length!=0 &&  waterFilterPart.length==0){
		                        	 addTopSellingPart(topSellingImagePath, topSellingDiv);
								}
							}
						}
						
                	}
				},
				error : function() {
					console.log("Model All Diagram -- API Failure");
				}
			});
}

function addWaterFilterPart(waterFilterImage, waterFilterDescription){
	$("#allDiagramContainer").append("<li class=\"grid-item\"><a class=\"disableDesktop\" href=\"\">"
			+ "<span class=\"diagramContainer model\">"
			+ "<img src=\""+waterFilterImage+"\" />"
			+ "<p class=\"diagramTitle\"><a class=\"disableMobile\" href=\"/content/searspartsdirect/en/modelpartlist.html\">"+waterFilterDescription+"</a></p></a>"
			+ "</span></a></li>");
}

function addTopSellingPart(topSellingPartImage, topSellingDiv){
	$("#allDiagramContainer").append("<li class=\"grid-item\"><a class=\"disableDesktop\" href=\"#\">"
			+"<span class=\"diagramContainer topParts\">"
			+"<img src=\""+topSellingPartImage+"\" />"
			+"<h5 class=\"diagramTitle disableMobile\">Top parts in this model</h5>"
			+"<ul class=\"topParts-list\">"
			+topSellingDiv
			+"</ul>"
			+"</span>"
			+"</a></li>");
}

function showSearchByPartName(modelNumber, formattedModelNumber, brandId, categoryId, brandName, modelDescription, description){
	window.location.href = "/content/searspartsdirect/en/searchbypartname.html"
		+ "?modelNumber="
		+ modelNumber
		+ "&formattedModelNumber="
		+ formattedModelNumber
		+ "&brandId="
		+ brandId
		+ "&categoryId="
		+ categoryId
		+ "&description="
		+ description
		+ "&brandName="
		+ brandName
		+ "&modelDescription=" + modelDescription;
}

