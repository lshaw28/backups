
<%@include file="/libs/foundation/global.jsp"%>

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










