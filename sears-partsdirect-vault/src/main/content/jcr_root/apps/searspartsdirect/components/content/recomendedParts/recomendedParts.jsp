<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>
<div class="accordion">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle collapsed" data-toggle="collapse" data-target="#${uniqueId}">
                <span><i class="icon-chevron-up"></i><i class="icon-chevron-down"></i><h4>Recomended parts for this repair</h4></span>
            </a>
        </div>
        <div class="accordion-body collapse row-fluid" id="#${uniqueId}">
            <div class="accordion-inner">

               	<cq:include path="displayRecommendedParts" resourceType="searspartsdirect/components/content/displayRecommendedParts" />

            </div>
        </div>
    </div>
</div>





