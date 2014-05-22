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
    <!-- I OWN THIS -->
    <div class="mainContainer">
        <div class="leftSide">
            <a id="addModelNumber" class="new-btn iOwnThisButton">I Own This</a>
        </div>
        <div class="rightSide">
            <p class="textSection">Add this model to <br class="visibleMobile" /> "My Models" <br class="visibleDesktop" />for easy access later.</p>
        </div>
        <div class="message">
            <p id="successMessage" class="success">Model #<span id="modelNumber"></span> was saved to your profile</p>
            <p id="errorMessage" class="error">Ups, an error occurred!</p>
        </div>
    </div>

    <!-- DYNAMIC TABS -->
    <div class="row-fluid">
        <div class="modelHeader">
            <ul id="dynamicTabs">
                <c:forEach var="responsiveTab" items="${responsiveTabs}">
                    <c:choose>
                        <c:when test="${!empty responsiveTabs}">
                            <c:set var="legacyTabUrl" value="${responsiveTab.legacyTabUrl}"/>
                            <li>
                                <a href="${legacyTabUrl}">${responsiveTab.tabName}</a>
                            </li>
                        </c:when>
                    <c:otherwise>
                        <!--- no responsive tabs --->
                    </c:otherwise>
                </c:choose>
            </c:forEach>
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


    <%@page import="java.net.URLDecoder,
				com.spd.cq.searspartsdirect.common.model.ResponsiveTabsModel" %>

    <!--
    All pages including this files should describe below parameters
    modelNumber, brandId, categoryId, brandName,  modelDescription.
    -->
    <spd:getResponsiveTabs formattedModelNumber="123848F401" brandId="0736" productCategoryId="1500600" />
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

    </div>
