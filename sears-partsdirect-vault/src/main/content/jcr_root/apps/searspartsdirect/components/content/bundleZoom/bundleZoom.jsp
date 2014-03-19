<!-- Story 7964 -->

<%@ include file="/apps/searspartsdirect/global.jsp" %><%
    %><%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image,com.day.cq.wcm.api.WCMMode" %>

<%
    if (!(currentNode!=null&& WCMMode.fromRequest(request) != WCMMode.DISABLED) ){
		out.println("<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png' />");
}
    else
    {
    String imageURL1=currentNode.hasNode("imageA/file/jcr:content")?currentNode.getNode("imageA/file/jcr:content").getProperty("jcr:data").getPath():"";
    String imageURL2=currentNode.hasNode("imageB/file/jcr:content")?currentNode.getNode("imageB/file/jcr:content").getProperty("jcr:data").getPath():"";

    %>
<div id="bundleBlock">
                    <a name="MIDDLE">
                        <p id="bundleTitle"><%=properties.get("bundleCaption","")%></p>
                        <p><%=properties.get("savePrice","")%></p>
                        <div class="bundleParts">
                            <img id="bundleImage" src="" height="70" width="60">
                            <p>Water Filter</p>
                        </div>
                        <div class="bundlePlus"></div>
                        <div class="bundleParts">
                            <img title="Refridgerator Deodorizer" alt="Refridgerator Deodorizer" src="<%=imageURL1%>" height="70" width="60">
                            <p><%=properties.get("imageATitle","")%></p>
                        </div>
                        <div class="bundlePlus"></div>
                        <div class="bundleParts last">
                            <img title="Freezer Deodorizer" alt="Freezer Deodorizer" src="<%=imageURL2%>" height="70" width="60">
                            <p><%=properties.get("imageBTitle","")%></p>
                        </div>
                    </a><div class="bundleCopyAndLink"><a name="MIDDLE">

                    <p class="smallPrint normal">Special offer for 3 fridge must-haves</p>
                    </a><p class="smallPrint"><a name="MIDDLE">


                    </a><a href="#" class="bundleLink">Show Bundle</a>

                    </p>
                    <p class="shippingTitle">FREE SHIPPING<span>*</span></p>
                    <p class="smallPrint">with automatic reorder</p>
                    </div>
                </div>

<%}%>
