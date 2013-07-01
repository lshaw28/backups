<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getMultifieldCategories />
<spd:getRelation single="true" assetType="productCategory" />
<spd:getNameByNodePath nodePath="${productCategoryRelation.path}" />

<c:if test="${not empty categories}">
	<c:choose>
		<c:when test="${fn:length(productCategoryRelation.title) lt 38 }">
			<cq:text property="header" placeholder="${productCategoryRelation.title} 101" />
		</c:when>
		<c:otherwise>
			<cq:text property="header" placeholder="${fn:substring(productCategoryRelation.title,0,37)} 101" />
		</c:otherwise>
	</c:choose>
	<ul>
		<c:forEach var="category" items="${categories}">
			<spd:LinkResolver value="${category.url}" />
			<li><a href="${url}">${category.title}</a></li>
		</c:forEach>
	</ul>
	
	<c:set var="linkText"><cq:text property='viewAllLinkText'/></c:set>
	<c:if test="${not empty linkText}">
		<c:set var="homePath" value="/content/searspartsdirect/en/allarticles" />
		<c:set var="suffix" value="-repair/repair-articles" />
		<spd:LinkResolver value="${homePath}/${nodeName}${suffix}" />
    	<a href="${url}">${linkText}</a>
	</c:if>
</c:if>