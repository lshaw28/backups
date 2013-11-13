<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:tagsByPage tagType="parent_categories" /><%
%><c:set var="defaultTitle" value="Select your product type for repair help" /><%
%><c:set var="actualTitle"><cq:text property="componentHeader" placeholder="${defaultTitle}" /></c:set><%
%><c:if test="${empty actualTitle}"><c:set var="actualTitle" value="${defaultTitle}" /></c:if><%
%><h2><c:out value="${actualTitle}" /></h2><%
%><c:forEach var="parentCategory" items="${parent_categoriesList}">
    <spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" /><%
%><c:if test="${!empty productCategoryList}"><%
%><div class="category-title">
		<h4><c:out value="${parentCategory.title} Repair Help" /></h4>
	</div>
	<div class="category-container"><c:forEach var="category" items="${productCategoryList}">
		<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${category.trueName}${Constants.MODELNO_SFX}" /><%
		%><div class="category-item">
			<a href="${url}"><span class="category-item-inner"><spd:getIcon category="${category}" pagePath="${Constants.CATEGORIES_ROOT}/${category.trueName}${Constants.MODELNO_SFX}"/><c:if test="${iconName != Constants.NO_ICON}"><i class="${iconName}"></i><br /></c:if>
			${category.pluralTitle}</span></a>
		</div>
	</c:forEach></div>
</c:if>
</c:forEach>