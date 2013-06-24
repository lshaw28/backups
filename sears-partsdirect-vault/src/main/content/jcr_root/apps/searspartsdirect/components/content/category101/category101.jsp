<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:tagsByPage tagType="parent_categories" />
<spd:getMultifieldCategories />

<c:if test="${not empty categories}">
	<c:choose>
		<c:when test="${fn:length(parent_categoriesList[0].title) lt 38 }">
			<cq:text property="header" placeholder="${parent_categoriesList[0].title} 101" />
		</c:when>
		<c:otherwise>
			<cq:text property="header" placeholder="${fn:substring(parent_categoriesList[0].title,0,37)} 101" />
		</c:otherwise>
	</c:choose>
	<ul>
		<c:forEach var="category" items="${categories}">
			<spd:LinkResolver value="${category.url}" />
			<li><a href="${url}">${category.title}</a></li>
		</c:forEach>
	</ul>
	<c:set var="viewAllLink"><cq:text property='viewAllLink'/></c:set>
	<spd:LinkResolver value="${viewAllLink}" />
	<a href="${url}">View All Categories</a>
</c:if>