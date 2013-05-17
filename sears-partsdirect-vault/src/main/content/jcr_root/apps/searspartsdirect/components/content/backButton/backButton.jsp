<%@ include file="/apps/searspartsdirect/global.jsp" %>

	<cq:text property="linkName" />
	
		<c:set var="linkName"><cq:text property="linkName" placeholder="" /></c:set>



		<a href="<spd:BackButton />">${linkName}</a> 
