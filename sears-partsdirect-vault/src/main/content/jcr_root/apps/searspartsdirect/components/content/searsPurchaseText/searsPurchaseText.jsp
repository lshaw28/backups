<%@include file="/libs/foundation/global.jsp"%>
<%
      String text="";
        String link="#";
        String height="";
        String width="";

        if(currentNode.hasNode("image/file/jcr:content")){

    
            if(currentNode.getNode("image").hasProperty("jcr:title")){
                text=currentNode.getNode("image").getProperty("jcr:title").getString();
            }
            if(currentNode.getNode("image").hasProperty("linkURL")){
                link=currentNode.getNode("image").getProperty("linkURL").getString();
                if (!(link.contains(".com")||link.contains(".html"))){
                    link=link+".html";
                }
            }

            if(currentNode.getNode("image").hasProperty("height") && currentNode.getNode("image").hasProperty("width")){
                height=currentNode.getNode("image").getProperty("height").getString()+"px";
                width=currentNode.getNode("image").getProperty("width").getString()+"px";

            }

    }
%>


<div>
	<h2>Sears Purchase Look up</h2>
    <div>
        <%
		if(currentNode.hasProperty("text")){%>
    		<cq:text property="text"/>
        <%}
		else{%>
			<p class="purchaseLookUpCopy">Find model numbers for products you've purchased or had serviced by Sears</p>

        <%}%>
        <a class="searchGraphicCTAButton new-btn new-btn-square" href="http://www.searspartsdirect.com/partsdirect/validateProfilePromoPage.pd?page=learnMorePage">Find my models</a><br/><br/>
        <%if(currentNode.hasNode("image/file/jcr:content")){%>

        <a href="<%=link%>"><img title="<%=text%>" src="<%=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath()%>" height="<%=height%>" width="<%=width%>" /></a>

        <%}%>
    </div>
</div>