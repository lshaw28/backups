<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.JSONObject,
				com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus" %>
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

<%
	PSFlagStatus flagStatus = sling.getService(PSFlagStatus.class);	// calling PSFlagStatus -- to get data from Felix
	JSONObject flagMessage = flagStatus.getStockAvailabilityMessage();
%>
<script>partSearchResults('<%=partNumber%>', '<%=flagMessage%>');</script>