<%--
  Copyright 1997-2010 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Default head script.

  Draws the HTML head with some default content:
  - includes the WCML init script
  - includes the head libs script
  - includes the favicons
  - sets the HTML title
  - sets some meta data

  ==============================================================================

--%><%@include file="/libs/foundation/global.jsp" %><%
%><%@ page import="com.day.cq.commons.Doctype,
				   org.apache.commons.lang3.StringEscapeUtils" %><%
	String favIcon = currentDesign.getPath() + "/favicon.ico";
	if (resourceResolver.getResource(favIcon) == null) {
		favIcon = null;
	}
%><head>
	<!-- Metadata -->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta name="author" content="Sears PartsDirect" />
	<meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
	<meta description="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
	<meta name="robots" content="index, follow" />
	<title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
	<!-- Viewport and Styles -->
	<cq:include script="headlibs.jsp"/>
	<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>
	<!-- Head Scripts -->
	<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
	<cq:include script="stats.jsp"/>
	<!-- Favorite Icons -->
	<% if (favIcon != null) { %>
	<% // @TODO: Implement all favourite icon types %>
	<link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<% } %>
</head>