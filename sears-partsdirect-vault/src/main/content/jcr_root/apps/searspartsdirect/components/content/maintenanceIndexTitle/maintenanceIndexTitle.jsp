<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation assetType="productCategory" single="true" />
<spd:FixSingularTitle modelInstance="${productCategoryRelation}" />
<h2>
	${productCategoryRelation.singularTitle}
	<cq:include path="/etc/spdAssets/globalConfig/maintenanceIndexTitleSuffix" resourceType="searspartsdirect/components/content/maintenanceIndexTitleGlobalSuffix" />
</h2>