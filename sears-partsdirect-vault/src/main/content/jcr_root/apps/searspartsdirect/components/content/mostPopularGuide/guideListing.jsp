<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
	<spd:tagsByPage tagType="subcategories"/>
	
	<cq:text property="title"/> <br />
	
	<c:forEach items="${guides}" var="entry" varStatus="mainStatus">
	
		<c:forEach items="${entry.value}" var="popularGuide" varStatus="status">
			<c:if test="${mainStatus.index == 0 && status.index == 0}">
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
					<p><cq:text property="timeRequired" /></p>
				</div>
				<a href="${url}"><cq:text property="viewAllText"/> </a> <br/>
			</c:if>
		</c:forEach>
	</c:forEach>
	<cq:text property="allGuidestitle"/> <br/>
	<cq:text property="subTitle"/> <br/>
	
	<c:forEach items="${guides}" var="row">
		SubCategory:  <spd:displayTagTitle tagId="${row.key}" /><br/>
		<c:forEach var="guide" items="${row.value}">
			<spd:linkResolver value="${guide.url}" />
			<a href="${url}"><spd:displayImage path="${guide.imagePath}"/></a>
			<a href="${url}">${guide.title}</a> <br/>
		</c:forEach>
	</c:forEach>
</c:if>
