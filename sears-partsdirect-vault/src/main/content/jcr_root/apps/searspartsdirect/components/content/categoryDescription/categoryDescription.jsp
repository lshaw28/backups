<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
${productCategoryRelation.description}