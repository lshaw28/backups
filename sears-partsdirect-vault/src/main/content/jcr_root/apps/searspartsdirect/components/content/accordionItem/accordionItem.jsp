<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>

<%
// @TODO move this to util tag (remove the import after you port it)

// generate unique id in page context
String path = resource.getPath();
path = path.substring(path.indexOf(JcrConstants.JCR_CONTENT) + JcrConstants.JCR_CONTENT.length() + 1);
pageContext.setAttribute("uniqId", path.replace("/", "_").replace(":", "_"));
%>

<div class="accordion" id="parent_${uniqId}">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#parent_${uniqId}" href="#${uniqId}">
				<i class="icon-minus"></i>
				<i class="icon-plus"></i>
				Header Item (have placeholder data)
			</a>
		</div>
		<div id="${uniqId}" class="accordion-body collapse">
			<div class="accordion-inner">
				RTE (have placeholder data)
			</div>
		</div>
	</div>
</div>