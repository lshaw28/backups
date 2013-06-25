<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parent_categories" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />
<br>
<c:forEach var="parentCategory" items="${parent_categoriesList}">
	 <br /> ${parentCategory.title} <br /><br />

	 <spd:getAssets assetType="productCategory" tagFilter="${parentCategory.tagID}" />
	 <c:forEach var="category" items="${productCategoryList}">

	 	<spd:getRelatedPages assetPath="${category.path}" rootPath="/content/searspartsdirect/en/categories" />
	 	<spd:LinkResolver value="${relatedPages[0].path}" />
		<a href="${url}"><spd:displayImage path="${relatedPages[0].path}/jcr:content/image"/></a>
		<a href="${url}">${relatedPages[0].title}</a>
		<br /><br />
	 </c:forEach>
</c:forEach>
