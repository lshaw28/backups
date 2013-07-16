<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="model" />
<spd:getRepairGuideJobCode />

<c:set var="jobCodes" value="${repairGuideJobCodes}" scope="request" />
<c:set var="modelNumber" value="${modelRelation}" scope="request" />
<cq:include path="jobCodePartsFinder" resourceType="searspartsdirect/components/content/jobCodePartsFinder" />

<c:forEach var="repairGuideJobCode" items="${repairGuideJobCodes}">
	<c:set var="recommendedParts" value="${jobCodeParts[repairGuideJobCode.id]}" scope="request" />
	<cq:include path="displayRecommendedParts" resourceType="searspartsdirect/components/content/displayRecommendedParts" />
</c:forEach>