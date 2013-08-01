<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%--

Make sure h1 and footer link are configurable!
Carousel Shows at max 5 items, component spec sets max to display at 4

--%>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
    <spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getRelatedGuides categoryPath="${productCategoryRelation.path}"/>
<c:if test="${not empty guides && not empty productCategoryRelation}">
    <h2>
        <cq:text property="newHeader" placeholder="Most Viewed Guides" default="Most Viewed Guides"/>
    </h2>
    <div class="touch-carousel hide-onload">
        <div class="carousel-wrapper row-fluid">
            <div class="carousel-list-wrapper">
                <c:forEach var="guide" items="${guides}">
                    <div class="carousel-item span3">
                        <spd:linkResolver value="${guide.url}" />
                        <div class="wrapperGuide">
                            <div class="image-wrapper">
                                <div class="wrench-symbol">
                                    <i class="icon-wrench"></i>
                                </div>
                                <div class="image">
                                    <a href="${url}"><spd:displayImage path="${guide.imagePath}" /></a>
                                </div>
                                <div class="guideURL">
                                    <a href="${url}"><c:out value="${guide.title}" /></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:if test="${fn:length(guides) eq 4}">
        <br />
        <c:set var="suffix" value="-repair/repair-guides" />
        <spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${productCategoryRelation.trueName}${suffix}" />
        <a href="${url}" class="new-btn-small accordion-inner-btn">View All Guides</a>
    </c:if>
</c:if>