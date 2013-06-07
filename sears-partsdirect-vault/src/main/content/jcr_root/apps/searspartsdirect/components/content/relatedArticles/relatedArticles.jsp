<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:getRelatedArticles categoryPath="${productCategoryRelation.path}" />

<c:if test="${not empty articles}">
	<cq:text property="itemsHeader"/><br />
	
	<c:forEach var="article" items="${articles}">
		<a href="${article.url}.html"><spd:displayImage path="${article.imagePath}"/></a>
		<a href="${article.url}.html">${article.title}</a> <br />
		${article.description}
		<br /><br />
	</c:forEach>
	
	<c:if test="${fn:length(articles) eq 4}">
		<a href="<cq:text property="viewAllItemsLink"/>.html"><cq:text property="viewAllItemsText"/></a>
	</c:if>
</c:if>
