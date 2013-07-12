<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getTopicJump />

<c:if test="${not empty jumpTopics}">
	<cq:include path="./jumpMenuTitle" resourceType="searspartsdirect/components/content/maintenanceJumpMenuTitle" />
	<cq:include path="./jumpMenuFirstItem" resourceType="searspartsdirect/components/content/maintenanceJumpMenuFirstItem" />
	
	<p><select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="${maintJumpFirstItem}" data-navigate="true">
		<c:forEach items="${jumpTopics}" var="current" varStatus="item">
			<option value="#${current.anchorName}">${current.linkText}</option>
		</c:forEach>
	</select></p>
 </c:if>