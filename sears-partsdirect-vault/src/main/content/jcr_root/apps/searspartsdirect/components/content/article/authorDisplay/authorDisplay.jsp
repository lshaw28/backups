<%@ include file="/apps/searspartsdirect/global.jsp" %>

<cq:text property="guideHeader" />
<spd:getRelatedPages assetPath="pages" rootPath="/content/searspartsdirect/en/authors" />
<p>
	by <a href="${relatedPages[0].path}.html">${relatedPages[0].title}</a>, Sears Home Services repair technician
</p>