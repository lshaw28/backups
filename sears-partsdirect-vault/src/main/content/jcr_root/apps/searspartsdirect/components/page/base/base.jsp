<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Default page component.

  Is used as base for all "page" components. It basically includes the "head"
  and the "body" scripts.

  ==============================================================================

--%><%@page session="false"
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
<!--[if lt IE 7]> <html class="lt-ie10 lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie10 lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie10 lt-ie9" lang="en"> <![endif]-->
<!--[if IE 9]>    <html class="lt-ie10" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html lang="en"> <!--<![endif]-->
<cq:include script="head.jsp"/>
<cq:include script="body.jsp"/>
</html>