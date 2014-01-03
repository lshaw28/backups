<%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.WCMMode,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image" %>
<%@include file="/libs/foundation/global.jsp"%>

<%
    Image image = new Image(resource, "image");

    if (image.hasContent() || WCMMode.fromRequest(request) == WCMMode.EDIT) {
        image.loadStyleData(currentStyle);
        // add design information if not default (i.e. for reference paras)
        if (!currentDesign.equals(resourceDesign)) {
            image.setSuffix(currentDesign.getId());
        }
        //drop target css class = dd prefix + name of the drop target in the edit config
        image.addCssClass(DropTarget.CSS_CLASS_PREFIX + "image");
        image.setSelector(".img");
        image.setDoctype(Doctype.fromRequest(request));

        String divId = "cq-textimage-jsp-" + resource.getPath();
        String imageHeight = image.get(image.getItemName(Image.PN_HEIGHT));
        // div around image for additional formatting
        %>



<%}%>

<div class="row-fluid lookUpBuckets">
	<div class="span6 lookUpBucket">
		<h2>Browse Models</h2>
		<p>Find parts by product type and brand</p>
		<div class="lookUpSelect">
			<select class="productType" id="productType">
                    
			</select>
		</div>
		<div class="lookUpSelect">
			<select class="brand" id="brand">
                    
			</select>
		</div>
		<a class="new-btn new-btn-square" href="#">Find Models</a>
	</div>
	<div class="span6 lookUpBucket">
		<h2>Sears Purchase Look up</h2>
        <div class="searchGraphic"><% image.draw(out); %></div>
		<p class="purchaseLookUpCopy">Find model numbers for products you've purchased or had serviced by Sears</p>
		<p>It's easy! Start here:</p>
		<a class="searchGraphicCTAButton new-btn new-btn-square" href="#">Find my models</a>
	</div>
</div>

<script>
	 $(document).ready(function(){
    	fillDropdowns();
    });
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


</script>










