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

<div id="diagramPinchImage" class="diagram-list-print" data-toggle="pinch-image" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}">
</div>

<div class="row-fluid">
    <div class="new-span-responsive">
        <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
    </div>
    <div class="new-span-responsive">
        <ul>
            <li><a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print</a>
                <ul>
                    <li><a href="#" id="diagram-print">Diagram</a></li>
                    <li><a href="#" id="list-print">Part List</a></li>
                    <li><a href="#" id="diagram-list-print">Diagram and Part List</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>