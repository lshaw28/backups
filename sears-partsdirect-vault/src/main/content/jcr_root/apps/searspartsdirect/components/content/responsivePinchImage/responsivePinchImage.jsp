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
        <c:set var="desktopPinchImage" value="${diagramImage}" />
        <c:set var="tabletPinchImage" value="${diagramImage}?wid=930" />
        <c:set var="mobilePinchImage" value="${diagramImage}" />
        <c:set var="imageAlt" value="Sears Part Image" />
    </c:otherwise>
</c:choose>

<div id="diagramPinchImage" class="diagram-list-print" data-toggle="pinch-image" data-desktoppinchimage="${desktopPinchImage}" data-tabletpinchimage="${tabletPinchImage}" data-mobilepinchimage="${mobilePinchImage}" data-alttext="${imageAlt}">
</div>

<div class="row-fluid">
    <div class="new-span-responsive pinch-options">
        <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
    </div>
    <div class="new-span-responsive pinch-options">
        <ul>
            <li><a target="_blank"><i class="icon-print"></i> Print</a>
                <ul>
                    <li><a data-toggle="pinch-print-diagram" href="#">Diagram</a></li>
                    <li><a data-toggle="pinch-print-list" href="#" id="list-print">Part List</a></li>
                    <li><a data-toggle="pinch-print-diagram-list" href="#" id="diagram-list-print">Diagram and Part List</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>