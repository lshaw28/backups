<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getAuthorArticles />

<c:if test="${not empty articles}">
	<c:forEach var="article" items="${articles}" varStatus="currentItem">
		<c:if test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:if>
		<div class="span6">
			<spd:LinkResolver value="${article.url}" />
			<c:if test="${not empty article.imagePath}">
				<a href="${url}"><spd:displayImage path="${article.imagePath}" /></a>
			</c:if>
			<h4>
				<a href="${url}"><c:out value="${article.title}" /></a>
			</h4>
			<p><c:out value="${article.description}" /></p>
		</div>
		<c:if test="${currentItem.count % 2 eq 0 or currentItem.last}">
			</div>
		</c:if>
	</c:forEach>
</c:if>