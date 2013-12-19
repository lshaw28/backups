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
<div class="span2">
	<spd:displayImage path="image"/>
</div>

<c:set var="searchInsTitle"><cq:text property="searchInstructionsTitle" placeholder=""/></c:set>
	<c:if test="${empty searchInsTitle}"><c:set var="searchInsTitle">Double Check your Model Number</c:set></c:if>
	<p><c:out value="${searchInsTitle}" /></p>

<c:set var="searchInstructions"><cq:text property="searchInstructions" placeholder="" /></c:set>
<c:if test="${empty searchInstructions}"><c:set var="searchInstructions">Enter your whole model number, including dashes (-) and periods (.). Watch out for characters that look similar, such as the letter "O" and the numeral "0".</c:set></c:if>
	<p><c:out value="${searchInstructions}" /></p>

<c:set var="linkText"><cq:text property="linkText" placeholder="" /></c:set>
<c:if test="${empty linkText}"><c:set var="linkText" value="model number finder" /></c:if>
<c:set var="linkText"><cq:text property="linkText" placeholder=""/></c:set><%--
	--%><c:if test="${empty linkText}"><c:set var="linkText">model number finder</c:set></c:if><%--
	--%><p><a href="#findMyModel" class="searchPanelFinder_js"><c:out value="${linkText}" /></a></p>

<script>
var flag =0;
var index=0;
$(document).ready(function(){
    modelSearchResults('<%=searchModPar%>','<%=pathTaken%>',flag,index);
    modelBrandResults('<%=searchModPar%>');
    modelProductResults('<%=searchModPar%>');
});

</script>