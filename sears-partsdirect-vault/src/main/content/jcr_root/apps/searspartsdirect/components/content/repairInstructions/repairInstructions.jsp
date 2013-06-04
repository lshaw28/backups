<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="stepCounter" scope="request">0</c:set>
<c:set var="localTitle"><cq:text property="./instructionsTitle" placeholder="" /></c:set> 
<h2>
	<c:choose>
		<c:when test="${empty localTitle}">
			<cq:include path="/etc/spdAssets/globalConfig/instructionsTitle" 
					resourceType="searspartsdirect/components/content/globalInstructionsTitle" />
		</c:when>
		<c:otherwise>${localTitle}</c:otherwise>
	</c:choose>
</h2>
<cq:include path="instructionsParsys" resourceType="foundation/components/parsys" />