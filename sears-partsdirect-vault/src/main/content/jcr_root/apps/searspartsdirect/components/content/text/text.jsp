<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.wcm.api.WCMMode" %>
<c:set var="rawText"><cq:text property="text"/></c:set>

<% if(currentNode != null) { 
	if(!currentNode.hasProperty("componentType") && WCMMode.fromRequest(request) != WCMMode.DISABLED) {
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
	} else {
		boolean hasSeoTextComponentEnabled = currentNode.hasProperty("componentType") && currentNode.getProperty("componentType").getString().equals("seoTextComponent");
		if(hasSeoTextComponentEnabled) {		
%>
	<%-- [Mingle #8077] - SEO: Landing page - Text component --%>
 	<h2><%=properties.get("title1", "") %></h2>
 	<spd:cleanText text="${rawText}" />
 			
	<% } else {  %> <spd:cleanText text="${rawText}" /> <% } %>

<% } } else {%>
	<%-- Default Text component --%>
	<spd:cleanText text="${rawText}" /> 
<% } %>