<%@ include file="/apps/searspartsdirect/global.jsp" %>

<h2><cq:text property="text1"/> ${productCategoryName} <cq:text property="text2"/></h2>

<spd:getCommonSymptoms categoryName="${productCategoryName}" />

<c:forEach items="${commonSymptoms}">
     <a href="${commonSymptomUrl}">{commonSymptomText}</a>
</c:forEach>