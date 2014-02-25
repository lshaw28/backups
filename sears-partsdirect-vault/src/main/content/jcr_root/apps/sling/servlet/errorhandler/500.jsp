<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %>


<% String serverName = request.getServerName();
if (serverName.contains("searshomeservices") || serverName.contains("shsvip")) {
	response.sendRedirect("/services/errorpage.html"); 
} else { %>
	<%@include file="/libs/sling/servlet/errorhandler/default.jsp"%>
<% } %>