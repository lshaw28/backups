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
<title>Forgot Password</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib css="apps.searspartsdirect.login" />
<cq:includeClientLib js="apps.searspartsdirect.head" />
</head>

<body>
<div class="row-fluid" id="secureForgotPasswordModal">
	<div class="span10 offset1">
		<h1 id="forgotPasswordModalLabel">Forgot Password</h1>
		<form id="secureForgotPasswordFormModal" method="post" action="${secureMainSitePath}/partsdirect/register.pd" data-regulagroup="forgotPasswordModal">
			<input type="hidden" name="currentForgotPageURL" value="/partsdirect/index.pd" id="currentForgotPageURL">
			<div class="alert alert-error hidden">&nbsp;</div>
			<p class="forgot-intro">Please enter your email address for this account. We will send you a confirmation email containing a link to reset your password. The password can be used across the entire suite of Sears websites.</p>
			<p class="hidden forgot-success">An email has been sent to your account. Please follow the link in the email and you'll be prompted to create a new password. The password can be used for PartsDirect, Kmart, Sears, etc.</p>
			<fieldset>
				<label>Email</label>
				<input type="text" name="email" id="fldEmail" class="text width_235 required email">
				<input type="hidden" name="returnTo" value="" id="forgotModalPswForm_returnTo">
				<input type="hidden" name="commercialUI" value="false" id="forgotModalPswForm_commercialUI">
			</fieldset>
			<div class="pull-right">
				<button type="button" class="new-btn" data-dismiss="modal" data-cancel="true" aria-hidden="true">Cancel</button>
				<button type="button" class="new-btn new-btn-search" data-submit="true">Continue</button>
			</div>
		</form>
	</div>
</div>
<cq:includeClientLib js="apps.searspartsdirect.login" />
</body>
</html>