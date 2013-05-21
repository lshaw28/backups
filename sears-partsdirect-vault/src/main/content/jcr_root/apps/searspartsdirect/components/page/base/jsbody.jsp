<%@ include file="/apps/searspartsdirect/global.jsp" %>
<!-- Global JavaScript -->
<cq:includeClientLib js="apps.searspartsdirect" />
<c:if test="${isEditMode or isDesignMode}">
	<!-- Author Mode JavaScript -->
	<cq:includeClientLib js="apps.searspartsdirect.cq.edit" />
</c:if>