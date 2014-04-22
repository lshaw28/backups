<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.wcm.api.WCMMode" %><%
%>
<%

String brandImageURL=currentNode.hasNode("brandImage/file/jcr:content")?currentNode.getNode("brandImage/file/jcr:content").getProperty("jcr:data").getPath():"";
String hasEnabled = properties.get("enableTwoColumnLayout","").trim();

if (!currentNode.hasProperty("headerText") && WCMMode.fromRequest(request) != WCMMode.DISABLED) {
	  out.println("<h4>Hero Image component </h4>");
	} else {
	%>

<div class="seoHeroImageContanier row-fluid span12">

    <% if(hasEnabled.equals("true")) {%>
	<div class="heroImage span4"><cq:include path="parsys" resourceType="foundation/components/parsys" /></div>
	<div class="heroImageContent span8">
		<div class="brangHeading">
		<% if (!brandImageURL.equals("")) {%>
			<div class="span3"><img src="<%= brandImageURL %>"  height="30" width="30" /><%= properties.get("enableTwoColumnLayout","") %></div>
			<% } %>
			<h2><%=properties.get("headerText", "Find parts To Repair ") %></h2>
		</div>

		<div class="desc"><%=properties.get("desc", "") %></div>

		<div>
			<form id="seoModelSearchForm" method="post">
				<div>
					<div class="form-inline">
						<div class="input-append"><input type="text" id="seoModelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" /></div>
					</div>
					<button id="seoSearchModels" class="new-btn new-btn-search"><%=properties.get("buttonText", "Search") %></button>
				</div>
			</form>
		</div>
	</div>
<%	} else { %>
	<div class="heroImageContent row-fluid">
		<div class="brangHeading">
			<% if (!brandImageURL.equals("")) {%>
				<div class="span3"><img src="<%= brandImageURL %>"  height="30" width="30" /></div>
			<% } %>
			<h2><%=properties.get("headerText", "Find parts To Repair") %></h2>
		</div>		
		<div class="desc"><%=properties.get("desc", "") %></div>		
		<div>
			<form id="seoModelSearchForm" method="post">
				<div>
					<div class="form-inline">
						<div class="input-append"><input type="text" id="seoModelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" /></div>
					</div>
					<button id="seoSearchModels" class="new-btn new-btn-search"><%=properties.get("buttonText", "Search") %></button>
				</div>
			</form>
		</div>
	</div>
<%	} %>
</div>
<% 	} %>