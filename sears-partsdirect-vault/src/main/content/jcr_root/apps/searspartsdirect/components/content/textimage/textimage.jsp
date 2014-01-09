
<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image,com.day.cq.wcm.api.WCMMode" %>
<%
if (!(currentNode.hasNode("image/file/jcr:content")||currentNode.hasProperty("text"))&& WCMMode.fromRequest(request) != WCMMode.DISABLED) {
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
} else {
%>
<div class="row-fluid doubleCheckModelNumber">

    <div class="span2" style="margin-top:10px;">
        <%if(currentNode.hasNode("image/file/jcr:content")){%>
		<img src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>"/>
        <%}%>
    </div>

    <div class="span10">
		<cq:text property="text"/>
   </div>
</div>

<%}%>








