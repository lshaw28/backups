<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parentCategory" />
<spd:getAssets />

<cq:text property="componentHeader" placeholder="Select your product type for repair help" />

<br>
<c:forEach var="parentCategoryTitle" items="${parentCategoryTitles}">
	 <br><br> ${parentCategoryTitle} <br>
	
	 <%-- nest another forEach to iterate through all categories, given current parent category
		(sample code, if given "categories" tag)
	
	 <c:forEach var="category" items="${models}">
		<li><a href="${category.url}.html"><spd:displayImage path="${category.imagePath}"/></a>
		<a href="${category.url}.html">${category.title}</a></li>
	 </c:forEach>  --%>
	
	 <c:forEach var="category" items="${models}">
		<spd:displayImage path="${category.imagePath}"/>
		${category.title} <br>
	 </c:forEach>
	
</c:forEach>
