<%@ include file="/apps/searspartsdirect/global.jsp"%>
<p><cq:include path="categoryIcon" resourceType="searspartsdirect/components/content/categoryIcon" />
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
${productCategoryRelation.description}</p>