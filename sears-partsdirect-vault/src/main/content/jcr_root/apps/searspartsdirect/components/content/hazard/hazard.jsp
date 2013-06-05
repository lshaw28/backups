<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHtw adhocField="hazardTextEntered" choiceField="hazardChosen" placeholder="NO HAZARD FOUND."/>
<c:choose>
	<c:when test="${empty htwImage}">
		USE GLOBAL
		<cq:include path="/etc/spdAssets/globalConfig/hazardThumbnail"
				resourceType="foundation/components/image" />
	</c:when>
	<c:otherwise>
		USE SPIFFIC
		<%-- when/if scaffolding image goes away / is made responsive, this needs to change --%>
		<cq:include path="${htwImage}" resourceType="foundation/components/image" />
	</c:otherwise>
</c:choose>
<cq:include path="/etc/spdAssets/globalConfig/hazardPrefix" 
					resourceType="searspartsdirect/components/content/hazardPrefix" />
${htwText}