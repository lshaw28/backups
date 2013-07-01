<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="registerModal" class="modal hide fade" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
	<h1 id="registerModalLabel">Register<span class="pull-right">Existing Customer? <a data-toggle="modal" data-target="#loginModal">Sign In</a></span></h1>
	<form method="post" action="${registerPath}" data-constraints='@EmailsMatch(field1="registerEmail", field2="registerEmailConfirm")'>
		<fieldset>
			<label>First Name<span>Required</span></label>
			<input type="text" name="user.firstName" data-constraints="@Required" />
			<label>Email<span>Required</span></label>
			<input type="text" id="registerEmail" name="user.email" data-constraints="@Required @Email" />
			<label>Confirm Email<span>Required</span></label>
			<input type="text" id="registerEmailConfirm" name="user.confirmEmailAddress" data-constraints="@Required @Email" />
			<label>Password<span>Required</span></label>
			<input type="user.password" name="password" data-constraints="@Required" />
			<label>Zip Code<span>Required</span><label>
			<input type="text" name="user.zip" data-constraints="@Required" />
			<label class="checkbox">
				<input type="checkbox" name="emailOptin" value="true" checked="checked" id="emailOptin"> Send me promos, discounts and other special information from SearsPartsDirect.com
			</label>
			<input type="hidden" name="__checkbox_emailOptin" value="true">
		</fieldset>
		<p>By clicking register, I agree to the <span><a href="#">terms of use</a></span> and <span><a href="#">privacy policy</a></span>.</p>
		<div class="pull-right">
			<button class="new-btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="new-btn new-btn-search" onclick="return false;regula.validate();">Register</button>
		</div>
	</form>
</div>