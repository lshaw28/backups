<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:GetCategoryArticleList categoryPath="${productCategoryRelation.path}" />
<h1><cq:text property="./indexTitle" placeholder="Needs title" /></h1>
<h2>${productCategoryRelation.title}</h2>
<c:choose>
	<c:when test="${not empty articles}">
	<div>
		<c:forEach var="article" items="${articles}">
		${article.url} ${article.imagePath} ${article.title} ${article.description}
		<div>
			<a href="${article.url}.html"><spd:displayImage path="${article.imagePath}"/></a>
			<a href="${article.url}.html">${article.title}</a>
			<p>${article.description}</p>
		</div>
		</c:forEach>
	</div>
	</c:when>
	<c:otherwise>
		No articles found
	</c:otherwise>
</c:choose>