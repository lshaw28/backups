<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getAuthorPages />

<c:when test="${fn:length(authors) lt 1 }">
	<p class="authorMessage" />
<c:otherwise>
	<c:set var="s" value="${fn:length(authors) gt 1? 's':''}" />
	<p>
		by
		<c:forEach var="author" items="${authors}">
			<spd:linkResolver value="${author.path}" />
			<a href="${url}">${author.title}</a>,
		</c:forEach>
		<cq:text property="authorPosition" placeholder="Sears Home Services repair technician${s}"/>
	</p>
</c:otherwise>
