<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation relationType="brand" />
<spd:getNameByNodePath nodePath="${brandRelation.path}" />
<c:set var="brandName">${nodeName}</c:set>
<spd:getUrlRelation relationType="productCategory" />
<c:set var="categoryName">${productCategoryRelation.trueName}</c:set>
<spd:getUrlRelation relationType="model" />
<c:set var="modelName">${modelRelation}</c:set>

<spd:getTopParts brandName="${brandName}" categoryName="${categoryName}" modelName="${modelName}" />

<c:choose>
	<c:when test="${!empty topParts}">
		<div class="boxed-callout">
			<h4>TOP SELLING PARTS</h4>
			<c:forEach var="part" items="${topParts}" end="2">
				<div class="topParts-item">
					<a href="${part.url}"><img src="${part.imageUrl}" alt="${part.text}" /></a>
					<a href="${part.url}">${part.text}</a>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<!-- Top Parts widget present - no parts found -->
	</c:otherwise>
</c:choose>