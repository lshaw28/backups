<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
	<spd:tagsByPage tagType="subcategories"/>

	<cq:text property="allGuidestitle"/> <br/>
	
	<c:forEach items="${guides}" var="row">
		SubCategory:  <spd:displayTagTitle tagId="${row.key}" /><br/>
		<c:forEach var="guide" items="${row.value}">
			<spd:linkResolver value="${guide.url}" />
			<a href="${url}"><spd:displayImage path="${guide.imagePath}"/></a>
			<a href="${url}">${guide.title}</a> <br/>
		</c:forEach>
	</c:forEach>
</c:if>
