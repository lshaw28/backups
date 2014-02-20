<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@page import="java.net.URLDecoder,
				org.apache.sling.commons.json.JSONObject,
				com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus" %>
<cq:includeClientLib categories="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String description = (request.getParameter("description") != null) ? request.getParameter("description") : "";
String brandName = (request.getParameter("brandName") != null) ? URLDecoder.decode(request.getParameter("brandName"), "UTF-8") : "";
String modelDescription = (request.getParameter("modelDescription") != null) ? URLDecoder.decode(request.getParameter("modelDescription"), "UTF-8") : "";
%>
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1>
				Model # <%=modelNumber %> <%=brandName %> <%=modelDescription %></h1>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="searchContainer span12">

		<div class="span6">
			<div class="modelNumberSearch">
				<div class="searchHeading">
					<p class="searchHeading">Search by part name</p>
				</div>
				<div class="modelNumberSearchInputs">
					<div class="form-inline">
						<div class="input-append">
							<input type="text" id="searchPart" name="" maxlength="42"
								data-inputhelp="ex. screw, gate, switch"
								data-inputhelpmobile="ex. screw, gate, switch"
								value="ex. screw, gate, switch"/> <span class="add-on"><button
									id="searchByPartName" onclick="showSearchByPartName('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=brandName %>', '<%=modelDescription %>', $('#searchPart').val())">>
									<i class="icon-search">&nbsp;</i>
								</button></span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="span6">
			<div class="modelNumberSearch">
				<p class="searchHeading">Popular searches:</p>
				<ul class="columns">
					<li><a href="#">Screw</a></li>
					<li><a href="#">Switch</a></li>
					<li><a href="#">Control panel</a></li>
					<li><a href="#">Turn key</a></li>
					<li><a href="#">Hob Nob</a></li>
					<li><a href="#">Broomstick</a></li>
				</ul>
			</div>
		</div>

	</div>
</div>
<div class="row-fluid" id="searchCountDiv" style="display:none;">
	<h2 class="resultsHeadline"><span id="searchCount"></span> parts found for "<%= description%>"</h2>
</div>
<div class="row-fluid">
	<div class="new-span-general partListItems" id="partNameResults">
	</div>
</div>
<%
	PSFlagStatus flagStatus = sling.getService(PSFlagStatus.class);	// calling PSFlagStatus -- to get data from Felix
	JSONObject flagMessage = flagStatus.getStockAvailabilityMessage();
%>

<script>modelSearchByPartName('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=description%>', '<%=flagMessage%>');
</script>

