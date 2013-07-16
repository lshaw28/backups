<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail partsRequired="true" symptomId ="${symptomRelation.id}" />

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<c:set var="modelRepairUrl" value="/content/searspartsdirect/en/${brandRelation.trueName}/${productCategoryRelation.trueName}/model-${modelRelation}-repair.html"/>
<a href="${modelRepairUrl}">Return to Repair help for model #${modelRelation}</a>

<c:set var="jobCodes" value="${modelSymptom.jobCodeModels}" scope="request" />
<c:set var="modelNumber" value="${modelRelation}" scope="request" />
<cq:include path="jobCodePartsFinder" resourceType="searspartsdirect/components/content/jobCodePartsFinder" />

<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description} </p>

<c:forEach var="jobCode" items="${modelSymptom.jobCodeModels}">
		<c:if test="${jobCode.partTypeModel != null &&  jobCode.partTypeModel.imagePath != null}">
			<spd:displayImage path="${jobCode.partTypeModel.imagePath}"/>
		</c:if>
	<h3>${jobCode.title}</h3>
	<p>${jobCode.description}</p>
	
	<!--  Guides:- -->
	<c:if test="${not empty jobCode.guides}">
		<c:forEach var="guide" items="${jobCode.guides}">
			<spd:linkResolver value="${guide.url}"/>
			<p><a href="${url}">${guide.title}</a></p>
		</c:forEach>
	</c:if>
	
	<c:set var="recommendedParts" value="${jobCodeParts[jobCode.id]}" scope="request" />
	<c:choose>
		<c:when test="${not empty recommendedParts}">
			<cq:include path="displayRecommendedParts" resourceType="searspartsdirect/components/content/displayRecommendedParts" />
		</c:when>
		<c:otherwise>
			<!--  no parts found then show the following block -->
			<spd:getPartsLinkTag brandName="${brandRelation.title}" categoryName="${productCategoryRelation.title}" modelNumber="${modelRelation}"/>
			<c:if test="${not empty findPartUrl && not empty jobCode.partTypeModel}">
				<p>
					<a href="${findPartUrl}">Find ${jobCode.partTypeModel.title} in this model</a>
				</p>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>


