<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="rawText"><cq:text property="text"/></c:set>
<spd:cleanText text="${rawText}" />