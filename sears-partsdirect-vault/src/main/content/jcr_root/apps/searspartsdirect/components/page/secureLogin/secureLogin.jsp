<!doctype html>
<%@ include file="/apps/searspartsdirect/global.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="X-UA-Compatible" content="IE=edge,chrome=1" />
<%-- Environment Variables --%>
<spd:getPDEnvDetail />
<spd:getLocalUrl />
<meta name="global-mainSitePath" content="${nonSecurePDUrl}" />
<meta name="global-mainSitePathSecure" content="${securePDUrl}" />
<meta name="global-channelVar" content="Home Page" />
<meta name="global-modelSearchServletPath" content="bin/searspartsdirect/modelsearch" />
<spd:getHeaderHelperData />
<meta name="global-apiPath" content="${PdApiRoot}" />
<meta name="global-apiPathSecure" content="${PdApiRootSecure}" />
<meta name="global-guestCookieId" content="${myProfileModelCookie}" />
<meta name="global-registeredUserId" content="${userId}" />
<meta name="global-cartId" content="${shoppingCartCookieId}" />
<title>Log In</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib css="apps.searspartsdirect.login" />
<cq:includeClientLib js="apps.searspartsdirect.head" />
</head>

<body>

    <div class="row-fluid hidden" id="secureLoginModal">
        <div class="span10 offset1">
            <h1 id="loginModalLabel">Sign In<span class="pull-right"><p class="headline">New Customer?</p> <a data-toggle="modal" data-dismiss="modal" data-target="#registerModal">Register</a></span></h1>
            <form id="secureLoginFormModal" name="secureLoginFormModal" method="post" action="${ssoServerUrl}" data-regulagroup="loginModal">
                <div class="alert alert-error hidden">&nbsp;</div>
                <fieldset>
                    <label>Email<span>Required</span></label>
                    <input type="text" name="loginId" data-constraints='@Required(message="Email is required.", groups=[loginModal]) @Required(message="A valid email address is required.", groups=[loginModal])' />
                    <label>Password<span>Required</span></label>
                    <input type="password" name="logonPassword" data-constraints='@Required(message="Please provide a password.", groups=[loginModal])' />
                    <input type="hidden" value="${secureMainSitePath}${pdServicePath}" name="service" id="service" />
                    <input type="hidden" value="true" name="renew" id="renew" />
                    <br />
                    <a data-toggle="modal" data-dismiss="modal" data-target="#forgotPasswordModal">Forgot Password?</a>
                </fieldset>
                <div class="pull-right">
                    <button type="button" class="new-btn new-btn-modal" data-dismiss="modal" data-cancel="true"aria-hidden="true">Cancel</button>
                    <button type="button" class="new-btn new-btn-modal new-btn-search" data-submit="true">Sign In</button>
                </div>
            </form>
        </div>
    </div>
<cq:includeClientLib js="apps.searspartsdirect.login" />
</body>
</html>