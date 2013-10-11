<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><%@ page import="com.day.cq.commons.Doctype,
	org.apache.commons.lang3.StringEscapeUtils,
	com.spd.cq.searspartsdirect.common.helpers.ExternalLinks" %><%
%><%
	String favIcon = currentDesign.getPath() + "/favicon.ico";
	if (resourceResolver.getResource(favIcon) == null) {
		favIcon = null;
	}
	ExternalLinks externalLinks = new ExternalLinks(slingRequest);
%><head>
<title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="author" content="Sears PartsDirect" />
<meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
<meta name="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
<meta name="robots" content="index, follow" />
<link rel="canonical" href="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
<meta property="og:title" content="<%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %>" />
<meta property="og:type" content="business:business" />
<meta property="og:image" content="<%=externalLinks.getExternalUrlForAsset("/etc/designs/searspartsdirect/clientlib_base/img/socialLogo.jpg") %>" />
<meta property="og:url" content="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"><spd:getPDEnvDetail />
<spd:getLocalUrl /><meta name="global-mainSitePath" content="${nonSecurePDUrl}" />
<meta name="global-mainSitePathSecure" content="${securePDUrl}" />
<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" /><spd:getHeaderHelperData />
<meta name="global-apiPath" content="${PdApiRoot}" />
<meta name="global-apiPathSecure" content="${PdApiRootSecure}" />
<meta name="global-guestCookieId" content="${myProfileModelCookie}" />
<meta name="global-registeredUserId" content="${userId}" />
<meta name="global-cartId" content="${shoppingCartCookieId}" />
<cq:include script="headlibs.jsp"/>
<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
<cq:include script="stats.jsp"/>
<!--  PD 24*7 chat flag -> ${pd247ChatFlag} -->
<script type="text/JavaScript" src="https://secure.ifbyphone.com/js/ibp_clickto_referral.js"></script>
<script type="text/JavaScript">
    var _ibp_public_key = "ebc48a973d649b0f9d0ed3398d0c425669b2ebc1";
    var _ibp_formatting = true;
    var _ibp_keyword_set = 59924;
</script> 
<% if (favIcon != null) { %><link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" /><% } %>
</head>