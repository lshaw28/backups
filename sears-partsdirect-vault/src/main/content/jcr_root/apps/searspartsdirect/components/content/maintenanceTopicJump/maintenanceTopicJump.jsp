<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getMaintenanceTopicJump />

<c:if test="${not empty jumpTopics}">
	<cq:include path="/etc/spdAssets/globalConfig/maintenanceJumpMenuTitle" resourceType="searspartsdirect/components/content/maintenanceJumpMenuTitle" />
	<c:if test="${isEditMode}">
		<cq:include path="/etc/spdAssets/globalConfig/maintenanceJumpMenuFirstItem" resourceType="searspartsdirect/components/content/maintenanceJumpMenuFirstItem" />
	</c:if>
	<p><select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select Topic" data-navigate="true">
		<c:forEach items="${jumpTopics}" var="current" varStatus="item">
			<option value="#${current.anchorName}">${current.linkText}</option>
		</c:forEach>
	</select></p>
 </c:if>