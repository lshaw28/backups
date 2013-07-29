<%@ include file="/apps/searspartsdirect/global.jsp" %><%--
--%><c:set var="hazardPrefix" scope="request"><cq:text property="./hazardPrefix" placeholder="" /></c:set><%--
--%><c:if test="${empty hazardPrefix}"><c:set var="hazardPrefix" scope="request" value="Hazard: " /></c:if>