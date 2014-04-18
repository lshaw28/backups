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

<spd:getHeaderHelperData />

<c:choose>
	<c:when test="${template eq '/apps/searspartsdirect/templates/modelHelp'}" >
		<spd:getUrlRelation />
		<title><c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /> Symptoms for Repair | Symptom Diagnosis - Sears PartsDirect</title>
		<meta name="description" content="Troubleshoot your <c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /> and research repair symptoms at Sears PartsDirect. Find out which repairs might help solve the problem, parts and more." />
		<meta name="keywords" content="<c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /> Symptoms, <c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /> Repair Guides" />
	</c:when>
	<c:when test="${template eq '/apps/searspartsdirect/templates/categorySymptom'}" >
		<spd:getUrlRelation relationType="symptom" />
		<spd:getSymptomDetail id="${symptomRelation.id}" />
		<spd:getRelation single="true" assetType="productCategory" />
		<c:if test="${empty productCategoryRelation}">
			<spd:getUrlRelation relationType="productCategory" />
		</c:if>

		<spd:getUrlRelation relationType="model" />
		<c:choose>
		<c:when test="${!empty modelRelation}">
			<title><c:out value="${productCategoryRelation.title}"/> <c:out value="${modelRelation}" /> <c:out value="${symptom.title}" /> - Sears PartsDirect</title>
		</c:when>
		<c:otherwise>
			<title><c:out value="${productCategoryRelation.title}" /> <c:out value="${symptom.title}" /> | Symptom Diagnosis - Sears PartsDirect</title>
		</c:otherwise>
		</c:choose>

		<meta name="description" content="Learn why your <c:out value="${productCategoryRelation.title}" />  <c:out value="${symptom.title}" /> at Sears PartsDirect. Find out which repairs might help solve the problem, parts and more." />
		<meta name="keywords" content="<c:out value="${productCategoryRelation.title}" /> repairs, <c:out value="${productCategoryRelation.title}" /> <c:out value="${symptom.title}" />, repair guides, repair help, diy" />
	</c:when>
	<c:otherwise>
			<title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
			<meta name="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
			<meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
	</c:otherwise>
</c:choose>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="author" content="Sears PartsDirect" />


<meta name="robots" content="index, follow" />
<c:choose>
	<c:when test="${template eq '/apps/searspartsdirect/templates/categorySymptom'}" >
		<link rel="canonical" href="<%=externalLinks.getExternalUrlForPage(request.getRequestURI()) %>" />
	</c:when>
	<c:otherwise>
		<link rel="canonical" href="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
	</c:otherwise>
</c:choose>
<meta property="og:title" content="<%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %>" />
<meta property="og:type" content="business:business" />
<meta property="og:image" content="<%=externalLinks.getExternalUrlForAsset("/etc/designs/searspartsdirect/clientlib_base/img/socialLogo.jpg") %>" />
<meta property="og:url" content="<%=externalLinks.getExternalUrlForPage(currentPage.getPath()) %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"><spd:getPDEnvDetail />
<spd:getLocalUrl /><meta name="global-mainSitePath" content="${nonSecurePDUrl}" />
<meta name="global-mainSitePathSecure" content="${securePDUrl}" />
<meta name="global-PRCVar" content="<%=properties.get("parAddress/address/city","")+", " + properties.get("parAddress/address/state","")+" | " + properties.get("parAddress/address/zipcode","") %>" />
<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" />
<meta name="global-apiPath" content="${PdApiRoot}" />
<meta name="global-apiPathSecure" content="${PdApiRootSecure}" />
<meta name="global-guestCookieId" content="${myProfileModelCookie}" />
<meta name="global-registeredUserId" content="${userId}" />
<meta name="global-cartId" content="${shoppingCartCookieId}" /><%
%><cq:include script="headlibs.jsp"/><%
%><cq:include script="/libs/wcm/core/components/init/init.jsp"/><%
%><cq:include script="stats.jsp"/>
<% if (favIcon != null) { %><link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>" /><% } %>

</head>