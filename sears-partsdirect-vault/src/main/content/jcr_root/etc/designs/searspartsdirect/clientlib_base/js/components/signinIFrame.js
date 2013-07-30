/*global window:true, $:true, Class:true, regula:true */
var signinIFrame = Class.extend(function () {
    "use strict";

    return {

        init: function () {

        },


        submitCallback: function (isConsumer) {
            if (!isConsumer) {
                passwordField.name = "j_password";
                userField.name = "j_username";
                document.loginFormModal.action = commercial_Url+"/partsdirect/commercialLogin.pd?email=" + $('#fldUsername').val();
                $('.regErrorPara span').html("Our records show you're a member of our commercial parts website. We're automatically redirecting you to Sears Commercial Parts.").show();
                setTimeout(function(){document.loginFormModal.submit();}, 3000);
            } else if ( isSSOEnabled ) {
                tb_show("", "#TB_inline?height=285&amp;width=450&amp;inlineId=SSOSwitchAlertMessage", "");
            } else {
                document.loginFormModal.submit();
            }
        },

        submitFormModal: function(userName, password) {
            var self = this;

            LoginAction.prepareConsumerLogin(	jQuery.trim(userName),
                jQuery.trim(password),
                authURL, authFailureURL,
                {
                    callback: self.submitCallback
                });
        }

    };

}());