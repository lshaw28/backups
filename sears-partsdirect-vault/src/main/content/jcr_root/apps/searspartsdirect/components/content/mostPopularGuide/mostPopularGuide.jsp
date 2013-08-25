<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getMostPopularGuide categoryPath="${productCategoryRelation.path}" />
	<c:set var="titleText"><cq:text property="title" placeholder="" /></c:set>
	<c:choose>
		<c:when test="${not empty titleText}">
			<h2><cq:text property="title" placeholder="Headline" default="Headline" /></h2>
		</c:when>
		<c:otherwise>
			<h2>Most Popular Guide</h2>
		</c:otherwise>
	</c:choose>

	<div class="guideListing-popular-view">
		<c:choose>
			<c:when test="${not empty guides}">
				<c:forEach items="${guides}" var="popularGuide" varStatus="status">
					<spd:linkResolver value="${popularGuide.url}" />
						<div class="guideListing-popular-image">
							<div class="wrapper">
								<spd:displayImage path="${popularGuide.imagePath}"/>
							</div>
						</div>

						<div class="wrench-symbol">
							<i class="icon-wrench"></i>
						</div>

						<div class="guideListing-popular-text">
							<div class="wrapper">
								<h3><c:out value="${popularGuide.title}" /></h3>
								<p>
									${popularGuide.description}
								</p>
								<div class="container">
									<div class="pull-left repair-difficulty">
										<strong>Repair difficulty</strong>
										<div>
											<div class="difficulty-level-gauge">
												<div class="difficultyRating ${difficultyLevel}">
													<i class="icon-wrench rating-one">&nbsp;</i><i class="icon-wrench rating-two">&nbsp;</i><i class="icon-wrench rating-three">&nbsp;</i><i class="icon-wrench rating-four">&nbsp;</i><i class="icon-wrench rating-five">&nbsp;</i>
												</div>
											</div>
										</div>
									</div>
									<div class="pull-left time-required">
										<strong>Time required</strong>
										<div>
											${timeRequired}
										</div>

									</div>
								</div>


								<c:set var="viewText"><cq:text property="viewThisText" placeholder="" /></c:set>
								<c:choose>
									<c:when test="${not empty viewText}">
										<a class="new-btn-large" href="${url}">
											<i class="icon-chevron-left"></i>
											${viewText}
										</a>
									</c:when>
									<c:otherwise>
										<a class="new-btn-large" href="${url}">
											<i class="icon-chevron-left"></i>
											View This Repair Guide
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
</c:if>