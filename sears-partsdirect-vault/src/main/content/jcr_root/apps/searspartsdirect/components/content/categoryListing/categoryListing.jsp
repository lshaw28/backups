<%@ include file="/apps/searspartsdirect/global.jsp" %>

<%-- get all the parent categories found on current page, based on tags.
	returns arraylist of tags --%>
<spd:tagsByPage tagType="parent_categories" />
<c:set var="defaultTitle" value="Select your product type for repair help" />
<c:set var="actualTitle"><cq:text property="componentHeader" placeholder="${defaultTitle}" /></c:set>
<c:if test="${empty actualTitle}"><c:set var="actualTitle" value="${defaultTitle}" /></c:if>
<h2><c:out value="${actualTitle}" /></h2>

<%-- iterate through each parent category tag --%>
<c:forEach var="parentCategory" items="${parent_categoriesList}">
    <spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />
    <c:if test="${!empty productCategoryList}">
	<div class="category-title"><h4><c:out value="${parentCategory.title} Repair Help" /></h4></div>
	<div class="category-container">

		<%-- get the list of all the product categories that correspond to the current parent category
			returns category asset model array list
		<spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />    --%>

		<%-- iterate through each category asset model object --%>
		<c:forEach var="category" items="${productCategoryList}">
		<div class="category-item">

				<%-- construct the url to the category page --%>
				<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${category.trueName}${Constants.MODELNO_SFX}" />
				<a href="${url}">

				<%-- display the icon --%>
				<span class="category-item-inner">
				<spd:getIcon category="${category}" pagePath="${Constants.CATEGORIES_ROOT}/${category.trueName}${Constants.MODELNO_SFX}"/>
				<c:if test="${iconName != Constants.NO_ICON}">
					<i class="${iconName}"></i><br />
				</c:if>

				<%-- display the title --%>
				${category.pluralTitle}

				</span></a>
			</div>
		</c:forEach>
    </c:if>
	</div>
</c:forEach>