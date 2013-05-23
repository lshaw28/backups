<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType ="parentCategory"/>
<spd:category101/>

<%-- Question: how to make conditionally mandatory? --%>
${parentCategoryTag.title} <cq:text property="header" placeholder=" 101" />

<c:if test="${empty categories}">  
  IT IS EMPTY!!! 
</c:if>  
<c:if test="${!empty categories}">  
  IT IS *NOT* EMPTY!!! 
</c:if>  

<ul>
	<c:forEach var="category" items="${categories}">
		<li><a href="<cq:text property="${category}"/>.html"> ${category} the category title would go here</a></li>
	</c:forEach>
</ul>
<br>

<a href="<cq:text property="viewAllLink"/>.html">Articles Index Page</a>