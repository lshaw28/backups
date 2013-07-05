<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredParts/>
<h3><cq:text property="partsRequiredTitle" placeholder="Parts Required:" /></h3>
<ul>
<c:forEach var="part" items="${parts}">
	<c:choose>
		<c:when test="${fn:length(part.url) gt 0}">
			<spd:linkResolver value="${part.url}" />
			<li data-toolid="${part.id}"><a href="${url}">${part.text}</a></li>
		</c:when>
		<c:otherwise>
			<li data-toolid="${part.id}">${part.text}</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>
<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />