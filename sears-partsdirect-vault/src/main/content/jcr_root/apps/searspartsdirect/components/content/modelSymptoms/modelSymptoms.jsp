<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<!--  in order to test use the test url-http://localhost:4502/content/searspartsdirect/en/home.kenmore.refrigerator.123.html  -->
<h2>
	Repair Symptoms for ${brandRelation.title}  ${productCategoryRelation.title} Model #${modelRelation}
</h2>

<spd:getModelSymptoms categoryPath="${productCategoryRelation.path}" />

<table>
<tr><td>Symptom</td><td>Symptom Frequency</td></tr>
<c:forEach var="symptom" items="${categorySymptoms}">
	<tr>
		<td><a href="/content/searspartsdirect/en/${brandRelation.title}/${productCategoryRelation.trueName}/model-${modelRelation}-repair/symptom/${symptom.id}.html">${symptom.title}</a></td>
		<td>No data available</td>
	</tr>
</c:forEach>
</table>