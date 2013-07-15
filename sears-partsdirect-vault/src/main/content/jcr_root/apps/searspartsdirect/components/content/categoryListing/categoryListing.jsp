<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parent_categories" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />
<c:forEach var="parentCategory" items="${parent_categoriesList}">
	<div class="category-title"><h4> ${parentCategory.title}</h4></div>
	<div class="category-container">
		<spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />
		<c:forEach var="category" items="${productCategoryList}">
			<div class="category-item">
				<a href="${url}">
				<spd:getRelatedPages assetPath="${category.path}" rootPath="${Constants.CATEGORIES_ROOT}" />
				<spd:linkResolver value="${relatedPages[0].path}" />
				<c:set var="iconImages"><cq:text property="${relatedPages[0].iconImages}" placeholder="svg-icon-chainsaw" /></c:set>
				<span class="category-item-inner">
				<i class="${iconImages}"></i>
				<br />
				${relatedPages[0].title}
				</span></a>
			</div>
		</c:forEach>
	</div>
</c:forEach>