<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getImagePathTag/><%
%><c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set><%
%><div class="diagramImage" data-toggle="pinch-image" data-desktopimage="" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}">
    <img src="http://i1304.photobucket.com/albums/s538/Laurence_Shaw/PartListDiagram_zpsa126eb06.png"/>
    <img src="http://i1304.photobucket.com/albums/s538/Laurence_Shaw/Diagram_zps3cfc9396.png"/>
</div>
<div class="pinchImage" class="row-fluid">
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