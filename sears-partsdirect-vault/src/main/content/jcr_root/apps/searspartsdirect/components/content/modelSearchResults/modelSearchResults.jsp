<%@ include file="/apps/searspartsdirect/global.jsp"%>
<script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/modelSearchResults.js" ></script>

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
			<h1>
                (<strong id="searchCountTotal" style="display:none;"></strong>) results found for model #<strong><%= searchModPar%></strong>
				<p class="pull-right">
					We also found <span><a>(1) part number</a></span>
				</p>
			</h1>
            <h3 id="SYWHeader" style="display:none;">In addition to model results, we found either a <strong>Sears item number,</strong> a <strong>partial model number,</strong> or a <strong>UPC code</strong> that matches the number you submitted.</h3>
		</div>
	</div>
</div>

<!-- SYW Section START -->
<div class="row-fluid" id="SYW1" style="display:none;">
    <h2 class="resultsHeadline">(<strong id="searchCountSYW" class="searchCountSYW"></strong>) model results found for <strong>Sears Partial Model # <%=searchModPar%></strong></h2>
</div>
<div class="row-fluid" id="SYW2" style="display:none;">
	<div class="searchFilters">
		<h4>Refine your search results</h4>
		<div class="row-fluid">
			<div class="span3">
				<h4>By Brand:</h4>
				<select class="brand" id="sywBrand">
                    <option value="Select">--Select--</option>
				</select>
			</div>
			<div class="span3">
				<h4>By Product Type:</h4>
				<select class="product" id="sywProductType">
                    <option value="Select">--Select--</option>
				</select>
			</div>
		</div>
	</div>
</div>
<div class="row-fluid" id="SYW3" style="display:none;">
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
	<div class="searchFilters">
		<h4>Refine your search results</h4>
		<div class="row-fluid">
			<div class="span3">
				<h4>By Brand:</h4>
				<select class="brand" id="brand">
                    <option value="Select">--Select--</option>
				</select>
			</div>
			<div class="span3">
				<h4>By Product Type:</h4>
				<select class="product" id="productType">
                    <option value="Select">--Select--</option>
				</select>
			</div>
		</div>
	</div>
</div>

<div class="row-fluid" id="search3" style="display:none;">
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
var index=0;

    $(document).ready(function(){
    	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
        populateBrandProductDetails('<%=searchModPar%>');
    });
    
    $("#pageNumber").change(function () {
        index = $(this).children(":selected").index();
        flag=1;
        modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
    });
    
    $("#sorting").change(function () {
        index = $(this).children(":selected").index();
        flag=2;
        modelSearchResults('<%=searchModPar%>','<%=pathTaken%>',flag,index);
        $('#pageNumber').prop('selectedIndex', 0);

    });
    
    $("#brand").change(function () {
        index = $(this).val();
        var productIndex = $("#productType").children(":selected").index();
        var productSelected = $("#productType").val();
        flag = 3;
        if(productIndex == 0){
        	fillDropdown('<%=searchModPar%>', index, 'brand', 'productType');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index, productSelected);
        }
    });
    
    $("#productType").change(function () {
        index = $(this).val();
        var brandIndex = $("#brand").children(":selected").index();
        var brandSelected = $("#brand").val();
        flag = 4;
        if(brandIndex == 0){
        	fillDropdown('<%=searchModPar%>', index, 'productType', 'brand');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index, brandSelected);
        }
    });
    
    $("#sywBrand").change(function () {
        index = $(this).val();
        var productIndex = $("#sywProductType").children(":selected").index();
        var productSelected = $("#sywProductType").val();
        flag = 5;
        if(productIndex == 0){
        	fillDropdown('<%=searchModPar%>', index, 'brand', 'sywProductType');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index, productSelected);
        }
    });
    
    $("#sywProductType").change(function () {
        index = $(this).val();
        var brandIndex = $("#sywBrand").children(":selected").index();
        var brandSelected = $("#sywBrand").val();
        flag = 6;
        if(brandIndex == 0){
        	fillDropdown('<%=searchModPar%>', index, 'productType', 'sywBrand');
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index);
        }else{
        	modelSearchResults('<%=searchModPar%>','<%=pathTaken%>', flag, index, brandSelected);
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