<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:tagsByPage tagType="parentCategory" />
<spd:getMultifieldCategories />

<c:if test="${not empty categories}">
	<
	c:choose>
		<c:when test="${fn:length(parentCategoryTag.title) < 38 }">
			<cq:text property="header" placeholder="${parentCategoryTag.title} 101" />
		</c:when>
		<c:otherwise>
			<cq:text property="header"
				placeholder="${fn:substring(parentCategoryTag.title,0,37)} 101" />
		</c:otherwise>
	</c:choose>
	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="${category.url}.html">${category.title}</a></li>
		</c:forEach>
	</ul>

	<a href="<cq:text property="viewAllLink"/>.html">Articles Index Page</a>

</c:if>