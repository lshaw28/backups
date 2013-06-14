<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="registerModal" class="modal hide fade" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
	<h1 id="registerModalLabel">Register<span class="pull-right">Existing Customer? <a data-toggle="modal" data-target="#loginModal">Sign In</a></span></h1>
	<form>
		<fieldset>
			<label>First Name<span>Required</span></label>
			<input type="text" name="firstName" />
			<label>Email<span>Required</span></label>
			<input type="text" name="email" />
			<label>Confirm Email<span>Required</span></label>
			<input type="text" name="emailConfirm" />
			<label>Password<span>Required</span></label>
			<input type="password" name="password" />
			<label>Zip Code<span>Required</span><label>
			<input type="text" name="zipCode" />
			<label class="checkbox">
				<input type="checkbox" name="receiveEmails" checked /> Send me promos, discounts and other special information from SearsPartsDirect.com
			</label>
		</fieldset>
		<p>By clicking register, I agree to the <span><a href="#">terms of use</a></span> and <span><a href="#">privacy policy</a></span>.</p>
		<div class="pull-right">
			<button class="new-btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="new-btn new-btn-search">Register</button>
		</div>
	</form>
</div>