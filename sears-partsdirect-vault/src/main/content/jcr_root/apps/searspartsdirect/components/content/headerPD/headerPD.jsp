<%@page import="java.net.URLDecoder,
				com.spd.cq.searspartsdirect.common.model.ResponsiveTabsModel" %>

<!-- 
All pages including this files should describe below parameters
modelNumber, brandId, categoryId, brandName,  modelDescription.
 -->
<div id="headerPD">
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1>
				Model #
                ${diagramList.modelNumber}
                ${diagramList.brandName}
                ${diagramList.categoryName}
				</h1>
		</div>
	</div>
</div>


<div class="row-fluid">
	<div class="modelHeader">
		<ul id="dynamicTabs">

		</ul>
	</div>
</div>
</div>
<!--  <script>getDynamicTabs('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>');</script>
    <spd:getResponsiveTabs formattedModelNumber="10655202400" brandId="0582" productCategoryId="0165000" /> -->
    <spd:getResponsiveTabs formattedModelNumber="123848F401" brandId="0736" productCategoryId="1500600" />


<%
out.println("******************* Responsive Tabs ******************* START");
out.println("<br/>");
ResponsiveTabsModel[] responsiveTabs = (ResponsiveTabsModel[])pageContext.getAttribute("responsiveTabs");
	
for (int i=0; i < responsiveTabs.length; i++) {
    	
		out.println("Tab Name  --> " + responsiveTabs[i].getTabName());out.println("<br/>");
		out.println("Legacy Tab Url  --> " + responsiveTabs[i].getLegacyTabUrl());
		out.println("<br/>");
}
out.println("******************* Responsive Tabs ******************* END");
%>