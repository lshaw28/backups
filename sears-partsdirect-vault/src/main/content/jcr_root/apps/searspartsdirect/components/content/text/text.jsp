<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.wcm.api.WCMMode" %>
<c:set var="rawText"><cq:text property="text"/></c:set>

<% if(currentNode != null && currentNode.hasProperty("componentType")) { 
	if(!currentNode.hasProperty("componentType") && WCMMode.fromRequest(request) != WCMMode.DISABLED) {
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
	} else {
		boolean hasSeoTextComponentEnabled = currentNode.hasProperty("componentType") && currentNode.getProperty("componentType").getString().equals("seoTextComponent");
		boolean hasValuePropEnabled = currentNode.hasProperty("componentType") && currentNode.getProperty("componentType").getString().equals("valueprop");
		if(hasValuePropEnabled) { %> 
			<%-- [Mingle #8073] - SEO: Landing page - Value prop component --%>
			<cq:include script="valuePropComponent" />        
        <% } else if(hasSeoTextComponentEnabled) { %>
			<%-- [Mingle #8077] - SEO: Landing page - Text component --%>
		 	<h2><%=properties.get("title1", "") %></h2>
		 	<spd:cleanText text="${rawText}" /> 			
	<% } else { %> <spd:cleanText text="${rawText}" /> <% } %>

<% } } else { %>
	<%-- Default Text component --%>
	<spd:cleanText text="${rawText}" /> 
<% } %>