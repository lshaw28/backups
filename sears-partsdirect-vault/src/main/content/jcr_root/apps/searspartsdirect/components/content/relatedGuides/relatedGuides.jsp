<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%-- 
Make sure h1 and footer link are configurable!
Carousel Shows at max 5 items, component spec sets max to display at 4
--%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:getRelatedGuides categoryPath="${productCategoryRelation.path}" />

<c:if test="${not empty guides && not empty productCategoryRelation}">
	<h2>
		<cq:text property="itemsHeader" />
	</h2>
	
	<div class="carousel-mobile-only">
		<div class="carousel-wrapper row-fluid">
			<c:forEach var="guide" items="${guides}">
				<div class="carousel-mobile-item span3">
					<a href="${guide.url}.html"><spd:displayImage path="${guide.imagePath}" /></a>
					<a href="${guide.url}.html">${guide.title}</a>
				</div>
			</c:forEach>
		</div>
	</div>

	<c:if test="${fn:length(guides) eq 4}">
	<br />
	<div class="new-btn">
			<a href="<cq:text property="viewAllItemsLink"/>.html" ><cq:text property="viewAllItemsText" placeholder="View all Guides" /></a>
		</div>
	</c:if>
</c:if>
