<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail partsRequired="false" symptomId ="${symptomRelation.id}" />

<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description}</p>

<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />

<h2>Checking these parts may help solve your problem:</h2>
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

	<!-- Guides:- -->
	<c:if test="${not empty jobCode.guides}">
		<c:forEach var="guide" items="${jobCode.guides}">
			<spd:linkResolver value="${guide.url}"/>
			<p><a href="${url}">${guide.title}</a></p>
		</c:forEach>
	</c:if>

	<p><b>Find this part</b></p>

	<p>For the manuals, repair guides, and specific part recommendations, enter your model number.</p>
	
	<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />
	
	<a href="#">Help me find my model number</a><!-- open the model number finder flyout from the header -->
	
</c:forEach>