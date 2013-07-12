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


 
<table class="table table-striped">
    <thead>
        <tr>
            <th>Symptom</th>
            <th>Frequency</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="symptom" items="${categorySymptoms}">
            <tr>
            	<c:set var="symptomUrl" value="/content/searspartsdirect/en/${brandRelation.title}/${productCategoryRelation.trueName}/model-${modelRelation}-repair/symptom/${symptom.id}.html" />
                <td><a href="${fn:toLowerCase(symptomUrl)}">${symptom.title}</a></td>
                <td><span class="big-number">74%&nbsp</span>of repairs</td>
            </tr>
        </c:forEach>
	        <c:otherwise>
	        	<cq:include path="mostPopularGuide" resourceType="searspartsdirect/components/content/mostPopularGuide" />
	        </c:otherwise>
	     </c:choose>
    </tbody>
</table>

<!--  
<cq:include path="categorySymptoms" resourceType="searspartsdirect/components/content/c6ategorySymptoms" />-->
