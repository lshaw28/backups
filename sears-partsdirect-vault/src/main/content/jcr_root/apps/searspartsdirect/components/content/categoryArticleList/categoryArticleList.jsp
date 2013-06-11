<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:GetCategoryArticleList categoryPath="${productCategoryRelation.path}" />
<h2>${productCategoryRelation.title}</h2>
<c:choose>
	<c:when test="${not empty articles}">
		<c:forEach var="article" items="${articles}" varStatus="currentItem">
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 1}">
					<div class="row-fluid">
				</c:when>
			</c:choose>
						<div class="span6">
							<a href="${article.url}"><spd:displayImage path="${article.imagePath}" decorated="false" /></a>
							<p><a href="${article.url}">${article.title}</a></p>
							<p>${article.description}</p>
						</div>
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>No articles found.</p>
	</c:otherwise>
</c:choose>