<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div id="forgotPasswordModal" class="modal hide fade" role="dialog" aria-labelledby="forgotPasswordModalLabel" aria-hidden="true">
    <h1 id="forgotPasswordModalLabel">Forgot Password</h1>
    <form method="post" action="${secureMainSitePath}/partsdirect/register.pd" data-constraints='@EmailsMatch(field1="registerEmail", field2="registerEmailConfirm", groups=[registerModal])' data-regulagroup="forgotPasswordModal">
        <div class="alert alert-error hidden">&nbsp;</div>
        <p>Please enter your email address for this account. We will send you a confirmation email containing a link to reset your password. The password can be used across the entire suite of Sears websites.</p>
        <fieldset>
            <label>Email<span>Required</span></label>
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