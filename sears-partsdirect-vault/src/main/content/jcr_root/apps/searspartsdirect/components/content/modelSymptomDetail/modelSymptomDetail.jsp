<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:GetUrlRelation relationType="productCategory" />
<%-- <spd:GetUrlRelation relationType="brand" />--%>
<%-- <spd:GetUrlRelation relationType="model" />--%>

<%-- <h2>
Repair Symptoms for ${brandRelation.title} ${productCategoryRelation.title} Model #${modelRelation.title}
</h2>--%>

${productCategoryRelation.path}

<spd:getModelSymptoms categoryPath="${productCategoryRelation.path}" />

<c:forEach var="symptom" items="${categorySymptoms}">
	<p><a href="/<brand>/<category>/model-<model-number>-repair/symptom/${symptom.id}">${symptom.title}</a></p>
</c:forEach>

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