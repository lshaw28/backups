<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<c:choose>
	<c:when test="${not empty productCategoryRelation}">
		<spd:getCategoryArticleList category="${productCategoryRelation}" />
		<c:choose>
			<c:when test="${not empty articles}">
				<h2> More Articles<!--  <c:out value="${productCategoryRelation.title} "/>--></h2>
				<%-- Becomes iteration over article.value --%>
				<c:forEach var="articlesEntry" items="${articles}" varStatus="currentSubcat">
					<h3><spd:displayTagTitle tagId="${articlesEntry.key}" /></h3>
					<c:forEach var="article" items="${articlesEntry.value}" varStatus="currentItem">
						<c:if test="${currentItem.count % 2 eq 1}">
							<div class="row-fluid">
						</c:if>
						<div class="span6">
							<spd:linkResolver value="${article.url}" />
							<h4><a href="${url}"><c:out value="${article.title} "/></a></h4>
							<c:if test="${not empty article.imagePath}">
								<a href="${url}"><spd:displayImage path="${article.imagePath}" decorated="false" /></a>
							</c:if>
							<p><c:out value="${article.description} "/></p>
						</div>
						<c:if test="${currentItem.count % 2 eq 0 or currentItem.last}">
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>No articles found.</p>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:if test="${isEditMode or isDesignMode}">
			<p>No category defined for this page. Add a category in page properties.</p>
		</c:if>
	</c:otherwise>
</c:choose>