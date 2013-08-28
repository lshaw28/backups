<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getCarouselImages />
<c:if test="${not empty carouselImages}">
	<c:set var="titleText"><cq:text property="./titleText" placeholder="" /></c:set>
	<c:if test="${not empty titleText}">
		<h2><c:out value="${titleText} "/></h2>
	</c:if>
	<div class="hide-onload desktopCarousel touchCarousel">
		<div class="carouselWrapper">
			<div class="carouselListWrapper">
				<c:forEach items="${carouselImages}" var="carouselImage">
					<div class="carouselItemHolder">
						<cq:include path="${carouselImage}" resourceType="searspartsdirect/components/content/carouselItem" />
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>