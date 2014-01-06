<%@include file="/libs/foundation/global.jsp"%>



<div>
	<h2>Sears Purchase Look up</h2>
    <div>
        <%if(currentNode.hasNode("image/file/jcr:content")){%>
        <img src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" style="float:right;"/>
        <%}%>
    	<cq:text property="text"/>
        <a class="searchGraphicCTAButton new-btn new-btn-square" href="#">Find my models</a>
    </div>
</div>