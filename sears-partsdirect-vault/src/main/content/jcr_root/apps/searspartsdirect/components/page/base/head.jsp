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
	<%-- Environment Variables --%>
	<spd:getPDUrl />
	<spd:getLocalUrl />
	<meta name="global-mainSitePath" content="${nonSecurePDUrl}" />
	<meta name="global-mainSitePathSecure" content="${securePDUrl}" />
	<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" />
	<spd:getHeaderHelperData />
	<meta name="global-apiPath" content="${PdApiRoot}" />
	<meta name="global-apiPathSecure" content="${PdApiRootSecure}" />
	<meta name="global-guestCookieId" content="${myProfileModelCookie}" />
	<meta name="global-registeredUserId" content="${userId}" />
	<meta name="global-cartId" content="${shoppingCartCookieId}" />
	<%-- Metadata --%>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta name="author" content="Sears PartsDirect" />
	<meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
	<meta name="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
	<meta name="robots" content="index, follow" />
	<link rel="canonical" href="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
	<title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
	<%-- Social Media --%>
	<meta property="og:title" content="<%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %>" />
	<meta property="og:type" content="business:business" />
	<meta property="og:image" content="<%=externalLinks.getExternalUrlForAsset("/etc/designs/searspartsdirect/clientlib_base/img/socialLogo.jpg") %>" />
	<meta property="og:url" content="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
	<%-- Viewport and Styles --%>
	<cq:include script="headlibs.jsp"/>
	<cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/>
	<%-- Head Scripts --%>
	<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
	<cq:include script="stats.jsp"/>
	<cq:include script="/libs/wcm/mobile/components/simulator/simulator.jsp"/>
	<%-- Favorite Icons --%>
	<% if (favIcon != null) { %>
	<link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
	<% } %>
</head>