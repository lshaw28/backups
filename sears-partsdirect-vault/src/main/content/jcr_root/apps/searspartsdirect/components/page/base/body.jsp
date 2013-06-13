<%@ include file="/apps/searspartsdirect/global.jsp"%>
<c:set var="pageClientLib"><cq:text property="pageClientLib" placeholder="" /></c:set><body class="<cq:text property="pageCssClassName" placeholder="" />">
	<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext" />
	<cq:include path="brandBar" resourceType="/apps/searspartsdirect/components/base/brandBar" />
	<div id="viewport">
		<div role="main" class="container-fluid">
			<cq:include script="header.jsp" />
			<cq:include script="content.jsp" />
			<cq:include script="footer.jsp" />
			<cq:include script="jsbody.jsp" />
		</div>
	</div>
	<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents" />
	<cq:include path="loginModal" resourceType="searspartsdirect/components/base/loginModal" />
	<cq:include path="registerModal" resourceType="searspartsdirect/components/base/registerModal" />
</body>