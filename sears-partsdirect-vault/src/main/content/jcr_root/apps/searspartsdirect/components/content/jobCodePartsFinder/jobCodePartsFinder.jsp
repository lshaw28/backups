<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getJobCodeParts jobCodes="${jobCodes}" modelNumber="${modelNumber}" />
<c:set var="jobCodeParts" value="${jobCodeParts}" scope="request" />
