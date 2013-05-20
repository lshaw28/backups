<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getProductCategoryName />

<h2><cq:text property="text1" placeholder=""/> ${productCategoryName}  <cq:text property="text2" placeholder=""/><cq:text property="errorCodesRichtext" placeholder=""/></h2>

<spd:getCommonSymptoms categoryName="${productCategoryName}" />

<c:forEach items="${commonSymptoms}">
     <a href="${commonSymptomUrl}">{commonSymptomText}</a>
</c:forEach>