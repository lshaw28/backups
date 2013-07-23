<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRecentlyViewed />
<ul class="cartNavItems">
	<div class="cartNavShadow"></div>
	<li class="cartNavItem">
		<div class="btn-group">
			<c:choose>
				<c:when test="${not empty rvModelList and not empty rvPartList}">
					<a data-toggle="dropdown" href="#">Recently Viewed <i class="icon-caret-down">&nbsp;</i></a>
					<ul class="dropdown-menu">

						<!-- Model List -->
						<c:forEach var="model" items="${rvModelList}">
							<li>Model <a href="${model.itemURL}">${model.itemName}</a><br />
							<a href="${model.itemURL}">${model.itemDescription}</a></li>
						</c:forEach>
						<!-- Part List -->
						<c:forEach var="part" items="${rvPartList}">
							<li>
								<a href="${part.itemURL}">
								<c:choose>
									<c:when test='${part.itemImageURL != null && part.itemImageURL != "null"}'>
										<img src="${part.itemImageURL}" alt="${part.itemDescription}" />
									</c:when>
									<c:otherwise>
										<img src="/assets/img/images/no_part_100x100.gif" alt="No part image available" />
									</c:otherwise>
								</c:choose>
								${part.itemName}<br />${part.itemDescription}</a>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<a data-toggle="false">Recently Viewed <i class="icon-caret-down">&nbsp;</i></a>
				</c:otherwise>
			</c:choose>
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
					<span class="count-badge parentheses">${fn:length(myProfileModels)}</span>
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
							<span class="cartModelItem"><input type="checkbox" value="${model.modelNumber}" data-categoryid="${model.categoryId}" data-brandId="${model.brandId}" /><a href="${mainSitePath}${model.itemURL}">${model.brandName} ${model.categoryName} model #${model.modelNumber}</a></span>
						</c:forEach>
						</li>
						<c:choose>
							<c:when test="${loggedIn}">
								<li><a class="new-btn" href="${mainSitePath}/partsdirect/">Edit List</a></li>
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
			<a data-toggle="dropdown" href="#"><i class="icon-shopping-cart">&nbsp;</i><span class="hidden-phone"> Cart</span>
			<c:choose>
				<c:when test="${not empty shoppingCart && fn:length(shoppingCart) gt 99}">
					<span class="count-badge">99+</span>
				</c:when>
				<c:when test="${not empty shoppingCart && fn:length(shoppingCart) gt 0 && fn:length(shoppingCart) lt 100}">
					<span class="count-badge">${fn:length(shoppingCart)}</span>
				</c:when>
				<c:otherwise>
					<span class="count-badge">0</span>
				</c:otherwise>
			</c:choose><i class="icon-caret-down hidden-phone">&nbsp;</i></a>
			<ul class="dropdown-menu">
				<li class="cart-title"><strong>Your Shopping Cart</strong></li>
				<c:choose>
					<c:when test="${fn:length(shoppingCart) gt 0}">
						<li><strong><span class="cart-part">Parts</span><span class="cart-quantity">Quantity</span></strong></li>
						<li><a class="new-btn-search" href="${mainSitePath}/partsdirect/showCart.pd">Check Out Now</a></li>
						<c:forEach var="cartItem" items="${shoppingCart}">
							<li class="cart-item">
								<span class="cart-part"><a href="${mainSitePath}/partsdirect/part-number/${cartItem.part.partNumber}/${cartItem.part.productGroupId}/${cartItem.part.supplierId}">${cartItem.part.partNumber}</a><br />
								<c:choose>
									<c:when test="${fn:length(cartItem.part.description) > 17}">
										${fn:substring(cartItem.part.description, 0, 17)}...
									</c:when>
									<c:otherwise>
										${cartItem.part.description}
									</c:otherwise>
								</c:choose></span>
								<span class="cart-quantity">${cartItem.quantity}</span>
							</li>
						</c:forEach>
						<li><strong>Total items: ${fn:length(shoppingCart)}</strong></li>
						<li><a class="new-btn" href="${mainSitePath}/partsdirect/showCart.pd">View Entire Cart</a></li>
					</c:when>
					<c:otherwise>
						<li>Your shopping cart is empty</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</li>
</ul>