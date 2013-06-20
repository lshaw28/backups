<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredTools/>
<h3><cq:text property="toolsRequiredTitle" placeholder="Tools Required:" /></h3>
<ul class="offset-small">
<c:forEach var="tool" items="${tools}">
	<li data-toolid="${tool.id}">${tool.text}</li>
</c:forEach>
</ul>