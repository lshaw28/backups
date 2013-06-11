<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spd:getRelation single="true" assetType="author" />

<spd:getAuthorArticles authorPath="${authorRelation.path}" />
<c:if test="${not empty articles}">
		
	<c:forEach var="article" items="${articles}">
		<a href="${article.url}"><spd:displayImage path="${article.imagePath}"/></a>
		<a href="${article.url}">${article.title}</a>
		${article.description}
		<br /><br />
	</c:forEach>

</c:if> 
