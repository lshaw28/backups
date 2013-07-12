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
			${titleText}<br />
		</c:when>
		<c:otherwise>
			Most Popular Guide
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty guides}">
			<c:forEach items="${guides}" var="popularGuide" varStatus="status">
				<spd:linkResolver value="${popularGuide.url}" />
				<a href="${url}"><spd:displayImage path="${popularGuide.imagePath}"/></a>
				<a href="${url}">${popularGuide.title}</a>
				<div class="new-span-responsive">
					<h5>Repair difficulty:</h5>
					<div class="difficulty-rating ${difficultyLevel}">
						<i class="icon-wrench rating-one">&nbsp;</i><i class="icon-wrench rating-two">&nbsp;</i><i class="icon-wrench rating-three">&nbsp;</i><i class="icon-wrench rating-four">&nbsp;</i><i class="icon-wrench rating-five">&nbsp;</i>
					</div>
				</div>
				<div class="new-span-responsive">
					<h5>Time required:</h5>
					<p>${timeRequired}</p>
				</div> 
				
				<c:set var="viewText"><cq:text property="viewThisText" placeholder="" /></c:set>
				<c:choose>
					<c:when test="${not empty viewText}">
						<a href="${url}">${viewText} </a> <br/>
					</c:when>
					<c:otherwise>
						<a href="${url}">View This Repair Guide </a> <br/>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
	</c:choose>
</c:if>
