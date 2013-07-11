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
                <td><a href="http://www.google.com">${symptom.title}</a></td>
                <td><span class="big-number">74%&nbsp</span>of repairs</td>
            </tr>
        </c:forEach>
    </tbody>

</table>

<%-- <spd:getRelation single="true" assetType="productCategory"/> --%>

<%--
<c:choose>
	<c:when test="${productCategoryRelation != null}">
		<h2>
			<!--  <cq:text property="text1" placeholder=""/> -->
			${productCategoryRelation.title} Symptoms
			<!--  <cq:text property="text2" placeholder=""/> -->
		</h2>
		<p><cq:text property="optionalDescription" placeholder=""/></p>

		<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
		<c:forEach var="symptom" items="${symptomList}" varStatus="currentItem">
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 1}">
					<div class="row-fluid">
				</c:when>
			</c:choose>
				<div class="span6">
					<a href="/${productCategoryRelation.title}-repair/symptom/${symptom.id}">${symptom.title}</a>
				</div>
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>No items are available.</p>
	</c:otherwise>
</c:choose> --%>
