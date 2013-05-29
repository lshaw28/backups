<%@ include file="/apps/searspartsdirect/global.jsp" %>
<!--  <h2>Most Common <spd:getProductCategoryName /> Symptoms</h2>  -->
<spd:getBrandCategoryModelNo />
<h2><cq:text property="text1"/> ${brandCategoryModelNo} <cq:text property="errorCodesRichtext"/></h2>

<spd:getModelSymptoms modelNumber="${brandCategoryModelNo}" />
<p>Repair Symptoms List</p>
<c:forEach items="${modelSymptoms}">
	 <a href="${commonSymptomUrl}">{commonSymptomText}</a>
</c:forEach>