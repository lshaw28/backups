/*global $:true, window:true, document:true, Class:true, searchPanel:true, revealPanel:true, responsiveImage: true, video:true, guideNavigation:true, regula:true */
(function (window) {
    "use strict";
    /**
     * Global functionality instantiation
     */
    $(document).ready(function () {
		/**
		 * Set window messaging
		 */
		window['parentDomain'] = decodeURIComponent(document.location.hash.replace(/^#/, ''));
		/**
		 * Initialize userData API class
		 */
		var newUserData = new userData();
    });
}(window));