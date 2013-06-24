<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
<spd:tagsByPage tagType="subcategories"/>

<cq:text property="title"/> <br />

<c:forEach items="${guides}" var="entry" varStatus="mainStatus">
	
	<c:forEach items="${entry.value}" var="popularGuide" varStatus="status">
		<c:if test="${mainStatus.index == 0 && status.index == 0}">
			<spd:LinkResolver value="${popularGuide.url}" />
			<a href="${url}"><spd:displayImage path="${popularGuide.imagePath}"/></a>
			<a href="${url}">${popularGuide.title}</a>
			<cq:text property="difficultyLevel"/> <br/>
			<cq:text property="timeRequired"/><br/>		
			<a href="${url}"><cq:text property="viewAllText"/> </a> <br/>
		</c:if>
	</c:forEach>
</c:forEach>
<%-- <a href="${popularGuide[0].url}"><spd:displayImage path="${popularGuide[0].imagePath}"/></a>
<a href="${popularGuide[0].url}">${popularGuide[0].title}</a>
<cq:text property="difficultyLevel"/> <br/>
<cq:text property="timeRequired"/><br/>		
<a href="${popularGuide[0].url}.html"><cq:text property="viewAllText"/> </a> <br/> 
 --%>
<cq:text property="allGuidestitle"/> <br/>
<cq:text property="subTitle"/> <br/>

<c:forEach items="${guides}" var="row">
    SubCategory:  ${row.key}<br/>
 	<c:forEach var="guide" items="${row.value}">
 		<spd:LinkResolver value="${guide.url}" />
		<a href="${url}"><spd:displayImage path="${guide.imagePath}"/></a>
		<a href="${url}">${guide.title}</a> <br/>
	</c:forEach>
</c:forEach>
 
<%-- <c:set var="popularGuide">${guides}</c:set>
<c:if test="${not empty popularGuide}">
	<a href="${popularGuide[0].pagePath}"><spd:displayImage path="${popularGuide.imagePath}"/></a>
	<a href="${popularGuide.pagePath}">${guide.title}</a>
	<cq:text property="difficultyLevel"/> <br/>
	<cq:text property="timeRequired"/><br/>		
	<a href="${popularGuide.pagePath}.html"><cq:text property="viewAllText"/> </a> <br/>

	<cq:text property="allGuidestitle"/> <br/>
	<cq:text property="subTitle"/> <br/>
	
	<c:forEach var="guide" items="${guides}">
		<a href="${guide.pagePath}"><spd:displayImage path="${guide.imagePath}"/></a>
		<a href="${guide.pagePath}">${guide.title}</a>
		<br /><br />
	</c:forEach>

</c:if>  --%>