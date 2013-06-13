<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%-- 
Make sure h1 and footer link are configurable!
Carousel Shows at max 5 items, component spec sets max to display at 4
--%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:getRelatedArticles categoryPath="${productCategoryRelation.path}" />

<c:if test="${not empty articles && not empty productCategoryRelation}">
	<h2>
		<cq:text property="itemsHeader" />
	</h2>

	<div class="carousel-mobile-only">
		<div class="carousel-wrapper row-fluid">
			<c:forEach var="article" items="${articles}">
				<div class="carousel-mobile-item span3">
					<a href="${article.url}.html"><spd:displayImage path="${article.imagePath}" /></a>
					<a href="${article.url}.html">${article.title}</a>
					<p>${article.description}</p>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<c:if test="${fn:length(articles) eq 4}">
		<br />
		<div class="view-all">
			<a href="<cq:text property="viewAllItemsLink"/>.html"><cq:text property="viewAllItemsText"  placeholder="View all Articles" /></a>
		</div>
	</c:if>
</c:if>
