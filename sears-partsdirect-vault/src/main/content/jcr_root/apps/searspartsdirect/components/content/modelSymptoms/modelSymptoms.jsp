<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!--  <h2>Most Common <spd:getProductCategoryName /> Symptoms</h2>  -->

<h2><cq:text property="text1"/> ${brandCategoryModelNo} <cq:text property="text"/></h2>

Repair Symptoms List
<c:forEach items="${modelSymptoms}">
     <a href="${commonSymptomUrl}">{commonSymptomText}</a>
</c:forEach>


