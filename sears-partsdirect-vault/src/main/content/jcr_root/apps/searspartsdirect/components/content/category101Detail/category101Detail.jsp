<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<spd:tagsByPage tagType="parent_categories" />
<spd:getMultifieldCategories />

<c:if test="${not empty categories}">
	<h2>
		<c:choose>
			<c:when test="${fn:length(parent_categoriesList[0].title) lt 38 }">
				<cq:text property="header" placeholder="${parent_categoriesList[0].title} 101" />
			</c:when>
			<c:otherwise>
				<cq:text property="header" placeholder="${fn:substring(parent_categoriesList[0].title,0,37)} 101" />
			</c:otherwise>
		</c:choose>
	</h2>
	
		<c:forEach var="category" items="${categories}" varStatus="currentItem">
		
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 1}">
					<div class="row-fluid">
				</c:when>
			</c:choose>
						<div class="span6">
							<c:if test="${not empty category.imagePath}">
								<a href="${category.url}"><spd:displayImage path="${category.imagePath}" decorated="false" /></a>
							</c:if>
							<h4><a href="${category.url}">${category.title}</a></h4>
							<p>${category.description}</p>
						</div>
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	<a href="<cq:text property="viewAllLink"/>.html">View All Categories</a>
</c:if>































