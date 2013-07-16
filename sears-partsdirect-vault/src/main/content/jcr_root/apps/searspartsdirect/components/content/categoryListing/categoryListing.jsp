<%@ include file="/apps/searspartsdirect/global.jsp" %>

<%-- get all the parent categories found on current page, based on tags.
	returns arraylist of tags --%>
<spd:tagsByPage tagType="parent_categories" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />

<%-- iterate through each parent category tag --%>
<c:forEach var="parentCategory" items="${parent_categoriesList}">
	<div class="category-title"><h4> ${parentCategory.title}</h4></div>
	<div class="category-container">
	
		<%-- get the list of all the product categories that correspond to the current parent category
			returns category asset model array list --%>
		<spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />
		
		<%-- iterate through each category asset model object --%>
		<c:forEach var="category" items="${productCategoryList}">
		
			<div class="category-item">
			
				<%-- turn the product category asset to the corresponding category page.
					returns product category page --%>
				<spd:getRelatedPages assetPath="${category.path}" rootPath="${Constants.CATEGORIES_ROOT}" />
								
				<%-- format the url to the category page path --%>
				<spd:linkResolver value="${relatedPages[0].path}" />
				<a href="${url}">
				
				<%-- diplay the icon --%>
				<c:set var="iconImage" value="${relatedPages[0].properties.iconImages}" />
				<c:if test="${fn:length(relatedPages[0].properties.iconImages) eq 0}" >
					<c:set var="iconImage" value="svg-icon-er" />
				</c:if>
				<span class="category-item-inner">
				<i class="${iconImage}"></i><br />
				
				<%-- diplay the title --%>
				${category.pluralTitle}
				
				</span></a>
			</div>
		</c:forEach>
	</div>
</c:forEach>