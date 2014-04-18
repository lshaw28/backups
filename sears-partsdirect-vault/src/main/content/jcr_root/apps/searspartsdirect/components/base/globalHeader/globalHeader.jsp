<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><div class="innerHeader ie_innerHeader ">
	<spd:getPDEnvDetail />
	<c:set var="promoAmount" scope="request" value="${promoAmount}"/>
	<c:set var="promoDate" scope="request" value="${promoDate}"/>
	
	<div class="body_shadow_left"></div>
	<div class="body_shadow_right"></div>
	<cq:include path="homeLogo" resourceType="/apps/searspartsdirect/components/base/homeLogo" />
	<c:set var="skipLoginModal" scope="request" value="true"/>
	<c:set var="skipRegisterModal" scope="request" value="true"/>
	<div id="headerNavigation">
		<div class="trigger"></div>
		<cq:include path="loginNav" resourceType="/apps/searspartsdirect/components/base/loginNav" />
		<div class="clear"></div>
		<cq:include path="cartNav" resourceType="/apps/searspartsdirect/components/base/cartNav" />
	</div>
	<cq:include path="searchPanel" resourceType="/apps/searspartsdirect/components/base/searchPanel" />
	
	 <c:if test="${promoFlag eq 'y'}">
		<cq:include path="headaerPromo" resourceType="/apps/searspartsdirect/components/base/headerPromo" />
     </c:if>
	
</div>

<c:choose>
	<c:when test="${param.searchTerm != null}">
		<span record="'searchSuccess',{'pageNameVar': '<%=currentPage.getTitle()%>', 'channel': '<%=currentPage.getProperties().get("channel") %>', 'searchTerm': '${param.searchTerm}', 'searchType': '${param.searchType}', 'searchTotal': '-1', 'resultType': 'Common Terms'}"></span>
	</c:when>
	<c:otherwise>
		<span record="'globalHeaderLoadEvent',{'pageNameVar': '<%=currentPage.getTitle()%>', 'channel': '<%=currentPage.getProperties().get("channel") %>'}"></span>
	</c:otherwise>
</c:choose>