<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getTopicJump />

<c:if test="${not empty jumpTopics}">
	<h4><cq:text property="jumpMenuTitle" placeholder="Topics" /></h4>

	<c:set var="jumpFirstItem" scope="request"><cq:text property="jumpMenuFirstItem" placeholder="Select Topic" /></c:set>
	
	<p><select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="${jumpFirstItem}" data-navigate="true">
		<c:forEach items="${jumpTopics}" var="current" varStatus="item">
			<option value="#${current.anchorName}">${current.linkText}</option>
		</c:forEach>
	</select></p>
 </c:if>