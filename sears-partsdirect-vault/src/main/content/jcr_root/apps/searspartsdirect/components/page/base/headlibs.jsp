<%@ include file="/apps/searspartsdirect/global.jsp" %>
<!-- Viewport -->
<meta http-equiv="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- Global Styles and Fonts -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib categories="cq.foundation-main" />
<cq:includeClientLib css="apps.searspartsdirect" />
<!-- Template-specific Styles -->
<cq:include script="templatelibs.jsp" />
<c:if test="${isEditMode or isDesignMode}">
	<!-- Author Styles -->
    <cq:includeClientLib css="apps.searspartsdirect.cq.edit" />
</c:if>
<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>