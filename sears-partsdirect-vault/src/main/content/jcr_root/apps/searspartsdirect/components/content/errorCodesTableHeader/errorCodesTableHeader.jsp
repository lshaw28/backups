<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory"/>
<spd:getRelation single="true" assetType="brand"/>
<spd:tagsByPage tagType="subcategories"/>

<h3>
	<c:if test="${not empty brandRelation}"><c:out value="${brandRelation.title}" />&nbsp;</c:if>
	<c:if test="${not empty productCategoryRelation}"><c:out value="${productCategoryRelation.title}" />&nbsp;</c:if>
	<c:if test="${fn:length(subcategoriesList) eq 1}"><c:out value="${subcategoriesList[0].title}" /></c:if>
	<cq:text property="text" placeholder=""/>
</h3>
<cq:text property="description" placeholder=""/>