<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="teamMemberContainer">
<%@ page import="com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image" %>
<%
    Image image = new Image(resource, "image");
    if (image.hasContent() || WCMMode.fromRequest(request) == WCMMode.EDIT) {
        image.loadStyleData(currentStyle);
        // add design information if not default (i.e. for reference paras)
        if (!currentDesign.equals(resourceDesign)) {
            image.setSuffix(currentDesign.getId());
        }
        //drop target css class = dd prefix + name of the drop target in the edit config
        image.addCssClass(DropTarget.CSS_CLASS_PREFIX + "image");
        image.setSelector(".img");
        image.setDoctype(Doctype.fromRequest(request));
        String divId = "cq-textimage-jsp-" + resource.getPath();
        // div around image for additional formatting
        %><div class="memberImage pull-left" id="<%= divId %>"><%
        %><% image.draw(out); %><br><%
        %></div>
        <%
    }
    %>
        <h5 class="memberName"><cq:text property="memberName" tagClass="text"/></h5>
        <p class="memberTitle"><cq:text property="memberTitle" tagClass="text"/></p>
    	<ul class="memberInfo">
        	<%
        	String memberDesc = properties.get("memberDesc", "");
        	if(memberDesc.length() != 0){
        		%>
        		<li class="memberDesc">"<span class="quoteStyling"><%=memberDesc %></span>"</li>
        		<%
        	}
        	%>
        	<li class="memberEmail"><cq:text property="memberEmail" tagClass="text"/></li>
        	<li class="memberPhone"><a href="tel:<%=properties.get("memberPhone", "")%>"><%=properties.get("memberPhone", "")%></a></li>
    	</ul>
</div>