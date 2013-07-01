<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="loginModal" class="modal hide fade" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
	<h1 id="loginModalLabel">Login<span class="pull-right">New Customer? <a data-toggle="modal" data-target="#registerModal">Register</a></span></h1>
	<form method="post" action="${loginPath}">
		<fieldset>
			<label>Email<span>Required</span></label>
			<input type="text" name="loginId" />
			<label>Password<span>Required</span></label>
			<input type="password" name="logonPassword" /><a href="#">Forgot Password?</a>
		</fieldset>
		<div class="pull-right">
			<button class="new-btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="new-btn new-btn-search" onclick="return false;regula.validate();">Sign In</button>
		</div>
	</form>
</div>