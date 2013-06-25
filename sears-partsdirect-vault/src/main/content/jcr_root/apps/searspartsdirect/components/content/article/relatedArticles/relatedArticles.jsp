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
					<spd:LinkResolver value="${article.url}" />
					<a href="${url}"><spd:displayImage path="${article.imagePath}" /></a>
					<a href="${url}">${article.title}</a>
					<p>${article.description}</p>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<c:if test="${fn:length(articles) eq 4}">
		<br />
		<div class="view-all">
			<c:set var="viewAllItemsLink"><cq:text property='viewAllItemsLink'/></c:set>
			<spd:LinkResolver value="${viewAllItemsLink}" />
			<a href="${url}"><cq:text property="viewAllItemsText"  placeholder="View all Articles" /></a>
		</div>
	</c:if>
</c:if>
