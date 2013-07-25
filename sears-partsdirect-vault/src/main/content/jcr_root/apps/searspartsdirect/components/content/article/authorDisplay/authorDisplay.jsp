<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getAuthorPages />
<c:set var="hideTitle">
	<cq:text property="hideDefaultTitle" />
</c:set>
<c:choose>
	<c:when test="${fn:length(authors) eq 0}">
		<p class="authorMessage">&nbsp;</p>
	</c:when>

	<c:otherwise>
		<p>by
			<c:forEach var="author" items="${authors}" varStatus="currentItem">
				<spd:linkResolver value="${author.path}" />
				<c:choose>
					<c:when test="${(fn:length(authors) - currentItem.count) gt 1}">
						<a href="${url}"><c:out value="${author.title}" /></a>,
					</c:when>
					<c:when test="${(fn:length(authors) - currentItem.count) eq 1}">
						<a href="${url}"><c:out value="${author.title}" /></a> and
					</c:when>
					<c:when test="${hideTitle}">
						<a href="${url}"><c:out value="${author.title}" /></a>
					</c:when>
					<c:otherwise>
						<a href="${url}"><c:out value="${author.title}"/></a>,
						<c:set var="s" value="${fn:length(authors) gt 1? 's':''}" />
						<cq:text property="authorPosition"
							placeholder="Sears Home Services repair technician${s}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</p>
	</c:otherwise>
</c:choose>
