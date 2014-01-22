
<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image,com.day.cq.wcm.api.WCMMode" %>
<%
if (!(currentNode.hasNode("image/file/jcr:content")||currentNode.hasProperty("text"))&& WCMMode.fromRequest(request) != WCMMode.DISABLED) {
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
} else {

		String text="";
        String link="#";
        String height="";
        String width="";

        if(currentNode.getNode("image").hasProperty("jcr:title")){
			text=currentNode.getNode("image").getProperty("jcr:title").getString();
        }
        if(currentNode.getNode("image").hasProperty("linkURL")){
			link=currentNode.getNode("image").getProperty("linkURL").getString();
            if (!(link.contains(".com")||link.contains(".html"))){
            	link=link+".html";
            }
        }
        if(currentNode.getNode("image").hasProperty("width")&&currentNode.getNode("image").hasProperty("width")){
            height=currentNode.getNode("image").getProperty("height").getString()+"px";
            width=currentNode.getNode("image").getProperty("width").getString()+"px";

        }


%>

<div class="row-fluid doubleCheckModelNumber">

    <div class="span2" style="margin-top:10px;">
        <%if(currentNode.hasNode("image/file/jcr:content")){%>
        <a href="<%=link%>"><img title="<%=text%>" src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" height="<%=height%>" width="<%=width%>" /></a>
        <%}%>
    </div>

    <div class="span10">
		<cq:text property="text"/>
   </div>
</div>

<%}%>
