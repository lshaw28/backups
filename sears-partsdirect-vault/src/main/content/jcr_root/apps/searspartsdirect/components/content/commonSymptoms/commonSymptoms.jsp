<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getProductCategoryName />
<h2><cq:text property="text1" placeholder=""/>  ${productCategoryName}  <cq:text property="text2" placeholder=""/></h2>
<p><cq:text property="optionalDescription" placeholder=""/></p>

<spd:getCommonSymptoms categoryName="${productCategoryName}" />
<c:forEach items="${commonSymptoms}">
     <a href="${commonSymptomUrl}">{commonSymptomText}</a>
</c:forEach>