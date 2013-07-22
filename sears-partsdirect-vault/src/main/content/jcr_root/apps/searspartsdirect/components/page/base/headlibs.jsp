<%@ include file="/apps/searspartsdirect/global.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib categories="cq.foundation-main" />
<cq:includeClientLib css="apps.searspartsdirect" />
<c:if test="${isEditMode or isDesignMode}">
	<cq:includeClientLib css="apps.searspartsdirect.cq.edit" />
</c:if>
<cq:includeClientLib css="apps.searspartsdirect.social" />
<cq:include script="templatelibs.jsp" />
<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>