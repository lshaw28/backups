<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHtw adhocField="hazardTextEntered" choiceField="hazardChosen" placeholder="NO HAZARD FOUND."/>
<c:choose>
	<c:when test="${empty htwImage}">
		<%-- displayImage tag never acts against other than the current resource.
		So cannot use it for displaying global images. --%>
		<%-- <spd:displayImage path="/etc/spdAssets/globalConfig/hazardThumbnail" /> --%>
		<cq:include path="/etc/spdAssets/globalConfig/hazardThumbnail" resourceType="foundation/components/image" />
	</c:when>
	<c:otherwise>
		<%-- when/if scaffolding image goes away / is made responsive, this needs to change --%>
		<%-- <spd:displayImage path="${htwImage}" /> --%>
		<cq:include path="${htwImage}" resourceType="foundation/components/image" />
	</c:otherwise>
</c:choose>
<cq:include path="/etc/spdAssets/globalConfig/hazardPrefix" 
			resourceType="searspartsdirect/components/content/hazardPrefix" />
${htwText}