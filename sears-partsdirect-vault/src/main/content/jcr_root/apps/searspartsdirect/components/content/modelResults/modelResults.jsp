<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%@page import="com.spd.cq.searspartsdirect.common.helpers.PSsettingsHelper"%>
<%
PSsettingsHelper shObject =  sling.getService(PSsettingsHelper.class);
String apiKey="";
 
if(shObject.getPartsDirectProductAPI()!=null){
	apiKey = shObject.getPartsDirectProductAPI();
}
// above api if fetched from felix to be used
%>

<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<!--  
<ul class="breadcrumb"><%
    String linkClass = "hidden-phone visible-tablet visible-desktop";
    String homeClass = "visible-phone visible-tablet visible-desktop breadcrumb-back";
    String delimStr = "&nbsp;&gt;&nbsp;";
    String searchModPar = (request.getParameter("searchModPar") != null) ? request.getParameter("searchModPar") : "";
    String pathTaken = (request.getParameter("pathTaken") != null) ? request.getParameter("pathTaken") : "";
    %>
	<li class="<%=homeClass%>"><a href="http://www.searspartsdirect.com/">Home</a><%= delimStr%></li>
	<li class="<%= linkClass %>">Model Search Results for "<%= searchModPar%>"</li>
</ul>
-->

<!-- Main Result Count -->
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1 id="totalResultCount" style="display:none;">
                (<strong id="searchCountTotal"></strong>) results found for model #<strong><%= searchModPar%></strong>
				<p class="pull-right">
					We also found <span><a>(1) part number</a></span>
				</p>
			</h1>
            <h3 id="SYWHeader" style="display:none;">In addition to model results, we found either a <strong>Sears item number,</strong> a <strong>partial model number,</strong> or a <strong>UPC code</strong> that matches the number you submitted.</h3>
		</div>
	</div>
</div>

<div class="row-fluid" id="brandRefinement" style="display:none;">
	<div class="searchFilters">
		<h4>Refine your search results</h4>
		<div class="row-fluid">
			<div class="span3 new-span-responsive">
				<h4>By Brand: <span class="pull-right filterClearLink"><a onclick="brandClear()" href="#">clear</a><i class="icon-remove-sign"></i></span></h4>
				<select class="brand" id="brand">
                    
				</select>
			</div>
			<div class="span3 new-span-responsive">
				<h4>By Product Type: <span class="pull-right filterClearLink"><a onclick="productClear()" href="#">clear</a><i class="icon-remove-sign"></i></span></h4>
				<select class="productType" id="productType">
                    
				</select>
			</div>
		</div>
	</div>
</div>

<!-- SYW Section START -->
<div class="row-fluid" id="SYW1" style="display:none;">
    <h2 class="resultsHeadline">(<strong id="searchCountSYW" class="searchCountSYW"></strong>) model results found for <strong>Sears Partial Model # <%=searchModPar%></strong></h2>
</div>

<div class="row-fluid" id="SYW2" style="display:none;">
	<div class="resultsHeaderBar">
		<div class="la-anim-2"></div>
		<span><strong id="pageCountSYW"></strong></span>
	</div>
</div>
<div class="modelSearchResultsItemBkg" id="searchResultsUp" style="display:none;">
</div>
<!-- SYW Section END -->

<div class="row-fluid" id="search1" style="display:none;">
	<h2 class="resultsHeadline">(<strong id="searchCountDown"></strong>) results found for <strong>model # <%= searchModPar%></strong></h2>
</div>

<div class="row-fluid" id="search2" style="display:none;">
	<div class="resultsHeaderBar">
		<div class="la-anim-2"></div>
		<span><strong class="pageCountResults"></strong>Sort by <select id="sorting">
				<option value="relevance" selected>Relevance</option>
				<option value="asscending">Model number asscending</option>
				<option value="descending">Model number descending</option>
		</select> </span>
	</div>
</div>

<div class="modelSearchResultsItemBkg" id="searchResultsDown" style="display:none;">
</div>

<div class="row-fluid" id="footer" style="display:none;">
	<div class="resultsFooterBar">
		<div class="row-fluid">
			<div class="span3 resultsFooterLeft hidden-phone">
				<strong class="pageCountResults"></strong>
			</div>
			<div class="span6 resultsFooterNav">
				<a onclick='paging(-1)'> <i class="icon-chevron-right"></i> <span
					class="hidden-phone">Previous</span>
				</a> <select id="pageNumber">
					
				</select> <a onclick='paging(1)'> <span class="hidden-phone">Next</span> <i
					class="icon-chevron-left"></i>
				</a>
			</div>
		</div>
	</div>
</div>
<div class="row-fluid modelSearchResultsNotSureMsg" id="notsure" style="display:none;">
	<div>
		<h4>Not sure which model is yours?</h4>
		<p>
			Check your product for its unique model number or contact us at <span><a>1-800-252-1698.</a></span>
		</p>
	</div>
</div>
<script>
var flag =0;
var selectedValue=0;

    $(document).ready(function(){
    	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
        populateBrandProductDetails('<%=searchModPar%>');
    });
    
    $("#pageNumber").change(function () {
    	selectedValue = $(this).children(":selected").index();
        flag=1;
        modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
    });
    
    $("#sorting").change(function () {
    	selectedValue = $(this).children(":selected").index();
        flag=2;
        modelSearchResults('<%=searchModPar%>','<%=pathTaken%>',flag, selectedValue);
        $('#pageNumber').prop('selectedIndex', 0);

    });

    function brandClear(){
        // will fill it again on the basis of modelNumber and productType selected
        // if No productType is selected, we will fill it again with modelNumber

            //$("#brand").empty();
            var productSelected = $("#productType option:selected").text();
          // other dropdown should contain all the options
            populateBrandProductDetails('<%=searchModPar%>', 'productType', 'false');
        if(productSelected != '--Select--'){
                selectedValue = 0;
                //console.log("selected product:"+productSelected);
                flag = 3;
        fillDropdown('<%=searchModPar%>', productSelected, 'productType', 'brand');
                // model search results with modelNumber, productType
                modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue, productSelected);
        }else{
                flag = 0;
                populateBrandProductDetails('<%=searchModPar%>', 'brand', 'true');
                // model search results with modelNumber
                modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
        }
        // filling another dropdown again
        }

    function productClear(){
        // will fill it again on the basis of modelNumber and brand selected
        // if No brand is selected, we will fill it again with modelNumber
            //$("#productType").empty();
        var brandSelected = $("#brand option:selected").text();
        // other dropdown should contain all the options
            populateBrandProductDetails('<%=searchModPar%>', 'brand', 'false');
        if(brandSelected != '--Select--'){
        selectedValue = 0;
                //console.log("selected brand:"+brandSelected);
                flag = 4;
        fillDropdown('<%=searchModPar%>', brandSelected, 'brand', 'productType');
                modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue, brandSelected);
        }else{
    // nothing is selected in brand dropdown
    flag = 0;
                populateBrandProductDetails('<%=searchModPar%>', 'productType', 'true');
                modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
        }
        // filling another dropdown again
    }

    
    $("#brand").change(function () {
    	selectedValue = $("#brand option:selected").text();

        var productIndex = $("#productType").children(":selected").index();
        var productSelected = $("#productType option:selected").text();
        flag = 3;
        if(selectedValue == '--Select--'){
        	selectedValue = 0;
        }
        if(productIndex == 0){
        	fillDropdown('<%=searchModPar%>', selectedValue, 'brand', 'productType');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue, productSelected);
        }
    });

    $("#productType").change(function () {
    	selectedValue = $("#productType option:selected").text();

        var brandIndex = $("#brand").children(":selected").index();
        var brandSelected = $("#brand option:selected").text();
        flag = 4;
        if(selectedValue == '--Select--'){
        	selectedValue = 0;
        }
        if(brandIndex == 0){
        	fillDropdown('<%=searchModPar%>', selectedValue, 'productType', 'brand');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, selectedValue, brandSelected);
        }
    });
    
    function paging(vary){
    	   var selectedIndex = $('#pageNumber').prop('selectedIndex');
    	   var ddLength = $("#pageNumber option").length;
    	    var n=selectedIndex+vary;
    	    if(n<0||n>=ddLength){return;}
    	    else{
    	        $('#pageNumber').prop('selectedIndex', n);
    	        $("#pageNumber").change();
    	    }
    	}
</script>