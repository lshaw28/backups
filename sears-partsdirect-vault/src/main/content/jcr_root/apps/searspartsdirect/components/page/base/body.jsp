<%@ include file="/apps/searspartsdirect/global.jsp"%><%
%><body class="${cqCssClass}"><%
%><cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext" /><%
%> <a id="skip-nav" href="#content" tabindex="0">skip navigation</a><%
%>	<a name="brandBar"></a><%
%><cq:include path="brandBar" resourceType="/apps/searspartsdirect/components/base/brandBar" /><%
%>	<div id="viewport">
		<div role="main" class="container-fluid"><%
%><cq:include script="header.jsp" /><%
%><cq:include script="content.jsp" /><%
%><cq:include script="footer.jsp" /><%
%><cq:include script="jsbody.jsp" /><%
%>		</div>
	</div><%
%><cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents" /><%
%>	<cq:include path="loginModal" resourceType="searspartsdirect/components/base/loginModal" /><%
%>	<cq:include path="registerModal" resourceType="searspartsdirect/components/base/registerModal" /><%
%>	<cq:include path="forgotPasswordModal" resourceType="searspartsdirect/components/base/forgotPasswordModal" /><%
%>	<cq:include path="promoModal" resourceType="searspartsdirect/components/base/promoModal" /><%
%>	<cq:include path="airFilterPromoModal" resourceType="searspartsdirect/components/base/airFilterPromoModal" /><%
%>	<cq:include path="chatOfflineModal" resourceType="searspartsdirect/components/base/chatOfflineModal" /><%
%>	<cq:include path="estimatedDeliveryModal" resourceType="searspartsdirect/components/base/estimatedDeliveryModal" /><%
%>	<cq:include path="shopYourWayModal" resourceType="searspartsdirect/components/base/shopYourWayModal" /><%
%>	<cq:include path="returnPolicyModal" resourceType="searspartsdirect/components/base/returnPolicyModal" /><%
%>	<div id="addToCartAnimation" class="addToCartAnimation">
        <i class="icon-shopping-cart">&nbsp;</i>
        <p>Added to Cart</p>
    </div>
</body>