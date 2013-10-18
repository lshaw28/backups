<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getImagePathTag/><%
%><c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set><%
%><div data-toggle="pinch-image" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}"></div>
<div class="row-fluid">
	<div class="new-span-responsive">
		<a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
	</div>
	<div class="new-span-responsive">
		<a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print</a>
	</div>
</div>