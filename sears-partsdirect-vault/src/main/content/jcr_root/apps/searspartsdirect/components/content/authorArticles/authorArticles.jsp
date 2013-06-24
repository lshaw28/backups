<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spd:getRelation single="true" assetType="author" />

<spd:getAuthorArticles authorPath="${authorRelation.path}" />
<c:if test="${not empty articles}">
		
	<c:forEach var="article" items="${articles}" varStatus="currentItem">
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:when>
	</c:choose>
		<div class="span6">
		<c:if test="${not empty article.imagePath}">
			<a href="${article.url}"><spd:displayImage path="${article.imagePath}"/></a>
		</c:if>
		<h4><a href="${article.url}">${article.title}</a></h4>
		<p>${article.description}</p>
		</div>
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
			</div>
		</c:when>
	</c:choose>
	</c:forEach>

</c:if> 
