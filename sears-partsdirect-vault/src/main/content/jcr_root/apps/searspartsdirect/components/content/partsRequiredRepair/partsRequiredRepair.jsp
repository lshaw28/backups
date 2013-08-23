<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredParts/>
<h3><cq:text property="partsRequiredTitle" placeholder="Parts Required:" default="Parts Required:"/></h3>
<ul>
<c:forEach var="part" items="${parts}">
	<c:choose>
		<c:when test="${fn:length(part.url) gt 0}">
			<spd:linkResolver value="${part.url}" />
			<li data-toolid="${part.id}"><a href="${url}"><c:out value="${part.text}" /></a></li>
		</c:when>
		<c:otherwise>
			<li data-toolid="${part.id}"><c:out value="${part.text}" /></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>
<c:set var="showSearchComponent"><cq:text property="showSearch" /></c:set>
<c:if test="${showSearchComponent}">
	<cq:include path="modelSearch" resourceType="searspartsdirect/components/content/responsiveFindThisPart" />
</c:if>