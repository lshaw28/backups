<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getParentPage />

<c:if test="${!empty parentPage}">
	<a href="${parentPage}.html" >
		<span record="'goToParentPage', {'parentPageURL': '${parentPage}.html'}"></span>
		<cq:text property="linkName" placeholder="*replace with text to redirect to parent page*" />
	</a>
</c:if>