<%@include file="/libs/foundation/global.jsp" %>
<%@taglib prefix="spd" uri="http://cms.testsears.com/bundles/cq/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.LinkedList" %>
<spd:defineObjects />
<spd:getPDUrl />
<spd:getConstants />

<c:set var="mainSitePath" scope="request">${nonSecurePDUrl}</c:set>
<c:set var="secureMainSitePath" scope="request">${securePDUrl}</c:set>
<c:set var="currentSitePath" scope="request">http://localhost:4502</c:set>
<c:set var="secureCurrentSitePath" scope="request">https://localhost:5433</c:set>
<c:set var="loginPath" scope="request" value="https://sso.shld.net/shccas/shcLogin"></c:set>
<c:set var="registerPath" scope="request" value="http://partsbetavip.qa.ch3.s.com/partsdirect/register.pd"></c:set>