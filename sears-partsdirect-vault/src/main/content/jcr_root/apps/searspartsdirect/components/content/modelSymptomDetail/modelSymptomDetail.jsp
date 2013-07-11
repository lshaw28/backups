<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail partsRequired="true" symptomId ="${symptomRelation.id}" />

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<a href="content/searspartsdirect/en/${brandRelation.title}/${productCategoryRelation.title}/model-${modelRelation}-repair.html">Return to Repair help for model #${modelRelation}</a>
<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description}</p>

<c:forEach var="jobCode" items="${modelSymptom.jobCodeModels}">
	<c:choose>
		<c:when test="${jobCode.partTypeModel != null &&  jobCode.partTypeModel.imagePath != null}">
			<spd:displayImage path="${jobCode.partTypeModel.imagePath}"/>
		</c:when>
		<c:otherwise>
			<p>show a default no jobcode part type image</p>
		</c:otherwise>
	</c:choose>
	<h3>${jobCode.title}</h3>
	<p>${jobCode.description}</p>

	<!--  Guides:- -->
	<c:if test="${not empty jobCode.guides}">
		<c:forEach var="guide" items="${jobCode.guides}">
			<spd:linkResolver value="${guide.url}"/>
			<p><a href="${url}">${guide.title}</a></p>
		</c:forEach>
	</c:if>

	<c:if test="${jobCode.partTypeModel != null}">
	<!-- TODO: need to find out the final url for below -->
		<p><a href="${mainSitePath}/partsdirect/part-model/${brandRelation.title}-Parts/${productCategoryRelation.title}-Parts/Model-${modelRelation}/1428/0121050&partType=${jobCode.partTypeModel.title}">
				Find ${jobCode.partTypeModel.title} in this model
			</a>
		</p>
	</c:if>
</c:forEach>