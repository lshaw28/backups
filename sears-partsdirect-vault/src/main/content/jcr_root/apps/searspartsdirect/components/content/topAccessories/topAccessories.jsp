<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation assetType="productCategory" single="true" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<c:choose>
	<c:when test="${!empty productCategoryRelation}">
		<spd:getRelation assetType="accessory" pagepath="${productCategoryRelation.path}" />
		<c:choose>
			<c:when test="${!empty accessoryRelationList}">
				<div class="boxed-callout">
					<h4>Top Accessories</h4>
					<c:forEach var="accessory" items="${accessoryRelationList}" end="2">
						<div class="topAccessories-item">
							<spd:displayImage path="${accessory.imagePath}"
								altText="${accessory.description}" decorated="false" />
							<a href="${accessory.linkUrl}"><c:out value="${accessory.title}" /></a>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${isEditMode or isDesignMode}">
					<p>No accessories defined for this category. Add accessories at <c:out value="${productCategoryRelation.path}" /></p>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:if test="${isEditMode or isDesignMode}">
			<p>No category defined for this page. Add a category in page properties.</p>
		</c:if>
	</c:otherwise>
</c:choose>