<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.Doctype,
	org.apache.commons.lang3.StringEscapeUtils,
	com.spd.cq.searspartsdirect.common.helpers.ExternalLinks" %>
<%
	String favIcon = currentDesign.getPath() + "/favicon.ico";
	if (resourceResolver.getResource(favIcon) == null) {
		favIcon = null;
	}
	ExternalLinks externalLinks = new ExternalLinks(slingRequest);
%><head>
	<!-- Environment Variables -->
	<meta name="global-mainSitePath" content="${mainSitePath}" />
	<meta name="global-apiPath" content="http://partsapivip.qa.ch3.s.com/pd-services/v1/" />
	<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" />
	<!-- Metadata -->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta name="author" content="Sears PartsDirect" />
	<meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
	<meta description="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
	<meta name="robots" content="index, follow" />
	<link rel="canonical" href="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
	<title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
	<!-- Viewport and Styles -->
	<cq:include script="headlibs.jsp"/>
	<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>
	<!-- Head Scripts -->
	<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
	<cq:include script="stats.jsp"/>
	<cq:include script="/libs/wcm/mobile/components/simulator/simulator.jsp"/>
	<!-- Favorite Icons -->
	<% if (favIcon != null) { %>
	<% // @TODO: Implement all favourite icon types %>
	<link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<% } %>
</head>