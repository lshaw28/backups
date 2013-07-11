<%@ include file="/apps/searspartsdirect/global.jsp" %>
MOST POPULAR GUIDE
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getMostPopularGuide categoryPath="${productCategoryRelation.path}" />
	<cq:text property="title"/> <br />

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
		<a href="${url}"><cq:text property="viewThisText"/> </a> <br/>
	</c:forEach>

</c:if>
