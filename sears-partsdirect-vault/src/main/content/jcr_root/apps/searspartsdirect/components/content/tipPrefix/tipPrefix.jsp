<%@ include file="/apps/searspartsdirect/global.jsp" %><%--
--%><c:set var="tipPrefix" scope="request"><cq:text property="./tipPrefix" placeholder="" /></c:set><%--
--%><c:if test="${empty tipPrefix}"><c:set var="tipPrefix" scope="request" value="Tip: " /></c:if>