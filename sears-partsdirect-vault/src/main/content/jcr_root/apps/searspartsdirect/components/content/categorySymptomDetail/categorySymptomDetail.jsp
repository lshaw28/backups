<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id ="${symptomRelation.id}" />

<h2>Checking these parts may help solve your problem:</h2>
<c:forEach var="jobCode" items="${modelSymptom.jobCodeModels}">

    <div class="csd-border">
        <h3>${jobCode.title}</h3>
        <div class="row-fluid">
            <c:choose>
                <c:when test="${jobCode.partTypeModel != null && jobCode.partTypeModel.imagePath != null}">
                    <div class="span3 csd-image">
                        <spd:displayImage path="${jobCode.partTypeModel.imagePath}" altText="${jobCode.partTypeModel.title}"/>
                    </div>
                    <c:set var="textClass" value="span9" />
                </c:when>
                <c:otherwise>
                    <c:set var="textClass" value="span12" />
                </c:otherwise>
            </c:choose>

            <div class="csd-content ${textClass}">
                <p>${jobCode.description}</p>

                <!-- Guides:- -->
                <c:if test="${not empty jobCode.guides}">
                    <c:forEach var="guide" items="${jobCode.guides}">
                        <spd:linkResolver value="${guide.url}"/>
                            <a href="${url}">${guide.title}</a>
                            <br />
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <cq:include path="findThisPart" resourceType="searspartsdirect/components/content/findThisPart" />

	
</c:forEach>