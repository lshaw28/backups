<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image,com.day.cq.wcm.api.WCMMode" %>
<%
if(currentNode.hasProperty("./componentType") && currentNode.getProperty("componentType").getString().equals("heroimage")) {
%>
<cq:include script="seoHeroComponent" />
<%
} else {
	 if (!(currentNode.hasNode("image/file/jcr:content")||currentNode.hasProperty("text"))&& WCMMode.fromRequest(request) != WCMMode.DISABLED) {
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
	} else {
		String title="";
		String link="#";
		String height="";
		String width="";
		String imageSrc="";

		if(currentNode.getNode("image").hasProperty("jcr:title")){
			title=currentNode.getNode("image").getProperty("jcr:title").getString();
		}
		if(currentNode.getNode("image").hasProperty("linkURL")){
			link=currentNode.getNode("image").getProperty("linkURL").getString();
			if (!(link.contains(".com")||link.contains(".html"))){
				link=link+".html";
			}
		}
		if(currentNode.getNode("image").hasProperty("height")&&currentNode.getNode("image").hasProperty("width")){
			height=currentNode.getNode("image").getProperty("height").getString()+"px";
			width=currentNode.getNode("image").getProperty("width").getString()+"px";
		}

		if(currentNode.hasProperty("type")){
			if(currentNode.getProperty("type").getString().equals("purchaseLookup")){
				%><%@include file="purchaseLookup.jsp"%><%
		   }
		   else if(currentNode.getProperty("type").getString().equals("checkModelNumber")){
			   %><%@include file="checkModelNumber.jsp"%><%
			}
		}
	}
}
%>