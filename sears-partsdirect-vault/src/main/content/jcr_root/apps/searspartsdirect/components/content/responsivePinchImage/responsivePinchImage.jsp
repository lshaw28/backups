<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getImagePathTag/><%
%><c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set><%
%>

<c:choose>
    <c:when test="${empty diagramImage}">
        <spd:getImagePathTag/>
        <c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="desktopImage" value="${diagramImage}" />
        <c:set var="tabletImage" value="${diagramImage}" />
        <c:set var="mobileImage" value="${diagramImage}" />
        <c:set var="imageAlt" value="Sears Part Image" />
    </c:otherwise>
</c:choose>

<div data-toggle="pinch-image" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}">
</div>

<div class="imageSizePrint row-fluid">
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