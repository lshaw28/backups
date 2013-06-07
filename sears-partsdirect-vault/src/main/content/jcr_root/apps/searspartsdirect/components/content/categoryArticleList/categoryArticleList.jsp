<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:GetCategoryArticleList categoryPath="${productCategoryRelation.path}" />

<c:if test="${not empty articles}">
	
	<c:forEach var="article" items="${articles}">
	<div>
		<a href="${article.url}.html"><spd:displayImage path="${article.imagePath}"/></a>
		<a href="${article.url}.html">${article.title}</a> <br />
		${article.description}
	</div>
	</c:forEach>

</c:if>
