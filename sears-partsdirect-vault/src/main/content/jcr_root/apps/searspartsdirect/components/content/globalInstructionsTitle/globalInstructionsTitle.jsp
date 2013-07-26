<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="titleText"><cq:text property="./titleText" placeholder="${Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK}" /></c:set>
<c:if test="${empty titleText}"><c:set var="titleText">${Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK}</c:set></c:if>
<c:out value="${titleText}" />