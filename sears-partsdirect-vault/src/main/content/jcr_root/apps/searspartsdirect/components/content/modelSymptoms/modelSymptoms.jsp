<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<h2>
	Repair Symptoms for ${brandRelation.title}  ${productCategoryRelation.title} Model #${modelRelation}
</h2>
Here are the most common symptoms we've seen for this model. Select a symptom to view which parts have been used to fix that problem.<br/>
<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />

<spd:getModelSymptoms brandName="${brandRelation.title}" categoryName="${productCategoryRelation.title}" modelNumber="${modelRelation}" />

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
								<c:set var="symptomUrl" value="/content/searspartsdirect/en/symptom/${symptom.id}.html" />
								<td><a href="${fn:toLowerCase(symptomUrl)}"><c:out value="${symptom.title}" /></a></td>
								<td>
									<c:choose>
										<c:when test="${not empty symptom.frequency}">
											<span class="big-number"><c:out value="${symptom.frequency}" />%&nbsp</span>of repairs
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
				<%--  if no symptoms then display the featured guide --%>
				<cq:include path="mostPopularGuide" resourceType="searspartsdirect/components/content/mostPopularGuide" />
			</c:otherwise>
		</c:choose>

