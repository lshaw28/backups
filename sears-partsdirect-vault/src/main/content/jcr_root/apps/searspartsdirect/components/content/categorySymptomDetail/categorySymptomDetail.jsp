<%@ include file="/apps/searspartsdirect/global.jsp" %>

<p>symptom details page</p>
<spd:getSymptomDetail partsRequired="false" />

<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description}</p>

<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />

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

	Guides:-
	<c:if test="${not empty jobCode.guides}">
		<c:forEach var="guide" items="${jobCode.guides}">
			<spd:linkResolver value="${guide.url}"/>
			<p><a href="${url}">${guide.title}</a></p>
		</c:forEach>
	</c:if>

	<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />

	<c:if test="${jobCode.partTypeModel != null}">
		<p>Don't have your model number?
			<a href="http://www.searspartsdirect.com/partsdirect/part-model/Frigidaire-Parts/Cooktop-Parts/Model-33003/1428/0121050&partType=${jobCode.partTypeModel.title}">
				Shop ${jobCode.partTypeModel.title}
			</a>
		</p>
	</c:if>
</c:forEach>