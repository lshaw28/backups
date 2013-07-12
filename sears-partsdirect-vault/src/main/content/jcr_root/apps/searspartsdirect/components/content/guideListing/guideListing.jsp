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
			${titleText}<br />
		</c:when>
		<c:otherwise>
			All Guides
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty guides}">
			<c:forEach items="${guides}" var="row">
				SubCategory:  <spd:displayTagTitle tagId="${row.key}" /><br/>
				<c:forEach var="guide" items="${row.value}">
					<spd:linkResolver value="${guide.url}" />
					<a href="${url}"><spd:displayImage path="${guide.imagePath}"/></a>
					<a href="${url}">${guide.title}</a> <br/>
				</c:forEach>
			</c:forEach>
		</c:when>
	</c:choose>
</c:if>
