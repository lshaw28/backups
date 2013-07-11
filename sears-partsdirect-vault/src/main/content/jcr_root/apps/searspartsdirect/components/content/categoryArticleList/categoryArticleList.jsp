<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<c:choose>
	<c:when test="${not empty productCategoryRelation}">
		<spd:getCategoryArticleList categoryPath="${productCategoryRelation.path}" />
		
		<h2>${productCategoryRelation.title}</h2>
		<c:choose>
			<c:when test="${not empty articles}">
				<%-- Becomes iteration over article.value --%>
				<c:forEach var="articlesEntry" items="${articles}" varStatus="currentSubcat">
					<h3><spd:displayTagTitle tagId="${articlesEntry.key}" /></h3>
					<c:forEach var="article" items="${articlesEntry.value}" varStatus="currentItem">
						<c:choose>
							<c:when test="${currentItem.count % 2 eq 1}">
								<div class="row-fluid">
							</c:when>
						</c:choose>
									<div class="span6">
										<spd:linkResolver value="${article.url}" />
										<c:if test="${not empty article.imagePath}">
											<a href="${url}"><spd:displayImage path="${article.imagePath}" decorated="false" /></a>
										</c:if>
										<h4><a href="${url}">${article.title}</a></h4>
										<p>${article.description}</p>
									</div>
						<c:choose>
							<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
								</div>
							</c:when>
						</c:choose>
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
			<p>No category defined. Please define a category in page properties.</p>
		</c:if>
	</c:otherwise>
</c:choose>