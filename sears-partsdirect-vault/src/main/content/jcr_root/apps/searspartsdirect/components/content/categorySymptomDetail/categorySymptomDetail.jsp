<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail partsRequired="false" id ="${symptomRelation.id}" />

<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description}</p>

<h2>Checking these parts may help solve your problem:</h2>
<c:forEach var="jobCode" items="${modelSymptom.jobCodeModels}">
    <div class="row-fluid csd-border">
        <h3>${jobCode.title}</h3>

        <c:if test="${jobCode.partTypeModel != null &&  jobCode.partTypeModel.imagePath != null}">
            <div class="span3 csd-image">
                <spd:displayImage path="${jobCode.partTypeModel.imagePath}"/>
            </div>
        </c:if>

        <p>${jobCode.description}</p>

        <!-- Guides:- -->
        <c:if test="${not empty jobCode.guides}">
            <c:forEach var="guide" items="${jobCode.guides}">
                <spd:linkResolver value="${guide.url}"/>
                <span class="arrow-link">
                    <a href="${url}">${guide.title}</a>
                    <i class="icon-chevron-left blue"></i>
                </span>
            </c:forEach>
        </c:if>
    </div>
    <cq:include path="findThisPart" resourceType="searspartsdirect/components/content/findThisPart" />

	
</c:forEach>