<div class="row-fluid doubleCheckModelNumber">

    <cq:text property="heading"/>

    <div class="span2" style="margin-top:10px;">
        <%if(currentNode.hasNode("image/file/jcr:content")){%>
        <a href="<%=link%>"><img title="<%=title%>" src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" height="<%=height%>" width="<%=width%>" /></a>
        <%}%>
    </div>

    <div class="span10">
		<cq:text property="text"/>
   </div>
</div>