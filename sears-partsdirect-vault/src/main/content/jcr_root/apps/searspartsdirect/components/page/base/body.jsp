<%@ include file="/apps/searspartsdirect/global.jsp"%>
<c:set var="pageClientLib"><cq:text property="pageClientLib" placeholder="" /></c:set><body class="<cq:text property="pageCssClassName" placeholder="" />">
	<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext" />
	<cq:include script="header.jsp" />
	<cq:include script="content.jsp" />
	<cq:include script="footer.jsp" />
	<cq:include script="jsbody.jsp" />
	
	<c:if test="${isEditMode}">
		<cq:includeClientLib js="apps.searspartsdirect.cq.edit" />
	</c:if>
	<cq:include path="skyscraper_adv" resourceType="/apps/searspartsdirect/components/content/skyscraperAd" />
	
	<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents" />
</body>