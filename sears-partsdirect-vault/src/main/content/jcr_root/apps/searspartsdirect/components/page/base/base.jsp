<%@page session="false"
	contentType="text/html; charset=utf-8"
	import="com.day.cq.commons.Doctype,
		com.day.cq.wcm.api.WCMMode,
		com.day.cq.wcm.foundation.ELEvaluator" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects/><%
	// read the redirect target from the 'page properties' and perform the
	// redirect if WCM is disabled.
	String location = properties.get("redirectTarget", "");
	// resolve variables in path
	location = ELEvaluator.evaluate(location, slingRequest, pageContext);
	if (WCMMode.fromRequest(request) != WCMMode.EDIT && location.length() > 0) {
		// check for recursion
		if (!location.equals(currentPage.getPath())) {
			final String redirectTo = slingRequest.getResourceResolver().map(request, location) + ".html";
			response.sendRedirect(redirectTo);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return;
	}
	// set doctype
	if (currentDesign != null) {
		currentDesign.getDoctype(currentStyle).toRequest(request);
	}

%><%= Doctype.fromRequest(request).getDeclaration() %>
<html lang="en"><cq:include script="head.jsp"/>
<cq:include script="body.jsp"/>
</html>