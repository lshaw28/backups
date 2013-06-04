<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:choose>
	<c:when test="${empty stepCounter}">
		<c:set var="stepCounter" scope="request">1</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="stepCounter" scope="request">${stepCounter + 1}</c:set>
	</c:otherwise>
</c:choose>
<cq:include path="/etc/spdAssets/globalConfig/stepLabelFormatting" resourceType="searspartsdirect/components/content/stepLabelFormatting" />
<cq:text property="./stepTitle" placeholder="" />