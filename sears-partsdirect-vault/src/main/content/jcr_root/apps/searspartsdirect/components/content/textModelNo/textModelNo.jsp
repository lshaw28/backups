<%@include file="/apps/searspartsdirect/global.jsp" %>
<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
            <h1><span class="textToDisplay"></span><span class="displaySearchType"></span> #<strong class="displaySearchModPar"></strong><p id="weAlsoFound" class="pull-right">We also found <span><a class="alsoFound" href=""></a></span></p></h1>
		</div>
	</div>
</div>
<script>
    $(document).ready(function(){
    	checkModelPartCount('<%=request.getParameter("searchModPar")%>','<%=request.getParameter("pathTaken")%>','<%=properties.get("text","(0) results found for ")%>');
    });   
</script>