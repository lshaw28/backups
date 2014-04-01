<div>
	<h2><cq:text property="heading"/></h2>
    <div>

        <cq:text property="text"/>


        <a class="searchGraphicCTAButton new-btn new-btn-square" href="http://www.searspartsdirect.com/partsdirect/validateProfilePromoPage.pd?page=learnMorePage">Find my models</a><br/><br/>

        <%if(currentNode.hasNode("image/file/jcr:content")){%>
        <a href="<%=link%>"><img title="<%=title%>" src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" height="<%=height%>" width="<%=width%>" /></a>
        <%}%>

    </div>
</div>