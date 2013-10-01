<%@ include file="/apps/searspartsdirect/global.jsp" %>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib css="cq.foundation-main" />
<cq:includeClientLib css="apps.searspartsdirect" />
<c:if test="${isEditMode or isDesignMode}"><cq:includeClientLib css="apps.searspartsdirect.cq.edit" /></c:if>
<cq:includeClientLib css="apps.searspartsdirect.social" />
<cq:include script="templatelibs.jsp" />
<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>
<cq:includeClientLib js="apps.searspartsdirect.head" />
<c:if test="${isEditMode or isDesignMode}">
<cq:includeClientLib css="apps.searspartsdirect.cq.edit" />
<cq:include script="/libs/wcm/mobile/components/simulator/simulator.jsp"/>
</c:if>