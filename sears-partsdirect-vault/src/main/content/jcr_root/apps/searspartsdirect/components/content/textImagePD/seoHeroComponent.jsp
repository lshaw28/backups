<%@include file="/libs/foundation/global.jsp"%>
<%@ page session="false" import="org.apache.sling.api.resource.ResourceUtil" %>
<%-- [Mingle #8078] - SEO: Landing page - Hero image component --%>
<%	
    String brandImageURL=currentNode.hasNode("image/file/jcr:content")?currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath():"";
    String brandName = currentPage.getProperties().get("brandName","");
    String productType = currentPage.getProperties().get("productType","");    
	Resource myResource = resource.getResourceResolver().getResource(currentNode.getPath());
	Resource imageResource = resource.getResourceResolver().getResource(currentNode.getPath()+"image/file/jcr:content");
	final ValueMap values = myResource.adaptTo(ValueMap.class);
	boolean hasEnabled = values.get("enableTwoColumnLayout","").equals("true");
%>

<div class="seoHeroImageContanier row-fluid span12">
<%--  if two column layout is enabled we need show product type image --%>
    <% if(hasEnabled) {%>
	<div class="heroImage span4 hidden-phone"><cq:include path="responsiveImage" resourceType="searspartsdirect/components/content/responsiveImage" /></div>
	<div class="heroImageContent span8">
		<div class="brandHeading span12">
			<% if (!brandImageURL.equals("")) {%> <div class="brandImage span3"><img src="<%= brandImageURL %>" /></div> <% } %>
			<div class="span9"><h2><%=properties.get("headerText", "Find Parts To Repair ") %><%= brandName %>&nbsp;<%= productType %></h2></div>
		</div>
		<div class="brandDesc"><%=properties.get("description", "") %></div>
		<div class="seoForm">
			<form id="seoModelSearchForm" method="post">
				<div class="input-append seoInput"><input type="text" id="seoModelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" /></div>
				<button id="seoSearchModels" class="new-btn new-btn-search seoBtn"><%=properties.get("buttonText", "Find My Model ") %></button>
			</form>
		</div>
	</div>
	<%--  if two column layout is disabled we need hide product type image --%>
<%	} else { %>
	<div class="heroImageContent row-fluid singleColumnLayout">
		<div class="brandHeading span12">
			<% if (!brandImageURL.equals("")) {%> <div class="brandImage span3"><img src="<%= brandImageURL %>" /></div> <% } %>
			<div class="span9"><h2><%=properties.get("headerText", "Find Parts To Repair ") %><%= brandName %>&nbsp;<%= productType %></h2></div>
		</div>
		<div class="brandDesc"><%=properties.get("description", "") %></div>
		<div class="seoForm">
			<form id="seoModelSearchForm" method="post">
				<div class="input-append seoInput seoInputSingleColumn"><input type="text" id="seoModelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" /></div>
				<button id="seoSearchModels" class="new-btn new-btn-search seoBtn"><%=properties.get("buttonText", "Find My Model ") %></button>
			</form>
		</div>
	</div>
<%	} %>
</div>	