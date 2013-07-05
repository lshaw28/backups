<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%--
Make sure h1 and footer link are configurable!
Carousel Shows at max 5 items, component spec sets max to display at 4
--%>

<spd:getRelation single="true" assetType="productCategory" />
<spd:getRelatedGuides categoryPath="${productCategoryRelation.path}" />
<spd:getNameByNodePath nodePath="${productCategoryRelation.path}" />

<c:if test="${not empty guides && not empty productCategoryRelation}">
	<h2>
		<cq:text property="itemsHeader" />
	</h2>
	<div class="touch-carousel hide-onload">
		<div class="carousel-wrapper row-fluid">
			<div class="carousel-list-wrapper">
				<c:forEach var="guide" items="${guides}">
					<div class="carousel-item span3">
						<spd:linkResolver value="${guide.url}" />
						<a href="${url}"><spd:displayImage path="${guide.imagePath}" /></a>
						<a href="${url}">${guide.title}</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<c:if test="${fn:length(guides) eq 4}">
	<br />
		<div class="new-btn">
			<c:set var="suffix" value="-repair/repair-articles" />
			<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${nodeName}${suffix}" />
			<a href="${url}" class="new-btn-small accordion-inner-btn">View All Articles</a>
		</div>
	</c:if>
</c:if>