<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getYouTubeID /> 

<iframe width="100%" height="100%" src="http://www.youtube.com/embed/${youTubeID}" frameborder="0" allowfullscreen></iframe>
<br> <cq:text property="caption"/>