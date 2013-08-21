<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="accordion">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle collapsed" data-toggle="collapse" data-target="#${uniqueId}">
                <span><i class="icon-chevron-up"></i><i class="icon-chevron-down"></i><h4>Find This Part</h4></span>
            </a>
        </div>
        <div class="accordion-body collapse in" id="#${uniqueId}">
            <div class="accordion-inner">
                <cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />
            </div>
        </div>
    </div>
</div>