<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="loginModal" class="modal hide fade" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
	<h1 id="loginModalLabel">Sign In<span class="pull-right">New Customer? <a data-toggle="modal" data-dismiss="modal" data-target="#registerModal">Register</a></span></h1>
	<form method="post" action="${loginPath}" data-regulagroup="loginModal">
		<div class="alert alert-error hidden">&nbsp;</div>
		<fieldset>
			<label>Email<span>Required</span></label>
			<input type="text" name="loginId" data-constraints='@Required(message="Email is required.", groups=[loginModal]) @Required(message="A valid email address is required.", groups=[loginModal])' />
			<label>Password<span>Required</span></label>
			<input type="password" name="logonPassword" data-constraints='@Required(message="Please provide a password.", groups=[loginModal])' />
            <br />
            <a href="#">Forgot Password?</a>
		</fieldset>
		<div class="pull-right">
			<button class="new-btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="new-btn new-btn-search" data-submit="true">Sign In</button>
		</div>
	</form>
</div>