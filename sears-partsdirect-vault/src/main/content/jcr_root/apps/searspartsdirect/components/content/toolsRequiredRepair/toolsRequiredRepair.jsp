<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:requiredTools/>
<h3><cq:text property="toolsRequiredTitle" placeholder="Tools Required:" default="Tools Required:"/></h3>
<ul class="offset-small">
<c:forEach var="tool" items="${tools}">
	<c:choose>
		<c:when test="${fn:length(tool.url) gt 0}">
			<spd:linkResolver value="${tool.url}" />
			<li data-toolid="${tool.id}"><a href="${url}"><c:out value="${tool.text}" /></a></li>
		</c:when>
		<c:otherwise>
			<li data-toolid="${tool.id}"><c:out value="${tool.text}" /></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>