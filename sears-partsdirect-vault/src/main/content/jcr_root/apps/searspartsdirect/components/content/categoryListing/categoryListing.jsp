<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parentCategory" />
<cq:text property="componentHeader" placeholder="Select your product type for repair help" />
<br>
<c:forEach var="parentCategoryTitle" items="${parentCategoryTitles}">
	 <br><br> ${parentCategoryTitle} <br><br>
	 <spd:getAssets assetType="productCategory" />
	 <c:forEach var="category" items="${productCategoryList}">
		<spd:displayImage path="${category.imagePath}"/>
		${category.title} <br><br>
	 </c:forEach>
</c:forEach>
