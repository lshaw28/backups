<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<h2>
	Repair Symptoms for ${brandRelation.title}  ${productCategoryRelation.title} Model #${modelRelation}
</h2>

<!-- check the api for model symptoms if yes, then display otherwise make the call to the following tag-->
<spd:getModelSymptoms categoryPath="${productCategoryRelation.path}" />
<!--  if no category symptoms then display the featured guide -->

<table>
<tr><td>Symptom</td><td>Symptom Frequency</td></tr>
<c:forEach var="symptom" items="${categorySymptoms}">
	<tr>
		<td><a href="/content/searspartsdirect/en/${brandRelation.title}/${productCategoryRelation.trueName}/model-${modelRelation}-repair/symptom/${symptom.id}.html">${symptom.title}</a></td>
		<td>No data available</td>
	</tr>
</c:forEach>
</table>