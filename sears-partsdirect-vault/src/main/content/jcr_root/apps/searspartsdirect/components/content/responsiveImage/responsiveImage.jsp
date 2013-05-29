<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getImagePathTag/>
<c:set var="displayWidth"><cq:text property="displayWidth" placeholder=""/></c:set>
<c:set var="displayHeight"><cq:text property="displayHeight" placeholder=""/></c:set>
<div class="responsiveImage" data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-width="${displayWidth}" data-height="${displayHeight}"></div>