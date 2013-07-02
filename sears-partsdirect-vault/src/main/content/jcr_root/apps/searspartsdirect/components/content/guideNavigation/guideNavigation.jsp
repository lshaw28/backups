<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:GuideNavigation />

<c:choose>
	<c:when test="${empty sections}">
		<p>No sections found.</p>
	</c:when>
	<c:otherwise>
		<ul class="hidden-phone">
			<c:forEach items="${sections}" var="current" varStatus="item">
				<li><a class="scroll-to" href="#${current.anchorName}">${current.linkText}</a></li>
			</c:forEach>
		</ul>

		<select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-groupclass="visible-phone" data-navigate="true">
			<option value="" selected="selected">${jumpToString}</option>
			<c:forEach items="${sections}" var="current" varStatus="item">
				<option value="#${current.anchorName}">${current.linkText}</option>
			</c:forEach>
		</select>
	</c:otherwise>
</c:choose>