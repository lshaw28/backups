<%@ include file="/apps/searspartsdirect/global.jsp"%>

<cq:include path="browseGlossary" resourceType="searspartsdirect/components/content/browseGlossary" />

<spd:getUrlRelation relationType="productCategory" />
<spd:getCommonParts categoryPath="${productCategoryRelation.path}"/>

<c:forEach var="commonPart" items="${commonParts}">
	<h2><c:out value="${commonPart.title}"/></h2>
	<p><c:out value="${commonPart.description}"/></p>
	
		<c:if test="${not empty commonPart.guides}">
			<h3>Common repairs using <c:out value="${commonPart.pluralTitle}"/></h3> 
			<c:forEach var="guide" items="${commonPart.guides}">
				<spd:linkResolver value="${guide.url}"/>
					<a href="${url}"><c:out value="${guide.title}"/></a>
					<br />
			</c:forEach>
		</c:if>
		<h3><a href="${mainSitePath}/partsdirect/product-types/${productCategoryRelation.title}-Parts">Shop for <c:out value="${commonPart.pluralTitle}"/></a></h3>
</c:forEach>