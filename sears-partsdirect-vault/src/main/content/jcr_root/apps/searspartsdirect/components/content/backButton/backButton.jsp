<%@ include file="/apps/searspartsdirect/global.jsp" %>

		<c:set var="linkName">
			<cq:text property="linkName" placeholder="*replace with text to redirect to parent page*" />
		</c:set>
		
		<a href="<spd:BackButton />.html">${linkName}</a> 
