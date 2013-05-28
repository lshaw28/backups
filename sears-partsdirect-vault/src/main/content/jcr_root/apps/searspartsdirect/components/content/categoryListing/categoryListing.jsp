<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="parentCategory" />

<cq:text property="componentHeader" placeholder="Select your product type for repair help" />

<br> list of tags found:<br>
	 <c:forEach var="parentCategoryTitle" items="${parentCategoryTitles}">
			<li>${parentCategoryTitle}</li>
			
			<%-- nest another forEach to iterate through all categories, given current parent category
				(sample code, if given "categories" tag)
			
			 <c:forEach var="category" items="${categories}">
					<li><a href="${category.url}.html"><img src="<cq:text property="${category.imagePath}"/>"/></a>
					<a href="${category.url}.html">${category.name}</a></li>
			 </c:forEach>
			
			--%>
			
	 </c:forEach>
	
	
	
<%-- test if category titles is empty
<c:if test="${empty parentCategoryTitles}">  
  LIST OF PARENT CATEGORIES IS EMPTY!!! 
</c:if>  
<c:if test="${!empty parentCategoryTitles}">  
  LIST OF PARENT CATEGORIES IS *NOT* EMPTY!!! 
</c:if> --%>

	