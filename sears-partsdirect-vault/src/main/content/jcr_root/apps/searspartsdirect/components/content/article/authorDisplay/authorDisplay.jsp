<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getAuthorPages />
<c:choose>
	<c:when test="${fn:length(authors) eq 0}">
		<p class="authorMessage" />
	</c:when>
	<c:otherwise>
		<c:set var="s" value="${fn:length(authors) gt 1? 's':''}" />
		<p>by
		<c:forEach var="author" items="${authors}">
				<spd:linkResolver value="${author.path}" />
				<a href="${url}">${author.title}</a>,
		</c:forEach>
			<cq:text property="authorPosition" placeholder="Sears Home Services repair technician${s}" />
		</p>
	</c:otherwise>
</c:choose>

