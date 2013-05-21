<%@ include file="/apps/searspartsdirect/global.jsp" %>

    <cq:text property="partsRequiredTitle"/>
    
    <spd:getPartsRequired />
    
    <c:forEach items="${partsRequiredList}" var="item">
        <ul><li><c:out value="${item}" /></li></ul>
    </c:forEach>