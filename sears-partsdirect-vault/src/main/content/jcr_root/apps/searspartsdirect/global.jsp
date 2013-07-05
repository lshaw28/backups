<%@include file="/libs/foundation/global.jsp" %>
<%@taglib prefix="spd" uri="http://cms.testsears.com/bundles/cq/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.LinkedList" %>
<spd:defineObjects />
<spd:getPDUrl />
<spd:getConstants />

<c:set var="mainSitePath" scope="request">${PDUrl}</c:set>
<% // @TODO: Where should we actually be posting to? %>
<c:set var="loginPath" scope="request" value="https://sso.shld.net/shccas/shcLogin"></c:set>
<c:set var="registerPath" scope="request" value="https://www.searspartsdirect.com/partsdirect/register.pd"></c:set>