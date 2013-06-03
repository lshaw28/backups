<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="home_header ">
	<div class="inner_header ie_inner_header ">
		<div class="body_shadow_left"></div>
		<div class="body_shadow_right"></div>
		<cq:include path="homeLogo" resourceType="/apps/searspartsdirect/components/content/homeLogo" />
		<div class="header_right_panel ">
			<div class="trigger"></div>
			<cq:include path="loginNav" resourceType="/apps/searspartsdirect/components/content/loginNav" />
			<div class="clear"></div>
			<cq:include path="cartNav" resourceType="/apps/searspartsdirect/components/content/cartNav" />
		</div>
		<cq:include path="searchPanel" resourceType="/apps/searspartsdirect/components/content/searchPanel" />
		<cq:include path="waterFilterPromo" resourceType="/apps/searspartsdirect/components/content/promo" />
	</div>
	<div class="clear"></div>
</div>