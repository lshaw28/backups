<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
	<spd:tagsByPage tagType="subcategories"/>
	<c:set var="titleText"><cq:text property="allGuidestitle" placeholder="" /></c:set>
	<c:choose>
		<c:when test="${not empty titleText}">
			<h4>${titleText}</h4>
		</c:when>
		<c:otherwise>
			<h4>All Guides</h4>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty guides}">
			<c:forEach items="${guides}" var="row">
				<%-- Design does not show this 
				<spd:displayTagTitle tagId="${row.key}" />
				--%>
				<c:forEach var="guide" items="${row.value}">
					<spd:linkResolver value="${guide.url}" />
						<div class="item">
							<h4><a href="${url}">${guide.title}</a></h4>

							<div class="wrapper">
								<div class="image-wrapper">
									<div class="wrench-symbol">
										<i class="icon-wrench"></i>
									</div>
									<div class="image">
										<spd:displayImage path="${guide.imagePath}" />
									</div>
								</div>

								<p>
									Article description goes here. Aenean feugiat fringilla odio. Donec  lorem porttitor volutpat elit. Proin metus justo, accumsan vitae, egestas nec.
								</p>
							</div>
						</div>
				</c:forEach>
			</c:forEach>
		</c:when>
	</c:choose>
</c:if>
