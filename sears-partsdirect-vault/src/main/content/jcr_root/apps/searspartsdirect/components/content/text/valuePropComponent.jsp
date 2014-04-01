<% /* For Story 8073 SEO: Landing page - Value prop component */ %>
<%@ include file="/apps/searspartsdirect/global.jsp" %>
<% 
	String node = currentNode.getName();
	String brandName = currentPage.getProperties().get("brandName","");
	String productType = currentPage.getProperties().get("productType","");
%>

<% if (node.equals("text_0")) { %>
	<div class="vpContanier">
		<h4><%=properties.get("title1", "Save on Shipping") %></h4>
		<%=properties.get("text", "Get <span class='red'>FREE Standard Shipping</span> on order of $100 or more. Discount will automatically be applied at checkout.") %>
	</div>
<% } else if (node.equals("text_1")) { %>
	<div class="vpContanier">
		<h4><%=properties.get("title1", "Guaranteed Fit") %></h4>
		<%=properties.get("text", "All parts are manufacturer-approved, to ensure proper fit and to keep appliances running for years to come.") %>
	</div>
<% } else { %>
	<div class="vpContanier">
		<h4><%=properties.get("title1", "Need Help") %></h4>
		<%=properties.get("text", "From free online <a href='javascript:void(0);'>manuals</a> to <a href='javascript:void(0);'>repair guides</a>, we've got what you need to fix your ") %><%= brandName %>&nbsp;<%= productType %>.
	</div>
<% } %>