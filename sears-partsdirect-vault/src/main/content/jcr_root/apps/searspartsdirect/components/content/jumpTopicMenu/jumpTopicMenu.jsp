<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getTopicJump />

<c:if test="${not empty jumpTopics}">
	<c:set var="jumpMenuTitle"><cq:text property="jumpMenuTitle" placeholder="" /></c:set><%--
	--%><c:if test="${empty jumpMenuTitle}"><c:set var="jumpMenuTitle" value="Topics" /></c:if><%--
	--%><h4><c:out value="${jumpMenuTitle}" /></h4>

	<c:set var="jumpFirstItem" scope="request"><cq:text property="jumpMenuFirstItem" placeholder="" /></c:set>
	<c:if test="${empty jumpFirstItem}"><c:set var="jumpFirstItem" scope="request" value="Select Topic" /></c:if>

	<p><select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="${jumpFirstItem}" data-navigate="true">
		<c:forEach items="${jumpTopics}" var="current" varStatus="item">
			<option value="#${current.anchorName}"><c:out value="${current.linkText}" /></option>
		</c:forEach>
	</select></p>
</c:if>