<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getYouTubeID />
<c:set var="caption"><cq:text property="caption" placeholder="" /></c:set>

<div data-youtubeid="${youTubeID}"></div>
<c:if test="${caption ne '' and caption ne Constants.EMPTY and caption ne null}">
	<p>${caption}</p>
</c:if>