<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getImagePathTag/>
<c:set var="displayWidth"><cq:text property="displayWidth" placeholder=""/></c:set>
<c:set var="displayHeight"><cq:text property="displayHeight" placeholder=""/></c:set>
<c:set var="linkAlt"><cq:text property="linkAlt" placeholder=""/></c:set>
<c:set var="linkURL"><cq:text property="linkURL" placeholder=""/></c:set>

<c:if test="${fn:contains(linkURL, '/content/searspartsdirect/en')}">
   <c:set var="linkURL" value="${linkURL}.html" />
</c:if>

<c:set var="captionClick"><cq:text property="captionClickable" placeholder=""/></c:set>
<c:set var="linkTarget"><cq:text property="linkTarget" placeholder=""/></c:set>
<c:set var="imageCaption"><cq:text property="imageCaption" placeholder=""/></c:set>
<c:set var="photoCredit"><cq:text property="photoCredit" placeholder=""/></c:set>
<div data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-width="${displayWidth}" data-height="${displayHeight}" data-linkalt="${linkAlt}" data-linkurl="${linkURL}" data-linktarget="${linkTarget}">
	<c:if test="${photoCredit ne ''}">
		<p class="credit"><c:out value="${photoCredit} "/></p>
	</c:if>
</div>

<c:if test="${imageCaption ne ''}">
	<p class="imageCaption">
		<c:if test="${captionClick}">
			<a href="${linkURL}">
		</c:if>
		<c:out value="${imageCaption} "/>
		<c:if test="${captionClick}">
			</a>
		</c:if>
    </p>
</c:if>