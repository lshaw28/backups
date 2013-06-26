<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation assetType="productCategory" single="true" />

<h2>${productCategoryRelation.title} <cq:include path="/etc/spdAssets/globalConfig/maintenanceIndexTitleSuffix" resourceType="searspartsdirect/components/content/maintenanceIndexTitleGlobalSuffix" /></h2>