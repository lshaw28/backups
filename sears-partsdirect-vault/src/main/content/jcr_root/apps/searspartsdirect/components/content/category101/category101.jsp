<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<spd:tagsByPage tagType ="parentCategory"/>
<spd:category101/>

<c:if test="${empty categories}">  
  CATEGORY 101 PLACEHOLDER. PLEASE UPDATE WITH CATEGORIES TO BE DISPLAYED. 
</c:if>  

<c:if test="${!empty categories}">
	
	<c:if test="${fn:length(parentCategoryTag.title) < 38 }">
 		<cq:text property="header" placeholder="${parentCategoryTag.title} 101" />
	</c:if>
	
	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="${category.url}.html">${category.title}</a></li>
		</c:forEach>
	</ul>
	
	<a href="<cq:text property="viewAllLink"/>.html">Articles Index Page</a>
</c:if>  
