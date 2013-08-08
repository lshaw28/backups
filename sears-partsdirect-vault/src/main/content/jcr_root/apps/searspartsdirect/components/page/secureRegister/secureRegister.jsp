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
<title>Register</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css">
<cq:includeClientLib css="apps.searspartsdirect.login" />
</head>

<body>
<div class="row-fluid" id="secureRegisterModal">
	<div class="span6 offset3">
		<h1 id="registerModalLabel">Register<span class="pull-right">Existing Customer? <a data-toggle="modal" data-dismiss="modal" data-target="#loginModal">Sign In</a></span></h1>
		<form method="post" action="${secureMainSitePath}/partsdirect/register.pd" data-constraints='@EmailsMatch(field1="registerEmail", field2="registerEmailConfirm")' data-regulagroup="registerModal">
			<div class="alert alert-error hidden">&nbsp;</div>
			<fieldset>
				<label>First Name<span>Required</span></label>
				<input type="text" name="user.firstName" data-constraints='@Required(message="First name is required.")' />
				<label>Email<span>Required</span></label>
				<input type="text" id="registerEmail" name="user.email" data-constraints='@Required(message="Email is required.") @Required(message="A valid email address is required.")' />
				<label>Confirm Email<span>Required</span></label>
				<input type="text" id="registerEmailConfirm" name="user.confirmEmailAddress" data-constraints='@Required(message="Please confirm your email address.") @Required(message="A valid confirmation email address is required.")' />
				<label>Password<span>Required</span></label>
				<input type="password" name="user.password" data-constraints='@Required(message="Please provide a password.") @Pattern(regex=/(?=^.{6,15}$)(\w*(?=\w*\d)(?=\w*[a-zA-Z])\w*)/, message="Please match password rules.")' />
				<label>
				Zip Code<span>Required</span>
				<label>
				<input type="text" name="user.zip" data-constraints='@Required(message="Please provide a valid ZIP code.")' />
				<label class="checkbox">
					<input type="checkbox" name="emailOptin" value="true" checked="checked" data-checkfield="#emailOptinHidden">
					Send me promos, discounts and other special information from SearsPartsDirect.com </label>
				<input type="hidden" name="__checkbox_emailOptin" id="emailOptinHidden" value="true">
				<input type="hidden" name="currentPageURL" id="currentPageURL" value="" />
				<input type="hidden" name="successfulRegistrationURL" id="successfulRegistrationURL" value="" />
				<input type="hidden" name="user.lastName" value="" />
			</fieldset>
			<p>By clicking register, I agree to the <span><a href="#">terms of use</a></span> and <span><a href="#">privacy policy</a></span>.</p>
			<div class="pull-right">
				<button type="button" class="new-btn" data-dismiss="modal" data-cancel="true" aria-hidden="true">Cancel</button>
				<button type="button" class="new-btn new-btn-search" data-submit="true">Register</button>
			</div>
		</form>
	</div>
</div>
<cq:includeClientLib js="apps.searspartsdirect.login" />
</body>
</html>