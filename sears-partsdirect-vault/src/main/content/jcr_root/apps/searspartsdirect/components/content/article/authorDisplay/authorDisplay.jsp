<%@ include file="/apps/searspartsdirect/global.jsp" %>

<cq:text property="guideHeader" />
<spd:getRelatedPages assetPath="pages" rootPath="/content/searspartsdirect/en/authors" />
<spd:LinkResolver value="${relatedPages[0].path}" />
<p>
	by <a href="${url}">${relatedPages[0].title}</a>, Sears Home Services repair technician
</p>