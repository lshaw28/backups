<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="innerHeader ie_innerHeader ">
	<div class="body_shadow_left"></div>
	<div class="body_shadow_right"></div>
	<cq:include path="homeLogo" resourceType="/apps/searspartsdirect/components/base/homeLogo" />
	<spd:GetUserData/>
	<c:set var="shoppingCart" scope="request" value="${userData.cart.cartLines}"/>
	<c:set var="myProfileModels" scope="request" value="${userData.myProfileModels}"/>
	<c:if test="${userData.loggedIn}">
		<c:set var="loggedIn" scope="request" value="${userData.loggedIn}"/>
		<c:set var="firstName" scope="request" value="${userData.firstName}"/>
		<c:set var="lastName" scope="request" value="${userData.lastName}"/>
	</c:if>

	<div id="headerNavigation">
		<div class="trigger"></div>
		<cq:include path="loginNav" resourceType="/apps/searspartsdirect/components/base/loginNav" />
		<div class="clear"></div>
		<cq:include path="cartNav" resourceType="/apps/searspartsdirect/components/base/cartNav" />
	</div>
	<cq:include path="searchPanel" resourceType="/apps/searspartsdirect/components/base/searchPanel" />
	<cq:include path="headerPromo" resourceType="/apps/searspartsdirect/components/base/headerPromo" />
</div>