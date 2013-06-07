<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parent_categories" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />

<br>
<c:forEach var="parentCategory" items="${parent_categoriesList}">
	 <br /> ${parentCategory.title} <br /><br />
	 <spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />
	 
	 <c:forEach var="category" items="${productCategoryList}">
	 	<spd:getRelatedPages assetPath="${category.path}" rootPath="/content/searspartsdirect/en/categories" />
	 		 	
		<a href="${relatedPages[0].path}.html"><spd:displayImage path="${category.imagePath}"/></a>
		<a href="${relatedPages[0].path}.html">${category.title}</a>
		<br /><br />
	 </c:forEach>
</c:forEach>
