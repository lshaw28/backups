<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:GetCarouselImages />

<c:choose>
	<c:when test="${not empty carouselImages}">
		<c:set var="titleText"><cq:text property="./titleText" placeholder="" /></c:set>
		<c:if test="${not empty titleText}">
			<h2>${titleText}</h2>
		</c:if>
		<div class="hide-onload desktop-carousel touch-carousel">
			<div class="carousel-wrapper">
				<div class="carousel-list-wrapper">
					<c:forEach items="${carouselImages}" var="carouselImage">
						<div class="carousel-item">
							<cq:include path="${carouselImage}" resourceType="searspartsdirect/components/content/carouselItem" />
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		No content.
	</c:otherwise>
</c:choose>