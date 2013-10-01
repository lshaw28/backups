<%@include file="/libs/foundation/global.jsp" %><%
%><%@taglib prefix="spd" uri="http://cms.testsears.com/bundles/cq/tags" %><%
%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
%><%@page import="java.util.LinkedList" %><%
%><spd:defineObjects /><%
%><spd:getPDUrl /><%
%><spd:getLocalUrl /><%
%><spd:getConstants /><%
%><c:set var="mainSitePath" scope="request">${nonSecurePDUrl}</c:set><%
%><c:set var="secureMainSitePath" scope="request">${securePDUrl}</c:set>