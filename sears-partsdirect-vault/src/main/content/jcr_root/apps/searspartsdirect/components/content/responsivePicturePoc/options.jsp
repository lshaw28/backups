<%@ page import="java.util.Iterator,java.util.ArrayList"%>

<%@include file="/apps/shc/searsstyle/global/global.jsp"%>

[{text:'None', value:''},

<%

    Node pageNode = slingRequest.getResourceResolver().resolve(request.getParameter("q")).adaptTo(Node.class);

    ArrayList<Node> nodePathList = new ArrayList<Node>();


    if(pageNode.hasNode("jcr:content/article/articlemaincontent")) {

       nodePathList.add(pageNode.getNode("jcr:content/article/articlemaincontent"));    
    }

    if(pageNode.hasNode("jcr:content/article/assetparsys")) {
     
       nodePathList.add(pageNode.getNode("jcr:content/article/assetparsys"));    
    }    
    
    if(pageNode.hasNode("jcr:content/assetparsys")) {
        nodePathList.add(pageNode.getNode("jcr:content/assetparsys"));    
     }

    pageContext.setAttribute("nodePathList", nodePathList);

    %>
<searsstyle-aem:linkListTag nodePathList="${nodePathList}" />

<%
out.print(pageContext.getAttribute("output"));
%>
]