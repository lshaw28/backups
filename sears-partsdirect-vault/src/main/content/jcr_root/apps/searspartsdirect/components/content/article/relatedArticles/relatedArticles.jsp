<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%--
Make sure h1 and footer link are configurable!
Carousel Shows at max 5 items, component spec sets max to display at 4
--%>

<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getRelatedArticles categoryPath="${productCategoryRelation.path}"/>

<c:if test="${not empty articles && not empty productCategoryRelation}">
	<h2>
		<cq:text property="newHeader" placeholder="Related Articles" />
	</h2>

	<div class="carousel-mobile-only">
		<div class="carousel-wrapper row-fluid">
			<c:forEach var="article" items="${articles}">
				<div class="carousel-mobile-item span3">
					<spd:linkResolver value="${article.url}" />
					<a href="${url}"><spd:displayImage path="${article.imagePath}" /></a>
					<a href="${url}"><c:out value="${article.title}" /></a>
					<p><c:out value="${article.description}" /></p>
				</div>
			</c:forEach>
		</div>
	</div>

	<c:if test="${fn:length(articles) eq 4}">
		<br />
		<div class="view-all">
			<c:set var="suffix" value="-repair/repair-articles" />
			<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${productCategoryRelation.trueName}${suffix}" />
			<a href="${url}" class="new-btn-small accordion-inner-btn">View All Articles</a>
		</div>
	</c:if>
</c:if>
