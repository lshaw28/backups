<%@include file="/libs/foundation/global.jsp" %>
<%@taglib prefix="spd" uri="http://cms.testsears.com/bundles/cq/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.LinkedList" %>
<spd:defineObjects />
<spd:getPDUrl />
<spd:getLocalUrl />
<spd:getConstants />

<c:set var="mainSitePath" scope="request">${nonSecurePDUrl}</c:set>
<c:set var="secureMainSitePath" scope="request">${securePDUrl}</c:set>
<c:set var="currentSitePath" scope="request">${nonSecureLocalUrl}</c:set>
<c:set var="secureCurrentSitePath" scope="request">${secureLocalUrl}</c:set>

<c:set var="nonSecureCommercialUrl" scope="request">${nonSecureCommercialUrl}</c:set>
<c:set var="secureCommercialUrl" scope="request">${secureCommercialUrl}</c:set>

<c:set var="securedPage" scope="request">${securedPage}</c:set>


