<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getAuthorPages />
<c:set var="s" value="${fn:length(authors) gt 1? 's':''}" />

<c:forEach var="author" items="${authors}" varStatus="currentItem">
	<c:choose>
	    <c:when test="${currentItem.count lt fn:length(authors)}">
			${author.description}, <br/>
	    </c:when>
	    <c:otherwise>
			${author.description}
	    </c:otherwise>
	</c:choose>
</c:forEach>

<p>
	by
	<c:forEach var="author" items="${authors}">
		<spd:LinkResolver value="${author.path}" />
		<a href="${url}">${author.title}</a>, 
	</c:forEach>
	Sears Home Services repair technician${s}
</p>
