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
								<!--<spd:displayImage path="${popularGuide.imagePath}"/>-->
								<img src="/content/dam/Sears-J10567-SHS06-E%23CC001A.jpg" />
							</div>
						</div>

						<div class="wrench-symbol">
							<i class="icon-wrench"></i>
						</div>
								
						<div class="guideListing-popular-text">
							<div class="wrapper">
								<h3>${popularGuide.title}</h3>
								<p>
									Refrigerator help overview Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aliquam interdum pulvinar nibh. aecenas eget nunc in justo rhoncus.
								</p>
								<div class="container">
									<div class="pull-left repair-difficulty">
										<strong>Repair difficulty</strong>
										<div>
											<div class="difficulty-level-gauge">

											</div>
											${difficultyLevel}
										</div>
									</div>
									<div class="pull-right time-required">
										<strong>Time required</strong>
										<div>
											{timeRequired}
										</div>

									</div>
								</div>
										

								<c:set var="viewText"><cq:text property="viewThisText" placeholder="" /></c:set>
								<c:choose>
									<c:when test="${not empty viewText}">
										<a class="new-btn-large" href="${url}">
											<i class="icon-chevron-right"></i>
											${viewText}
										</a>
									</c:when>
									<c:otherwise>
										<a class="new-btn-large" href="${url}">
											<i class="icon-chevron-right"></i>
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