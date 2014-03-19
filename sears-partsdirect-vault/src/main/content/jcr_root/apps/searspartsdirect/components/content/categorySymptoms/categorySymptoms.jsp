<%@ include file="/apps/searspartsdirect/global.jsp" %>

<%-- get symptom list --%>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${productCategoryRelation != null}">
    <spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
</c:if>

<c:set var="showImage"><cq:text property="showImage"/></c:set>

<%-- if symptom list empty then the component wont appear --%>
<c:if test="${!empty symptomList}">

    <%-- header --%>
    <h3>
        <cq:text property="text1" placeholder=""/>
        <spd:tagsByPage tagType="subcategories"/>
        <c:if test="${fn:length(subcategoriesList) eq 1}"> ${subcategoriesList[0].title} </c:if>
        ${productCategoryRelation.title}
        <cq:text property="text2" placeholder=""/>
    </h3>
    <cq:text property="optionalDescription" placeholder=""/>

    <c:if test="${showImage == 'true'}">
        <div class="accessoryWithImage clearfix">
    </c:if>

    <%-- list --%>
    <c:forEach var="symptom" items="${symptomList}" varStatus="currentItem">
        <c:choose>
            <c:when test="${currentItem.count eq fn:length(symptomList) or currentItem.count + 1 eq fn:length(symptomList)}">
                <c:set var="symptomRowClass" value=" last" />
            </c:when>
            <c:otherwise>
                <c:set var="symptomRowClass" value="" />
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${currentItem.count % 2 eq 1}">
                <div class="row-fluid${symptomRowClass}">
            </c:when>
        </c:choose>
        <div class="span6">
            <c:set var="symptomUrl" value="/content/searspartsdirect/en/categories/${productCategoryRelation.trueName}-repair/symptom/${symptom.id}.html" />
            <a href="${symptomUrl}"><c:out value="${symptom.title} "/></a>
        </div>
        <c:choose>
            <c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
                </div> <%-- //end div.row-fluid${symptomRowClass} --%>
            </c:when>
        </c:choose>
    </c:forEach>

    <%-- image --%>
    <c:if test="${showImage == 'true'}">
        </div> <%-- //end div.accessoryWithImage --%>
        <div class="imageHolder span4 clearfix">
            <cq:include path="responsiveImage" resourceType="searspartsdirect/components/content/responsiveImage" />
        </div>

        <script>
            function setSymptomImageAlignment() {
                $(".categorySymptoms .imageHolder").css({"height" : $(".categorySymptoms .accessoryWithImage").height()+"px"});
                var fullOuterHeight = $(".imageHolder").height();
                var imageHeight = $(".imageHolder .responsiveImage").height();
                var marginVert = (fullOuterHeight - imageHeight)/2;
                var fullOuterWidth = $(".imageHolder").width();
                var imageWidth = $(".imageHolder .responsiveImage").width();
                var marginHoriz = (fullOuterWidth - imageWidth)/2;
                $(".imageHolder .responsiveImage").css({"margin-top" : marginVert, "margin-right" : marginHoriz, "margin-bottom" : marginVert, "margin-left" : marginHoriz});
            }

            $(document).ready(function(){
                if ($(".categorySymptoms .accessoryWithImage").length === 1){
                    setTimeout(setSymptomImageAlignment,1000);
                }
            });
        </script>
    </c:if>
</c:if>