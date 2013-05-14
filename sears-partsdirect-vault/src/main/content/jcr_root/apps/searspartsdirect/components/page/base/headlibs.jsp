<%@ include file="/apps/searspartsdirect/global.jsp" %>
<% // @TODO: Global meta tags %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<cq:includeClientLib categories="cq.foundation-main" />
<cq:includeClientLib css="apps.searspartsdirect" />

<cq:include script="templatelibs.jsp"/>

<c:if test="${isEditMode or isDesignMode}">
    <cq:includeClientLib css="apps.searspartsdirect.cq.edit"/>
</c:if>