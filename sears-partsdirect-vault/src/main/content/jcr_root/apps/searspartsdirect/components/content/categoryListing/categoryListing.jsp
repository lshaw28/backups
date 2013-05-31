<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parent_categories" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />
<br>
<c:forEach var="parentCategory" items="${parent_categoriesList}">
	 <br><br> ${parentCategory.title} <br><br>
	 <spd:getAssets assetType="productCategory" productCategoryFilter="${parentCategory.title}" />
	 <c:forEach var="category" items="${productCategoryList}">
		<spd:displayImage path="${category.imagePath}"/>
		${category.title} <br><br>
	 </c:forEach>
</c:forEach>
