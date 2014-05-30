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
                ${jsonResponse.modelNumber}
                ${jsonResponse.brandName}
                ${jsonResponse.categoryName}
            </h1>
		</div>
	</div>
</div>

    <!-- DYNAMIC TABS -->
    <div class="row-fluid">
        <div class="modelHeader">
            <ul class="dynamicTabs">
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
