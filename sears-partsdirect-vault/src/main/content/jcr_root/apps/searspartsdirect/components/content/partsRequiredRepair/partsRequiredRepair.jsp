<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredParts/>
<h3><cq:text property="partsRequiredTitle" placeholder="Parts Required:" /></h3>
<ul class="offset-small">
<c:forEach var="part" items="${parts}">
	<li data-partid="${part.id}">${part.text}</li>
</c:forEach>
</ul>
<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />