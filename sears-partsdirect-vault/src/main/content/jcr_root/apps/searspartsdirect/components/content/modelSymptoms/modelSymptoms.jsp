<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<h2>
	Repair Symptoms for ${brandRelation.title}  ${productCategoryRelation.title} Model #${modelRelation}
</h2>

<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />

<!-- check the api for model symptoms if yes, then display otherwise make the call to the following tag-->
<spd:getModelSymptoms brandName="${brandRelation.title}" categoryName="${productCategoryRelation.title}" modelNumber="${modelRelation}" />
<!--  if no category symptoms then display the featured guide -->
    	<c:choose>
    		<c:when test="${not empty modelSymptoms}">
    			<table class="table table-striped">
				    <thead>
				        <tr>
				            <th>Symptom</th>
				            <th>Frequency</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="symptom" items="${modelSymptoms}">
				            <tr>
				            	<c:set var="symptomUrl" value="/content/searspartsdirect/en/${brandRelation.trueName}/${productCategoryRelation.trueName}/model-${modelRelation}-repair/symptom/${symptom.id}.html" />
				                <td><a href="${fn:toLowerCase(symptomUrl)}">${symptom.title}</a></td>
				                <td>
				                	<c:choose>
				                		<c:when test="${not empty symptom.frequency}">
				                			<span class="big-number">${symptom.frequency}%&nbsp</span>of repairs
				                		</c:when>
				                		<c:otherwise>
				                			No data available
				                		</c:otherwise>
				                	</c:choose>
				                </td>
				            </tr>
				        </c:forEach>
		           </tbody>
				</table>
	        </c:when>
	        <c:otherwise>
	        	<cq:include path="mostPopularGuide" resourceType="searspartsdirect/components/content/mostPopularGuide" />
	        </c:otherwise>
		 </c:choose>
	       
