<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><ul class="cartNavItems">
	<div class="cartNavShadow"></div>
	<li id="cartRecents" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="false">Recently Viewed <i class="icon-caret-down">&nbsp;</i></a>
			<ul class="dropdown-menu"></ul>
		</div>
	</li>
	<li id="cartModels" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="dropdown" href="#">My Models&nbsp;<span class="count-badge parentheses">0</span>&nbsp;<i class="icon-caret-down">&nbsp;</i></a>
			<ul class="dropdown-menu">
				<li id="cartModelItems" class="inactive"></li>
				<li id="cartUserEdit" class="inactive"><a class="new-btn" href="${secureMainSitePath}/partsdirect/myProfileMyOwnedModels.pd">Edit List</a></li>
				<li id="cartGuestEdit" class="inactive"><button type="button" class="new-btn edit_js">Edit List</button></li>
				<li id="cartGuestControls" class="inactive"><button type="button" class="new-btn new-btn-edit remove_js">Remove</button><button type="button" class="new-btn new-btn-borderless pull-right cancel_js">Cancel</button></li>
				<li class="cartModelsEmpty_js">You can find parts to your models faster by adding models you own to this list.<br /><br /><a class="new-btn" href="${mainSitePath}/partsdirect/linkToProfilePromoPage.action">Learn More</a></li>
			</ul>
		</div>
	</li>
	<li id="cartShop" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="dropdown" href="#"><i class="icon-shopping-cart">&nbsp;</i><span class="hidden-phone"> Cart</span>&nbsp;<span class="count-badge">0</span>&nbsp;<i class="icon-caret-down hidden-phone">&nbsp;</i></a>
			<ul class="dropdown-menu">
				<li class="cart-title"><strong>Your Shopping Cart</strong></li>
				<li class="cartShopCheckout_js cartShopCheckOut_js inactive"><a class="new-btn new-btn-search" href="${mainSitePath}/partsdirect/showCart.pd">Checkout Now</a></li>
				<li class="cartShopHeader_js inactive"><strong><span class="cart-part">Parts</span><span class="cart-quantity">Quantity</span></strong></li>
				<li class="cartShopTotals_js inactive"><strong>Total items: <span class="cartShopCount_js">0</span></strong></li>
				<li class="cartShopView_js inactive"><a class="new-btn new-btn-add-to-cart" href="${mainSitePath}/partsdirect/showCart.pd">View Entire Cart</a></li>
				<li class="cartShopEmpty_js">Your shopping cart is empty.</li>
			</ul>
		</div>
	</li>
</ul>