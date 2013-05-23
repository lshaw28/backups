<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getPartsRequired />

<cq:text property="partsRequiredTitle" placeholder="*replace with text for parts required*" />
<ul>
	<c:forEach items="${partsRequiredList}" var="item">
		<li><c:out value="${item}" /></li>
	</c:forEach>
</ul>