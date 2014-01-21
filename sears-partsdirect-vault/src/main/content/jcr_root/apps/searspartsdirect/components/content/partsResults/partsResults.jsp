<%@ include file="/apps/searspartsdirect/global.jsp"%>
<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String partNumber = (request.getParameter("searchModPar") != null) ? request.getParameter("searchModPar") : "";
%>
<div class="row-fluid">
	<h2 class="resultsHeadline">(<span id="searchCount"></span>) results found for part #<strong><%= partNumber%></strong></h2>
</div>
<div class="row-fluid">
	<div class="new-span-general partListItems" id="partSearchResults">
	</div>
</div>

<script>partSearchResults('<%=partNumber%>');</script>