<%@ include file="/apps/searspartsdirect/global.jsp"%>

<ul class="breadcrumb"><%
    String linkClass = "hidden-phone visible-tablet visible-desktop";
    String homeClass = "visible-phone visible-tablet visible-desktop breadcrumb-back";
    String delimStr = "&nbsp;&gt;&nbsp;";
    String searchModPar = (request.getParameter("searchModPar") != null) ? request.getParameter("searchModPar") : "";
    String pathTaken = (request.getParameter("pathTaken") != null) ? request.getParameter("pathTaken") : "";
    %>
    <script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/modelSearchResults.js" ></script>
    
	<li class="<%=homeClass%>"><a href="http://www.searspartsdirect.com/">Home</a><%= delimStr%></li>
	<li class="<%= linkClass %>">Model Search Results for "<%= searchModPar%>"</li>
</ul>

<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1>
                (<strong class="searchCount0"></strong>) results found for model #<strong><%= searchModPar%></strong>
				<p class="pull-right">
					We also found <span><a>(1) part number</a></span>
				</p>
			</h1>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="searchFilters">
		<h4>Refine your search results</h4>
		<div class="row-fluid">
			<div class="span3">
				<h4>By Brand:</h4>
				<select>
					<option value="relevance">Relevance</option>
					<option value="price">Price</option>
					<option value="popularity">Popularity</option>
				</select>
			</div>
			<div class="span3">
				<h4>By Product Type:</h4>
				<select>
					<option value="relevance">Relevance</option>
					<option value="price">Price</option>
					<option value="popularity">Popularity</option>
				</select>
			</div>
			<div class="span3">
				<h4>By XXXXXXX:</h4>
				<select>
					<option value="relevance">Relevance</option>
					<option value="price">Price</option>
					<option value="popularity">Popularity</option>
				</select>
			</div>
		</div>
	</div>

</div>
<div class="row-fluid">
	<div class="resultsHeaderBar">
		<div class="la-anim-2"></div>
		<span><strong class="searchCount1"></strong>Sort by <select>
				<option value="relevance" selected>Relevance</option>
				<option value="price">Price</option>
				<option value="popularity">Popularity</option>
		</select> </span>
	</div>
</div>
<div class="modelSearchResultsItemBkg">
	
</div>
<div class="row-fluid">
	<div class="resultsFooterBar">
		<div class="row-fluid">
			<div class="span3 resultsFooterLeft hidden-phone">
				<strong class="searchCount1"></strong>
			</div>
			<div class="span6 resultsFooterNav">
				<a> <i class="icon-chevron-right"></i> <span
					class="hidden-phone">Previous</span>
				</a> <select>
					<option value="1" selected>Page 1</option>
					<option value="2">Page 2</option>
					<option value="3">Page 3</option>
				</select> <a> <span class="hidden-phone">Next</span> <i
					class="icon-chevron-left"></i>
				</a>
			</div>
		</div>
	</div>
</div>
<div class="row-fluid modelSearchResultsNotSureMsg">
	<div>
		<h4>Not sure which model is yours?</h4>
		<p>
			Check your product for its unique model number or contact us at <span><a>1-800-252-1698.</a></span>
		</p>
	</div>
</div>
<script>
    $(document).ready(function(){
        modelSearchResults('<%=searchModPar%>','<%=pathTaken%>');
    });
</script>