<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getImagePathTag/><%
%><c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set><%
%>

<img id="diagramImage" src="" />

<div class="pinchImage row-fluid">
    <div class="new-span-responsive">
        <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
    </div>
    <div class="new-span-responsive">
        <a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print</a>
    </div>
    <div class="new-span-responsive">
    <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> View Full Size</a>
    </div>
    <div class="new-span-responsive">
    <a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print Diagram</a>
    </div>
</div>