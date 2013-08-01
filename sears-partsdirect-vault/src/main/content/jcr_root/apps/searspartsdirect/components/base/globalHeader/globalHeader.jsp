<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="innerHeader ie_innerHeader ">
	<div class="body_shadow_left"></div>
	<div class="body_shadow_right"></div>
	<cq:include path="homeLogo" resourceType="/apps/searspartsdirect/components/base/homeLogo" />
	<spd:getUserData/>
	<c:set var="userId" scope="request" value="${userData.userId}"/>
	<c:set var="shoppingCart" scope="request" value="${userData.cart.cartLines}"/>
	<c:set var="myProfileModels" scope="request" value="${userData.myProfileModels.profileModelsList}"/>
	<c:if test="${userData.loggedIn}">
		<c:set var="loggedIn" scope="request" value="${userData.loggedIn}"/>
		<c:set var="firstName" scope="request" value="${userData.firstName}"/>
		<c:set var="lastName" scope="request" value="${userData.lastName}"/>
	</c:if>
	<c:set var="cartCount" scope="request" value="${cartCount}"/>
	<div id="headerNavigation">
		<div class="trigger"></div>
		<cq:include path="loginNav" resourceType="/apps/searspartsdirect/components/base/loginNav" />
		<div class="clear"></div>
		<cq:include path="cartNav" resourceType="/apps/searspartsdirect/components/base/cartNav" />
	</div>
	<cq:include path="searchPanel" resourceType="/apps/searspartsdirect/components/base/searchPanel" />
	<cq:include path="headerPromo" resourceType="/apps/searspartsdirect/components/base/headerPromo" />
</div>