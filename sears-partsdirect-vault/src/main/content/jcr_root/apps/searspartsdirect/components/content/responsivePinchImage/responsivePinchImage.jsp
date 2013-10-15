<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getImagePathTag/><%
%><c:set var="imageAlt"><cq:text property="imageAlt" placeholder=""/></c:set><%
%><div data-toggle="pinch-image" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-alttext="${imageAlt}"></div>