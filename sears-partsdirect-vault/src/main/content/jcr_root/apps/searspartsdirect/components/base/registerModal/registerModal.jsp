<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="registerModal" class="modal hide fade" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
	<h1 id="registerModalLabel">Register<span class="pull-right">Existing Customer? <a data-toggle="modal" data-target="#loginModal">Sign In</a></span></h1>
	<form method="post" action="${registerPath}" data-constraints='@EmailsMatch(field1="registerEmail", field2="registerEmailConfirm", groups=[registerModal])' data-regulagroup="registerModal">
		<div class="alert alert-error hidden">&nbsp;</div>
		<fieldset>
			<label>First Name<span>Required</span></label>
			<input type="text" name="user.firstName" data-constraints='@Required(message="First name is required.", groups=[registerModal])' />
			<label>Email<span>Required</span></label>
			<input type="text" id="registerEmail" name="user.email" data-constraints='@Required(message="Email is required.", groups=[registerModal]) @Required(message="A valid email address is required.", groups=[registerModal])' />
			<label>Confirm Email<span>Required</span></label>
			<input type="text" id="registerEmailConfirm" name="user.confirmEmailAddress" data-constraints='@Required(message="Please confirm your email address.", groups=[registerModal]) @Required(message="A valid confirmation email address is required.", groups=[registerModal])' />
			<label>Password<span>Required</span></label>
			<input type="text" name="user.password" data-constraints='@Required(message="Please provide a password.", groups=[registerModal])' />
			<label>Zip Code<span>Required</span><label>
			<input type="text" name="user.zip" data-constraints='@Required(message="Please provide a valid ZIP code.", groups=[registerModal])' />
			<label class="checkbox">
				<input type="checkbox" name="emailOptin" value="true" checked="checked" data-checkfield="#emailOptinHidden"> Send me promos, discounts and other special information from SearsPartsDirect.com
			</label>
			<input type="hidden" name="__checkbox_emailOptin" id="emailOptinHidden" value="true">
		</fieldset>
		<p>By clicking register, I agree to the <span><a href="#">terms of use</a></span> and <span><a href="#">privacy policy</a></span>.</p>
		<div class="pull-right">
			<button class="new-btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="new-btn new-btn-search" data-submit="true">Register</button>
		</div>
	</form>
</div>