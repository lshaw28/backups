<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation assetType="productCategory" single="true" />
<spd:getRelation assetType="accessory" pagepath="${productCategoryRelation.path}" />
<c:choose>
	<c:when test="${!empty accessoryRelationList}">
		<div class="boxed-callout">
			<h4>Top Accessories</h4>
			<c:forEach var="accessory" items="${accessoryRelationList}" end="2">
				<div class="topAccessories-item">
					<spd:displayImage path="${accessory.imagePath}"
						altText="${accessory.description}" decorated="false" />
					<a href="${accessory.linkUrl}">${accessory.title}</a>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		No accessories defined for this category. Add accessories at ${productCategoryRelation.path}
	</c:otherwise>
</c:choose>