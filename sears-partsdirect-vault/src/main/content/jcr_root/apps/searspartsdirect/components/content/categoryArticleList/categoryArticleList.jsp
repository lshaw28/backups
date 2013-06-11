<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:GetCategoryArticleList categoryPath="${productCategoryRelation.path}" />
<h2>${productCategoryRelation.title}</h2>
<c:choose>
	<c:when test="${not empty articles}">
	<div class="categoryArticles">
		<c:forEach var="article" items="${articles}">
		<div class="categoryArticle">
			<p><a href="${article.url}"><spd:displayImage path="${article.imagePath}"/></a><a href="${article.url}.html">${article.title}</a></p>
			<p>${article.description}</p>
		</div>
		</c:forEach>
	</div>
	</c:when>
	<c:otherwise>
		<p>No articles found.</p>
	</c:otherwise>
</c:choose>