<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getAuthorArticles />

<c:if test="${not empty articles}">
<h2>Articles by this author</h2>
	<c:forEach var="article" items="${articles}" varStatus="currentItem">

		<c:if test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:if>

		<div class="span6 authorArticleTab">
			<spd:linkResolver value="${article.url}" />
             <h4 class="articleTitle1">
				<a href="${url}"><c:out value="${article.title}" /></a>
			</h4>
            <div class="span3">
                <c:if test="${not empty article.imagePath}">
					<a href="${url}"><spd:displayImage path="${article.imagePath}" /></a>
			</c:if>
            </div>
            <div class="span7">
			<h4 class="articleTitle">
				<a href="${url}"><c:out value="${article.title}" /></a>
			</h4>

			<p><c:out value="${article.description}" /></p>

            </div>

		</div>
		<c:if test="${currentItem.count % 2 eq 0 or currentItem.last}">
		</div>
		</c:if>
	</c:forEach>
</c:if>