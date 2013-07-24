<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="pageTitle"><cq:text property="header" placeholder="${currentPage.title}"/></c:set>
<c:if test="${empty pageTitle}"><c:set var="pageTitle">${currentPage.title}</c:set></c:if>
<h1>${pageTitle}</h1>