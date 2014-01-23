<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><%
%>
<div id="bannerContainer" class="visible-desktop hidden-phone hidden-tablet"></div>
<script id="js_filterBannerTemplate" type="text/x-handlebars-template">
    <div class="banner">
        <a href="{{pageUrl}}">
            <img class="newRibbonIcon" src="/etc/designs/searspartsdirect/clientlib_base/img/banners/newRibbonIcon.png" />
                <span class="AFText">{{bannerHeading}}</span>
            <span class="SignUpText">Sign up for automatic reorder to receive a new air filter every 3, 6 or 12 months.</span>
            <span class="shopAF_btn">Shop Filters</span>
                <img class="airBannerAd" src="{{filterImageUrl}}" />
            <span class="freeShipping">
                <img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png" /><br/>
                Get <i>FREE Standard Shipping</i> if you sign up for automatic reorder.*
            </span>
        </a>
    </div>
</script>