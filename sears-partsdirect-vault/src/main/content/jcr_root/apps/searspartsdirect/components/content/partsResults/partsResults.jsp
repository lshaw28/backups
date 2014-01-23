<%@ include file="/apps/searspartsdirect/global.jsp"%>
<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String partNumber = (request.getParameter("searchModPar") != null) ? request.getParameter("searchModPar") : "";
%>
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1 id="partCountHeader" style="display:none;">
                (<span id="partCount"></span>) results found for part #<strong><%= partNumber%></strong>
				<p id="modelCountHeader" class="pull-right">
					We also found <span><a href="/content/searspartsdirect/en/modelsearchresults.html?searchModPar=<%=partNumber%>">(<span id="modelCount"></span>) model number</a></span>
				</p>
			</h1>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="new-span-general partListItems" id="partSearchResults">
	</div>
</div>

<script>partSearchResults('<%=partNumber%>');</script>