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

<!--  
<ul class="breadcrumb"><%
    String linkClass = "hidden-phone visible-tablet visible-desktop";
    String homeClass = "visible-phone visible-tablet visible-desktop breadcrumb-back";
    String delimStr = "&nbsp;&gt;&nbsp;";
    String modelNumber = (request.getParameter("searchModPar") != null) ? request.getParameter("searchModPar") : "";
    String pathTaken = (request.getParameter("pathTaken") != null) ? request.getParameter("pathTaken") : "";
    %>
	<li class="<%=homeClass%>"><a href="http://www.searspartsdirect.com/">Home</a><%= delimStr%></li>
	<li class="<%= linkClass %>">Model Search Results for "<%= modelNumber%>"</li>
</ul>
-->

<!-- Main Result Count -->
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1 id="totalResultCount">
                (<strong id="searchCountTotal"></strong>) results found for model #<strong><%= modelNumber%></strong>
				<p id="partCountHeader" class="pull-right">
					We also found <span><a href="/content/searspartsdirect/en/partsearchresults.html?searchModPar=<%=modelNumber%>">(<span id="partCount"></span>) part number</a></span>
				</p>
			</h1>
            <h3 id="SYWHeader">In addition to model results, we found either a <strong>Sears item number,</strong> a <strong>partial model number,</strong> or a <strong>UPC code</strong> that matches the number you submitted.</h3>
		</div>
	</div>
</div>

<div class="row-fluid" id="brandRefinement">
	<div class="searchFilters">
		<h4>Refine your search results</h4>
		<div class="row-fluid">
			<div class="new-span-responsive">
				<h4>By Brand: <span class="pull-right filterClearLink"><a onclick="brandClear()" href="#">clear</a><i class="icon-remove-sign"></i></span></h4>
				<div class="brand" id="brand">
                    
				</div>
			</div>
			<div class="new-span-responsive">
				<h4>By Product Type: <span class="pull-right filterClearLink"><a onclick="productClear()" href="#">clear</a><i class="icon-remove-sign"></i></span></h4>
				<div class="productType" id="productType">
                    
				</div>
			</div>
		</div>
	</div>
</div>

<!-- SYW Section START -->
<div class="row-fluid" id="SYW1">
    <h2 class="resultsHeadline">(<strong id="searchCountSYW" class="searchCountSYW"></strong>) model results found for <strong>Sears Partial Model # <%=modelNumber%></strong></h2>
</div>

<div class="row-fluid" id="SYW2">
	<div class="resultsHeaderBar">
		<div class="la-anim-2"></div>
		<span><strong id="pageCountSYW"></strong></span>
	</div>
</div>
<div class="modelSearchResultsItemBkg" id="searchResultsUp">
</div>
<!-- SYW Section END -->

<div class="row-fluid" id="search1">
	<h2 class="resultsHeadline">(<strong id="searchCountDown"></strong>) results found for <strong>model # <%= modelNumber%></strong></h2>
</div>

<div class="row-fluid" id="search2">
	<div class="resultsHeaderBar">
		<div class="la-anim-2"></div>
		<span><strong class="pageCountResults"></strong>Sort By: <select id="sorting">
				<option value="relevance" selected>Relevance</option>
				<option value="ascending">Model number ascending</option>
				<option value="descending">Model number descending</option>
		</select> </span>
	</div>
</div>

<div class="modelSearchResultsItemBkg" id="searchResultsDown">
</div>

<div class="modelSearchResultsMain">
</div>

<div class="row-fluid" id="footer">
</div>
<div class="row-fluid modelSearchResultsNotSureMsg" id="notsure">
	<div>
		<h4>Not sure which model is yours?</h4>
		<p>
			Check your product for its unique model number or contact us at <span><a>1-800-252-1698.</a></span>
		</p>
	</div>
</div>
<script id="msr-template" type="text/x-handlebars-template">
    {{#collection tag="ul"}}
        <li class="row-fluid modelSearchResultsItem">
            <div class="span4 modelSearchResultsItemLeft">
                <p>Model <span><a>{{modelNumber}}</a></span> ({{numberOfParts}} parts)</p>
                <span class="hidden-phone"><a>View manuals</a><a>Repair Help</a></span>
            </div>
            <div class="span3 modelSearchResultsItemCenter">
                <p>{{brandName}}</p>
                <p>{{categoryName}}</p>
            </div>
            <div class="span3 hidden-phone modelSearchResultsItemRight">
                <button class="new-btn new-btn-search">Shop Parts</button>
            </div>
        </li>
    {{/collection}}
</script>
<script id="brands-template" type="text/x-handlebars-template">
    {{#collection tag="select" id="brandSelect"}}
        <option value="{{id}}">{{name}}</option>
    {{/collection}}
</script>
<script id="products-template" type="text/x-handlebars-template">
    {{#collection tag="select" id="productSelect"}}
        <option value="{{id}}">{{name}}</option>
    {{/collection}}
</script>
<script id="footer-template" type="text/x-handlebars-template">
    <div class="resultsFooterBar">
        <div class="row-fluid">
            <div class="span3 resultsFooterLeft visible-desktop">
                <strong class="pageCountResults"></strong>
            </div>
            <div class="span6 resultsFooterNav">
               <a class="resultsFooterButtonLeft">
                    <span><i class="icon-chevron-right"></i>
                    <p>Previous</p></span>
                </a>
                <select id="pageNumber" class="resultsFooterNavSelect">

                </select>
                <a class="resultsFooterButtonRight">
                    <span><p>Next</p>
                    <i class="icon-chevron-left"></i></span>
                </a>
            </div>
        </div>
    </div>
 </script>