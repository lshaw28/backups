<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getImagePathTag/>
<c:set var="displayWidth"><cq:text property="displayWidth" placeholder=""/></c:set>
<c:set var="displayHeight"><cq:text property="displayHeight" placeholder=""/></c:set>
<c:set var="linkAlt"><cq:text property="linkAlt" placeholder=""/></c:set>
<c:set var="linkURL"><cq:text property="linkURL" placeholder=""/></c:set>
<c:set var="linkTarget"><cq:text property="linkTarget" placeholder=""/></c:set>
<c:set var="imageCaption"><cq:text property="imageCaption" placeholder=""/></c:set>
<c:set var="photoCredit"><cq:text property="photoCredit" placeholder=""/></c:set>
<div data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-width="${displayWidth}" data-height="${displayHeight}" data-linkalt="${linkAlt}" data-linkurl="${linkURL}" data-linktarget="${linkTarget}"></div>

<c:if test="${imageCaption ne ''}">
	<p>${imageCaption}</p>
</c:if>