<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="forgotPasswordModal" class="modal hide fade" role="dialog" aria-labelledby="forgotPasswordModalLabel" aria-hidden="true">
    <h1 id="forgotPasswordModalLabel">Forgot Password<span class="pull-right">Existing Customer? <a data-toggle="modal" data-dismiss="modal" data-target="#loginModal">Sign In</a></span></h1>
    <form method="post" action="${secureMainSitePath}/partsdirect/register.pd" data-constraints='@EmailsMatch(field1="registerEmail", field2="registerEmailConfirm", groups=[registerModal])' data-regulagroup="forgotPasswordModal">
        <div class="alert alert-error hidden">&nbsp;</div>
        <fieldset>
            <label>Email<span>Required</span></label>
            <input type="text" name="email" id="fldEmail" class="text width_235 required email">
            <input type="hidden" name="returnTo" value="" id="forgotModalPswForm_returnTo">
            <input type="hidden" name="commercialUI" value="false" id="forgotModalPswForm_commercialUI">
        </fieldset>
        <p>By clicking register, I agree to the <span><a href="#">terms of use</a></span> and <span><a href="#">privacy policy</a></span>.</p>
        <div class="pull-right">
            <button type="button" class="new-btn" data-dismiss="modal" data-cancel="true" aria-hidden="true">Cancel</button>
            <button type="button" class="new-btn new-btn-search" data-submit="true">Continue</button>
        </div>
    </form>
</div>