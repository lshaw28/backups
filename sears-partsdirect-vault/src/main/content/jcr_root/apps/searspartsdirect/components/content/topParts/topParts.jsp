<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getUrlRelation />
<spd:getTopParts brand="${brandRelation}" category="${productCategoryRelation}" model="${modelRelation}" />

<c:if test="${!empty topParts}">
	<h5>TOP SELLING PARTS</h5>
	<div class="boxed-callout">
		<c:forEach var="part" items="${topParts}" end="2">
			<div class="topParts-item">
				<a href="${part.url}"><img src="${part.imageUrl}" alt="${part.text}" /></a>
				<a href="${part.url}"><c:out value="${part.text}" /></a>
			</div>
		</c:forEach>
	</div>
</c:if>