<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRecentlyViewed />
<ul class="cartNavItems">
	<div class="cartNavShadow"></div>
	<li id="cartRecents" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="false">Recently Viewed <i class="icon-caret-down">&nbsp;</i></a>
			<ul class="dropdown-menu"></ul>
		</div>
	</li>
	<li id="cartModels" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="dropdown" href="#">My Models
			<c:choose>
				<c:when test="${not empty myProfileModels && fn:length(myProfileModels) gt 99}">
					<span class="count-badge parentheses">99+</span>
				</c:when>
				<c:when test="${not empty myProfileModels && fn:length(myProfileModels) gt 0 && fn:length(myProfileModels) lt 100}">
					<span class="count-badge parentheses"><c:out value="${fn:length(myProfileModels)}" /></span>
				</c:when>
				<c:otherwise>
					<span class="count-badge parentheses">0</span>
				</c:otherwise>
			</c:choose>
			<i class="icon-caret-down">&nbsp;</i></a>
			<ul class="dropdown-menu">
				<c:choose>
					<c:when test="${not empty myProfileModels && fn:length(myProfileModels) gt 0}">
						<li id="cartModelItems">
						<c:forEach var="model" items="${myProfileModels}">
							<span class="cartModelItem"><input type="checkbox" value="${model.id}" /><a href="${mainSitePath}${model.itemURL}"><c:out value="${model.brandName}" /> <c:out value="${model.categoryName}"/> model #<c:out value="${model.modelNumber}" /></a></span>
						</c:forEach>
						</li>
						<c:choose>
							<c:when test="${loggedIn}">
								<li><a class="new-btn" href="${mainSitePath}/partsdirect/myProfileMyOwnedModels.pd">Edit List</a></li>
							</c:when>
							<c:otherwise>
								<li id="cartGuestEdit"><button type="button" class="new-btn edit_js">Edit List</button></li>
								<li id="cartGuestControls"><button type="button" class="new-btn new-btn-edit remove_js">Remove</button><button type="button" class="new-btn new-btn-borderless pull-right cancel_js">Cancel</button></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<li>You can find parts to your models faster by adding models you own to this list.<br /><br /><a class="new-btn" href="${mainSitePath}/partsdirect/linkToProfilePromoPage.action">Learn More</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</li>
	<li id="cartShop" class="cartNavItem">
		<div class="btn-group">
			<a data-toggle="dropdown" href="#"><i class="icon-shopping-cart">&nbsp;</i><span class="hidden-phone"> Cart</span>&nbsp;<span class="count-badge">0</span>&nbsp;<i class="icon-caret-down hidden-phone">&nbsp;</i></a>
			<ul class="dropdown-menu">
				<li class="cart-title"><strong>Your Shopping Cart</strong></li>
				<li class="cartShopCheckout_js inactive"><a class="new-btn new-btn-search" href="${mainSitePath}/partsdirect/showCart.pd">Checkout Now</a></li>
				<li class="cartShopHeader_js inactive"><strong><span class="cart-part">Parts</span><span class="cart-quantity">Quantity</span></strong></li>
				<li class="cartShopTotals_js inactive"><strong>Total items: <span class="cartShopCount_js">0</span></strong></li>
				<li class="cartShopView_js inactive"><a class="new-btn" href="${mainSitePath}/partsdirect/showCart.pd">View Entire Cart</a></li>
				<li class="cartShopEmpty_js">Your shopping cart is empty.</li>
			</ul>
		</div>
	</li>
</ul>