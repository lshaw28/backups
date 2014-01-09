<%@include file="/libs/foundation/global.jsp"%>



<div>
	<h2>Sears Purchase Look up</h2>
    <div>
        <%if(currentNode.hasNode("image/file/jcr:content")){%>

        <img src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" style="float:right;"/>

        <%}
		if(currentNode.hasProperty("text")){%>
    		<cq:text property="text"/>
        <%}
		else{%>
			<p class="purchaseLookUpCopy">Find model numbers for products you've purchased or had serviced by Sears</p>
									<p>It's easy! Start here:</p>
        <%}%>
        <a class="searchGraphicCTAButton new-btn new-btn-square" href="#">Find my models</a>
    </div>
</div>