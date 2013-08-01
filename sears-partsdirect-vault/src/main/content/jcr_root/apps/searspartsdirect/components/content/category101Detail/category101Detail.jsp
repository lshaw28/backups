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

<c:forEach var="category101Model" items="${category101Models}" varStatus="currentItem">
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:when>
	</c:choose>
	<div class="span6">
		<spd:linkResolver value="${category101Model.url}" />
		<h4>
			<a href="${url}"><c:out value="${category101Model.title} "/></a>
		</h4>
		<c:if test="${not empty category101Model.imagePath}">
			<a href="${url}" ><spd:displayImage path="${category101Model.imagePath}" decorated="false" /></a>
		</c:if>
		<p><c:out value="${category101Model.description} "/></p>
	</div>
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
			</div>
		</c:when>
	</c:choose>
</c:forEach>