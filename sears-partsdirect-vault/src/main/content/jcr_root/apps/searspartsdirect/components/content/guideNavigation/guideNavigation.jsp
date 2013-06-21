<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:GuideNavigation />

             
		<c:choose>
			<c:when test="${empty sections}">
				No sections found
			</c:when>
			<c:otherwise>
				<ul class="hidden-phone">
					<c:forEach items="${sections}" var="current" varStatus="item">
						<li><a class="scroll-to" href="#${current[1]}">${current[0]}</a></li>
					</c:forEach>
				</ul>
                                <!-- TODO: Make sure global drop-down mobile styling is applied -->
				<select class="visible-phone">
					<option value="" selected="selected">${jumpToString}</option>
					<c:forEach items="${sections}" var="current" varStatus="item">
						<option value="#${current[1]}">${current[0]}</option>
					</c:forEach>
				</select>
			</c:otherwise>
                </c:choose>
