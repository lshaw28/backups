<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id ="${symptomRelation.id}" />

<h2>These repairs may help solve your problem:</h2>
<c:forEach var="jobCode" items="${symptom.jobCodeModels}">
	<div class="categorySymptomBorder">
		<h3><c:out value="${jobCode.title} "/></h3>
		<div class="row-fluid">
			<c:choose>
				<c:when test="${jobCode.partTypeModel != null && jobCode.partTypeModel.imagePath != null && fn:indexOf(jobCode.partTypeModel.imagePath, '/0.gif') == -1}">
					<div class="new-span-quarter categorySymptomImage">
						<spd:displayImage path="${jobCode.partTypeModel.imagePath}" altText="${jobCode.partTypeModel.title}"/>
					</div>
					<c:set var="textClass" value="new-span-three-quarters" />
					<c:set var="guideClass" value="span9" />
				</c:when>
				<c:otherwise>
					<c:set var="textClass" value="span12" />
					<c:set var="guideClass" value="span12" />
				</c:otherwise>
			</c:choose>
			<div class="categorySymptomContent ${textClass}">
				<p>${jobCode.description}</p>
			</div>
			<div class="categorySymptomGuides ${guideClass}">
				<c:if test="${not empty jobCode.guides}">
					<p><c:forEach var="guide" items="${jobCode.guides}" varStatus="currentGuide">
						<spd:linkResolver value="${guide.url}"/>
						<c:choose>
							<c:when test="${currentGuide.last}">
								<a class="last" href="${url}"><c:out value="${guide.title} "/></a>
							</c:when>
							<c:otherwise>
								<a href="${url}"><c:out value="${guide.title} "/></a><br />
							</c:otherwise>
						</c:choose>
					</c:forEach></p>
				</c:if>
			</div>
		</div>
	</div>
	<cq:include path="modelSearch" resourceType="searspartsdirect/components/content/responsiveFindThisPart" />
</c:forEach>

<div class="categorySymptoms">
    <cq:include script="/apps/searspartsdirect/components/content/categorySymptoms/categorySymptoms.jsp" />
</div>