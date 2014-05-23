<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="rawText"><cq:text property="text"/></c:set>

<% if(currentNode != null && currentNode.hasProperty("componentType")) {
	
    	String currentComponentType = currentNode.getProperty("componentType").getString();
		boolean hasSeoTextComponentEnabled = currentComponentType.equals("seoTextComponent");
		boolean hasValuePropEnabled = currentComponentType.equals("valueprop");

		if(hasValuePropEnabled) { %> 
			<%-- [Mingle #8073] - SEO: Landing page - Value prop component --%>
			<cq:include script="valuePropComponent" />        
        <% } else if(hasSeoTextComponentEnabled) { %>
			<%-- [Mingle #8077] - SEO: Landing page - Text component --%>
		 	<h2><%=properties.get("title1", "") %></h2>
		 	<spd:cleanText text="${rawText}" /> 			
	<% } else { %> <spd:cleanText text="${rawText}" /> <% } %>

<% } else { %>
	<%-- Default Text component --%>
	<spd:cleanText text="${rawText}" /> 
<% } %>