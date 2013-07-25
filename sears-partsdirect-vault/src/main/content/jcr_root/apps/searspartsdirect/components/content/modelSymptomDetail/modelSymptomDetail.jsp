<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id="${symptomRelation.id}" />

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getUrlRelation relationType="model" />

<c:set var="modelRepairUrl" value="/content/searspartsdirect/en/${brandRelation.trueName}/${productCategoryRelation.trueName}/model-${modelRelation}-repair.html"/>
<a href="${modelRepairUrl}">Return to Repair help for model #<c:out value="${modelRelation}" /></a>

<c:set var="jobCodes" value="${symptom.jobCodeModels}" scope="request" />
<c:set var="modelNumber" value="${modelRelation}" scope="request" />
<cq:include path="jobCodePartsFinder" resourceType="searspartsdirect/components/content/jobCodePartsFinder" />

<h1><c:out value="${symptom.title}" /></h1>
<p><c:out value="${symptom.description}" /> </p>

<c:forEach var="jobCode" items="${symptom.jobCodeModels}">
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

                <!--  Guides:- -->
                <c:if test="${not empty jobCode.guides}">
                    <c:forEach var="guide" items="${jobCode.guides}">
                        <spd:linkResolver value="${guide.url}"/>
                        <p><a href="${url}"><c:out value="${guide.title}" /></a></p>
                    </c:forEach>
                </c:if>


                <spd:getPartsLinkTag brandName="${brandRelation.title}" categoryName="${productCategoryRelation.title}" modelNumber="${modelRelation}"/>
                <c:if test="${empty recommendedParts && not empty findPartUrl && not empty jobCode.partTypeModel}">
                    <p class="shopParts">
                        <a href="${findPartUrl}">Shop ${jobCode.partTypeModel.title} in this model</a>
                    </p>
                </c:if>


            </div>
        </div>

    </div>
    <c:set var="recommendedParts" value="${jobCodeParts[jobCode.id]}" scope="request" />
    <c:choose>
        <c:when test="${not empty recommendedParts}">
            <cq:include path="recommendedParts" resourceType="searspartsdirect/components/content/recommendedParts" />
        </c:when>
        <c:otherwise>
            <!--  no parts found then show the following block -->

        </c:otherwise>
    </c:choose>

</c:forEach>

