<%@ include file="/apps/searspartsdirect/global.jsp" %><%--
--%><c:set var="warningPrefix" scope="request"><cq:text property="./warningPrefix" placeholder="" /></c:set><%--
--%><c:if test="${empty warningPrefix}"><c:set var="warningPrefix" scope="request" value="Warning: " /></c:if>