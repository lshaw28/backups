<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!--  Parts -->
<c:if test="${not empty jobCodeParts}">
	<c:forEach var="jobCode" items="${jobCodes}">
		<c:forEach var="part" items="${recommendedParts}">
			<c:out value="${part.name}" />
			<br />
			<c:out value="${part.price}" />
			<br />
			<c:out value="${part.number}" />
			<br />
			<c:out value="${part.image.url}" />
			<br />
			<c:out value="${part.frequency}" />
			<br />
			<c:out value="${part.availabilityStatus}" />
			<br />
			<c:out value="${part.url}" />
			<br />
			<c:out value="${part.restriction}" />
			<br />
		</c:forEach>
	</c:forEach>
</c:if>