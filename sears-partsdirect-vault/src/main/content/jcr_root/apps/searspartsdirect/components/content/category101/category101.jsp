<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:tagsByPage tagType="parent_categories" />
<spd:getMultifieldCategories />
<spd:getRelation single="true" assetType="productCategory" />
<spd:getNameByNodePath nodePath="${productCategoryRelation.path}" />
<spd:uniqueID />

<c:if test="${not empty categories}">
    <div class="accordion" id="parent_${uniqueId}" >
        <div class="accordion-group">
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}" href="#${uniqueId}">
                    <c:choose>
                        <c:when test="${fn:length(parent_categoriesList[0].title) lt 38 }">
                                <cq:text property="header" placeholder="${parent_categoriesList[0].title} 101" />
                        </c:when>
                        <c:otherwise>
                                <cq:text property="header" placeholder="${fn:substring(parent_categoriesList[0].title,0,37)} 101" />
                        </c:otherwise>
                    </c:choose>
                    <i class="icon-chevron-up pull-right"></i>
		            <i class="icon-chevron-down pull-right"></i>
                </a>
            </div>
            <div id="${uniqueId}" class="accordion-body collapse in">
                <div class="accordion-inner">
                    <ul>
                        <c:forEach var="category" items="${categories}">
                            <spd:LinkResolver value="${category.url}" />
                            <li><a href="${url}">${category.title}</a></li>
                        </c:forEach>
                    </ul>
                    <c:set var="linkText"><cq:text property='viewAllLinkText'/></c:set>
                    <c:if test="${fn:length(linkText) gt 0}">
                    <c:set var="suffix" value="-repair/repair-articles" />
                    <spd:LinkResolver value="${Constants.EN_ROOT}/${nodeName}${suffix}" />
                        <a href="${url}" class="new-btn-small accordion-inner-btn">${linkText}</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

</c:if>