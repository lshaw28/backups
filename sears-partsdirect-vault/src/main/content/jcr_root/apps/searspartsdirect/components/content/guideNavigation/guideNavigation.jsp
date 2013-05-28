<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:guideNavigation />
<div class="sticky-wrapper">
	<nav id="sticky">
		<c:if test="${empty sections}">
			No sections found
		</c:if>
		<c:forEach items="${sections}" var="current" varStatus="item">
			<a class="scroll-to scroll-to-child-${item.index}" href="#${current[1]}">${current[0]}</a>
		</c:forEach>
	</nav>
</div>
