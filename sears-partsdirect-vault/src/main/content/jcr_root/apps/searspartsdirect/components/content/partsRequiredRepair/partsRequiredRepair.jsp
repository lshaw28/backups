<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredParts/>
<h3><cq:text property="partsRequiredTitle" placeholder="Parts Required:" /></h3>
<ul>
<c:forEach var="part" items="${parts}">
	<spd:LinkResolver value="${part.url}" />
	<li data-partid="${part.id}"><a href="${url}">${part.text}</a></li>
</c:forEach>
</ul>
<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />