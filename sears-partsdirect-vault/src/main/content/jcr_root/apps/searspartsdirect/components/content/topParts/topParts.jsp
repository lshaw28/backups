<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation />

<spd:getTopParts brand="${brandRelation}" category="${productCategoryRelation}" model="${modelRelation}" />

<c:choose>
	<c:when test="${!empty topParts}">
        <h5>TOP SELLING PARTS</h5>
		<div class="boxed-callout">
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