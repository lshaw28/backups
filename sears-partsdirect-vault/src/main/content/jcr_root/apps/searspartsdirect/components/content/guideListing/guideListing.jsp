<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
<spd:tagsByPage tagType="subcategories"/>

<cq:text property="title"/> <br />
<c:set var="popularGuide">"${guides}"</c:set>
<a href="${popularGuide.pagePath}"><spd:displayImage path="${popularGuide.imagePath}"/></a>
		<a href="${popularGuide.pagePath}">${guide.title}</a>
<cq:text property="difficultyLevel"/> <br/>
<cq:text property="timeRequired"/><br/>		
<a href="${popularGuide.pagePath}.html"><cq:text property="viewAllText"/> </a> <br/>

<cq:text property="allGuidestitle"/> <br/>
<cq:text property="subTitle"/> <br/>
<c:if test="${not empty viewAllGuides}">
	
	<c:forEach var="guide" items="${guides}">
		<a href="${guide.pagePath}"><spd:displayImage path="${guide.imagePath}"/></a>
		<a href="${guide.pagePath}">${guide.title}</a>
		<br /><br />
	</c:forEach>

</c:if>