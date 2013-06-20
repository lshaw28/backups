<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="stepCounter" scope="request">0</c:set>
<c:set var="localTitle"><cq:text property="./instructionsTitle" placeholder="" /></c:set>

<c:choose>
	<c:when test="${empty localTitle}">
		<cq:include path="/etc/spdAssets/globalConfig/instructionsTitle"
				resourceType="searspartsdirect/components/content/globalInstructionsTitle" />
	</c:when>
	<c:otherwise><h3>${localTitle}</h3></c:otherwise>
</c:choose>

<div class="row-fluid">
	<div class="span12 offset-small">
		<cq:include path="instructionsParsys" resourceType="foundation/components/parsys" />
	</div>
</div>