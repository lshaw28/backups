<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!--  <h2>Most Common <spd:getProductCategoryName /> Symptoms</h2>  -->

<h2><cq:text property="text1"/> ${productCategoryName} <cq:text property="text2"/></h2>

<spd:getCommonSymptoms categoryName="${productCategoryName}" />

Common symptoms are

<c:forEach items="${commonSymptoms}">
  <c:out><a href="${commonSymptomUrl}">{commonSymptomText}</a></c:out>
</c:forEach>




