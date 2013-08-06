<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation relationType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getRelation single="true" assetType="productCategory" />
</c:if>
<h2>
	<c:set var="derivedTitle" value="${productCategoryRelation.title}" />
	<c:if test="${fn:length(derivedTitle) gt 37}">
		<c:set var="derivedTitle" value="${fn:substring(derivedTitle,0,37)}" />
	</c:if>
	<c:set var="actualTitle"><cq:text property="header" placeholder="" /></c:set>
	<c:if test="${empty actualTitle}"><c:set var="actualTitle" value="${derivedTitle} 101" /></c:if>
	<c:out value="${actualTitle}" />
</h2>

<spd:getCategory101Models category="${productCategoryRelation}" />

<c:forEach var="article" items="${category101Models}" varStatus="currentItem">
	<c:if test="${currentItem.count % 2 eq 1}">
		<div class="row-fluid">
	</c:if>
	<div class="span6">
		<spd:linkResolver value="${article.url}" />
		<h4><a href="${url}"><c:out value="${article.title} "/></a></h4>
		<c:if test="${not empty article.imagePath}">
			<a href="${url}"><spd:displayImage path="${article.imagePath}" decorated="false" /></a>
		</c:if>
		<p><c:out value="${article.description} "/></p>
	</div>
	<c:if test="${currentItem.count % 2 eq 0 or currentItem.last}">
		</div>
	</c:if>
</c:forEach>