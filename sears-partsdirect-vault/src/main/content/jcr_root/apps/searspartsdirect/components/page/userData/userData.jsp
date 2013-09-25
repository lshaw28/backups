<!doctype html>
<%@ include file="/apps/searspartsdirect/global.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<%-- Environment Variables --%>
<spd:getPDUrl />
<spd:getLocalUrl />
<meta name="global-mainSitePath" content="${nonSecurePDUrl}" />
<meta name="global-mainSitePathSecure" content="${securePDUrl}" />
<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" />
<spd:getHeaderHelperData />
<meta name="global-apiPath" content="${PdApiRoot}" />
<meta name="global-apiPathSecure" content="${PdApiRootSecure}" />
<title>User Data</title>
<cq:includeClientLib js="apps.searspartsdirect.head" />
</head>

<body>
<cq:includeClientLib js="apps.searspartsdirect.user" />
</body>
</html>