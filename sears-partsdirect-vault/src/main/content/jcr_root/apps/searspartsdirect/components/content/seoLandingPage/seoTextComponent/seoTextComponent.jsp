<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.wcm.api.WCMMode" %><%
%><%

if (!currentNode.hasProperty("title") && WCMMode.fromRequest(request) != WCMMode.DISABLED) {
	  out.println("<h4>SEO Text component </h4><p>Content title goes here</p><p>Content text goes here</p>");
	} else {
	%>
	<div>
		<h2><%=properties.get("title", "") %></h2>
		<div class="desc">
	        <%=properties.get("desc", "") %>
	    </div>
	</div>	
	<%		
	}
%>


