<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation relationType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getRelation single="true" assetType="productCategory" />
</c:if>
<h2>
<c:choose>
	<c:when test="${fn:length(productCategoryRelation.title) lt 38 }">
		<cq:text property="header" placeholder="${productCategoryRelation.title} 101" />
	</c:when>
	<c:otherwise>
		<cq:text property="header" placeholder="${fn:substring(productCategoryRelation.title,0,37)} 101" />
	</c:otherwise>
	</c:choose>
</h2>

<spd:getCategory101Models category="${productCategoryRelation.path}"/>

<c:forEach var="category101Model" items="${category101Models}" varStatus="currentItem">
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:when>
	</c:choose>
	<div class="span6">
		<spd:linkResolver value="${category101Model.url}" />
	<c:if test="${not empty category101Model.imagePath}">
		<a href="${url}" ><spd:displayImage path="${category101Model.imagePath}" decorated="false" /></a>
	</c:if>
	<h4>
		<a href="${url}">${category101Model.title}</a>
	</h4>
	<p>${category101Model.description}</p>
	</div>
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
			</div>
		</c:when>
	</c:choose>
</c:forEach>
