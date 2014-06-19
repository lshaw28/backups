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
    <!-- I OWN THIS -->
    <div class="row-fluid">
        <div class="mainContainer">
            <div class="leftSide">
                <button class="new-btn iOwnThisButton" onClick="checkCookie('${jsonResponse.modelNumber}', '${brandId}', '${categoryId}')">I Own This</button>
            </div>
            <div class="rightSide">
                <p class="textSection">Add this model to "My Models" for easy access later.</p>
            </div>
            <div class="message hideSuccess hideError">
                <p class="success"><i class="icon-check-sign"></i> Model #<c:out value="${jsonResponse.modelNumber}"/> was saved to your profile</p>
                <p class="error">Ups, an error occurred!</p>
            </div>
        </div>
    </div>

    <!-- DYNAMIC TABS -->
    <div class="row-fluid headerStyle">
        <p>Browse by section:</p>
        <div class="modelHeader">
            <ul class="dynamicTabs">
                <li class="current drop">
                    <a href="#">Shop Parts</a>
                    <!-- Begin Toggle Icon -->
                    <span class="toggle">&nbsp;</span>
                    <!-- End Toggle Icon -->
                </li>
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
            <ul class="dynamicDropDown">
                <li class="current drop">
                    <a href="#">Shop Parts</a>
                    <!-- Begin Toggle Icon -->
                    <span class="toggle">&nbsp;</span>
                    <!-- End Toggle Icon -->
                    <ul>
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
                </li>
            </ul>
        </div>
    </div>
</div>
