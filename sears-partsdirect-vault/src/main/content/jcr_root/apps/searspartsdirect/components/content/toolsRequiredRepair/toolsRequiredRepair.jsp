<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredTools/>

<p>Tools Required:</p>
<ul>
<c:forEach var="tool" items="${tools}">
	<li>${tool.text} (${tool.id})</li>
</c:forEach>
</ul>