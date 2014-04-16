<%--Mingle #8075, #8134--%>
<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@page import="com.spd.cq.searspartsdirect.common.helpers.Constants" %>

<%--Mingle #8134--%>
<spd:getUrlRelation relationType="symptom" />

<%-- If symptomRelation.id is null implies that this is not a symptom page --%>
<c:set var="showImage" value="" />
<c:set var="productType" value="" />
<c:if test="${symptomRelation.id == null}">
    <c:set var="showImage"><cq:text property="showImage"/></c:set>
    <c:set var="productType"><%=currentPage.getProperties().get("productType", "") %></c:set>
</c:if>

<%-- If product type is provided, use that --%>

<%-- set productCategoryFilter --%>
<c:set var="assetType" value="productCategory" />
<c:choose>
    <c:when test='${productType != ""}'>
        <c:set var="productCategoryFilter"><%=Constants.ASSETS_PATH %>/${assetType}/${productType}</c:set>
    </c:when>
    <c:otherwise>
        <spd:getRelation single="true" assetType="${assetType}" />
        <c:if test="${productCategoryRelation != null}">
            <c:set var="productCategoryFilter">${productCategoryRelation.path}</c:set>
        </c:if>
    </c:otherwise>
</c:choose>

<%-- get symptom list --%>
<c:if test="${productCategoryFilter != null}">
    <spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryFilter}" />
</c:if>

<%-- if symptom list empty then the component wont appear --%>
<c:if test="${!empty symptomList}">

    <%-- header --%>
    <c:choose>
        <c:when test="${symptomRelation.id == null}">
            <%--Mingle #8075--%>
            <h3>
                <cq:text property="text1" placeholder=""/>
                <c:choose>
                    <c:when test='${productType != ""}'>
                        ${productType}
                    </c:when>
                    <c:otherwise>
                       <spd:tagsByPage tagType="subcategories"/>
                       <c:if test="${fn:length(subcategoriesList) eq 1}"> ${subcategoriesList[0].title} </c:if>
                       ${productCategoryRelation.title}
                    </c:otherwise>
                </c:choose>
                <cq:text property="text2" placeholder=""/>
            </h3>
            <cq:text property="optionalDescription" placeholder=""/>
        </c:when>
        <c:otherwise>
            <%--Mingle #8134--%>
            <h4 class="linkageHeader">
                <i>Not the symptom or solution you were looking for? Check out these other solutions</i>
            </h4>
        </c:otherwise>
    </c:choose>

    <c:if test="${showImage == 'true'}">
        <div class="accessoryWithImage clearfix">
    </c:if>

    <%-- list --%>
    <c:set var="count" value="0" />
    <c:set var="length" value="${fn:length(symptomList)}" />
    <c:forEach var="symptom" items="${symptomList}" varStatus="currentItem">

        <c:choose>
            <c:when test="${symptomRelation.id != symptom.id}">
                <c:set var="count" value="${count+1}" />
                <c:choose>
                    <c:when test="${count eq length or count + 1 eq length}">
                        <c:set var="symptomRowClass" value=" last" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="symptomRowClass" value="" />
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${count % 2 eq 1}">
                        <div class="row-fluid${symptomRowClass}">
                    </c:when>
                </c:choose>
                <div class="span6">
                    <c:choose>
                        <c:when test='${productType != ""}'>
                            <c:set var="symptomUrl" value="/content/searspartsdirect/en/categories/${productType}-repair/symptom/${symptom.id}.html" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="symptomUrl" value="/content/searspartsdirect/en/categories/${productCategoryRelation.trueName}-repair/symptom/${symptom.id}.html" />
                        </c:otherwise>
                    </c:choose>
                    <a href="${symptomUrl}"><c:out value="${symptom.title} "/></a>
                </div>
                <c:if test="${count % 2 eq 0 or count eq length}">
                    </div> <%-- //end div.row-fluid${symptomRowClass} --%>
                </c:if>
            </c:when>
            <c:otherwise>
                <c:set var="length" value="${length-1}" />
            </c:otherwise>
        </c:choose>

    </c:forEach>

    <%-- image --%>
    <c:if test="${showImage == 'true'}">
        </div> <%-- //end div.accessoryWithImage --%>
        <div class="imageHolder span4 clearfix">
            <%-- <cq:include path="responsiveImage" resourceType="searspartsdirect/components/content/responsiveImage" /> --%>
            <cq:include script="/apps/searspartsdirect/components/content/responsiveImage/responsiveImage.jsp" />
        </div>

       <script>
            function setSymptomImageAlignment() {
                $(".categorySymptoms .imageHolder").css({"height" : $(".categorySymptoms .accessoryWithImage:first").height()+"px"});
                var fullOuterHeight = $(".imageHolder:first").height();
                var imageHeight = $(".imageHolder div:first").height();
                var marginVert = (fullOuterHeight - imageHeight)/2;
                //if ($(".imageHolder .imageCaption").length === 1){
                    marginVert = marginVert - 20;
                //}
                $(".imageHolder div").css({"margin-top" : marginVert});
            }

            (function($,sr){

              // debouncing function from John Hann
              // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
              var debounce = function (func, threshold, execAsap) {
                  var timeout;

                  return function debounced () {
                      var obj = this, args = arguments;
                      function delayed () {
                          if (!execAsap)
                              func.apply(obj, args);
                          timeout = null;
                      };

                      if (timeout)
                          clearTimeout(timeout);
                      else if (execAsap)
                          func.apply(obj, args);

                      timeout = setTimeout(delayed, threshold || 100);
                  };
              }
              // smartresize
              jQuery.fn[sr] = function(fn){  return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); };

            })(jQuery,'smartresize');

            $(document).ready(function(){
                if($('[data-desktopimage] a img', $('.categorySymptoms .imageHolder:first')).length === 0) {
                    var target = $('[data-desktopimage]', $('.categorySymptoms .imageHolder:first')),
                    newResponsiveImage = new responsiveImage(target);

                    //if ($(".categorySymptoms .accessoryWithImage").length === 1){
                        setTimeout(setSymptomImageAlignment, 1000);
                    //}
                    $(window).smartresize(function(){
                        setSymptomImageAlignment();
                    });
                }
            });
        </script>
    </c:if>
</c:if>