<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getAuthorPages />
<cq:text property="guideHeader" />
<c:set var="s" value="${fn:length(authorPages) gt 1? 's':''}" />

<p>
	by
	<c:forEach var="authorPage" items="${authorPages}">
		<spd:LinkResolver value="${authorPage.path}" />
		<a href="${url}">${authorPage.title}</a>, 
	</c:forEach>
	Sears Home Services repair technician${s}
</p>
