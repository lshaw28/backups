<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:ResolveHazardTipWarning adhocField="tipTextEntered" choiceField="tipChosen" placeholder=""/>
<c:choose>
	<c:when test="${empty htwImage}">
		<%-- displayImage tag never acts against other than the current resource.
		So cannot use it for displaying global images. --%>
		<%-- <spd:displayImage path="/etc/spdAssets/globalConfig/tipThumbnail" /> --%>
		<cq:include path="/etc/spdAssets/globalConfig/tipThumbnail" resourceType="foundation/components/image" />
	</c:when>
	<c:otherwise>
		<%-- when/if scaffolding image goes away / is made responsive, this needs to change --%>
		<%-- <spd:displayImage path="${htwImage}" /> --%>
		<cq:include path="${htwImage}" resourceType="foundation/components/image" />
	</c:otherwise>
</c:choose>
<cq:include path="/etc/spdAssets/globalConfig/tipPrefix"
	resourceType="searspartsdirect/components/content/tipPrefix" /> ${htwText}