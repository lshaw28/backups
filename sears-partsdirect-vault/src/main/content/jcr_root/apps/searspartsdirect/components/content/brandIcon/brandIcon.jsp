<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation assetType="productCategory" single="true" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getIcon category="${productCategoryRelation}" />
<c:if test="${iconName != Constants.NO_ICON}">
	<i class="${iconName}"></i>
</c:if>