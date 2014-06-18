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
        <c:set var="desktopImage" value="${diagramImage}?wid=653" />
        <c:set var="tabletImage" value="${diagramImage}?wid=653" />
        <c:set var="mobileImage" value="${diagramImage}?wid=653" />
        <c:set var="imageAlt" value="Sears Part Image" />
    </c:otherwise>
</c:choose>

<div data-toggle="pinch-image" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}">
</div>

<div class="row-fluid">
    <div class="new-span-responsive">
        <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
    </div>
    <div class="new-span-responsive">
        <ul>
            <li><a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print</a>
                <ul>
                    <li><a href="#">Diagram</a></li>
                    <li><a href="#">Part List</a></li>
                    <li><a href="#">Diagram and Part List</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>