<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredTools/>
<h3>Tools Required:</h3>
<ul>
<c:forEach var="tool" items="${tools}">
	<li data-toolid="${tool.id}">${tool.text}</li>
</c:forEach>
</ul>