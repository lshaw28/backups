<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ page import="java.net.URLEncoder"%>

<%
	String address = properties.get("address1","") +" "+ properties.get("address2","")+" " +properties.get("city","")+" " + properties.get("state","")+" " + properties.get("zipcode","");
	address = URLEncoder.encode(address, "utf-8");
	String fullAddress = "https://www.google.com/maps/place/" + address;
%>
<div class="addressContainer">
    <h4 class="vcardHeader">Address</h4>
    <div class="vcard">
        <a href="<%=fullAddress%>" target="_blank">
            <span class="fn org"><%=properties.get("storename", "")%></span> <br />
            <span class="adr streetAddress"><%=properties.get("address1", "")%> <%=properties.get("address2", "")%></span> <br />
            <span class="locality"><%=properties.get("city", "")%> <%=properties.get("state", "")%> <%=properties.get("zipcode", "")%></span>
        </a>
    </div>

    <div class="phone">Phone: <a href="tel:<%=properties.get("phone", "")%>"><%=properties.get("phone", "")%></a></div>
        <%
        String fax = properties.get("fax", "");
        if(fax.length() != 0){
            %><div class="fax">Fax: <%=fax %></div>
    <%
        }
    %>
</div>