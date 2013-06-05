<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getYouTubeID />
<c:set var="caption"><cq:text property="caption" placeholder="" /></c:set>
<c:set var="displayWidth"><cq:text property="displayWidth" placeholder=""/></c:set>
<c:set var="displayHeight"><cq:text property="displayHeight" placeholder=""/></c:set>

<div data-youtubeid="${youTubeID}" data-width="${displayWidth}" data-height="${displayHeight}"></div>
<c:if test="${caption ne '' and caption ne Constants.EMPTY and caption ne null}">
	<p>${caption}</p>
</c:if>