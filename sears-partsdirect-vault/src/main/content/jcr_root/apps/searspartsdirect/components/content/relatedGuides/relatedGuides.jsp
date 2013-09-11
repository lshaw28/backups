<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getRelatedGuides productCategory="${productCategoryRelation}"/>
<c:if test="${not empty guides && not empty productCategoryRelation}">
	<h2><cq:text property="newHeader" placeholder="Most Viewed Guides" default="Most Viewed Guides"/></h2>
	<div class="touchCarousel hide-onload">
		<div class="carouselWrapper">
			<div class="carouselListWrapper">
				<c:forEach var="guide" items="${guides}">
					<div class="carouselItemHolder span3">
						<spd:linkResolver value="${guide.url}" />
						<div class="wrapperGuide">
							<div class="image-wrapper">
								<div class="wrench-symbol">
									<i class="icon-wrench"></i>
								</div>
								<div class="image">
									<a href="${url}"><spd:displayImage path="${guide.imagePath}" /></a>
								</div>
								<div class="guideURL">
									<a href="${url}"><c:out value="${guide.title}" /></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<c:if test="${fn:length(guides) eq 4}">
		<c:set var="suffix" value="-repair/repair-guides" />
		<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${productCategoryRelation.trueName}${suffix}" />
		<p><a href="${url}" class="new-btn">View All Guides</a></p>
	</c:if>
</c:if>