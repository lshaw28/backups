<%@ include file="/apps/searspartsdirect/global.jsp"%>
<p>
	<cq:include path="categoryIcon" resourceType="searspartsdirect/components/content/categoryIcon" />

	<c:set var="text"><cq:text property="description" placeholder="" default="" /></c:set>
	<c:choose>
		<c:when test="${text ne null and not empty text}">
			${text}
		</c:when>
		<c:otherwise>
			<spd:getRelation single="true" assetType="productCategory" />
			<c:if test="${empty productCategoryRelation}">
				<spd:getUrlRelation relationType="productCategory" />
			</c:if>
			${productCategoryRelation.description}
		</c:otherwise>
	</c:choose>
</p>