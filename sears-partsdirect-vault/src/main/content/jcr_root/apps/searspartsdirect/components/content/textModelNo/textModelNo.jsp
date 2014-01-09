<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%

	    String modelNo="";
		if(request.getParameter("searchModPar")!=null){
			modelNo = request.getParameter("searchModPar");
		}
		else{
 			modelNo = "model no. not found";
		}
%>
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1><%=properties.get("text","(0) results found for model # ")%><strong><%=modelNo%></strong><p class="pull-right">We also found <span><a>(1) part number</a></span></p></h1>
		</div>
	</div>
</div>
