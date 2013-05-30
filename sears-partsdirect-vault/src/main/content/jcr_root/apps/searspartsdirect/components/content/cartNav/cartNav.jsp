<div class="cart_navigation ">
				<ul>
					<div class="li_shadow_curve"></div>


					<li id="RecentlyViewed"><a href="javascript:void(0);">Recently viewed&nbsp;&nbsp;<span></span></a>
						<div id="RecentlyViewedDiv" class="png_fix" style="display: none;">
							<div class="topArrowDiv"></div>
							<div class="resentView">
								<div class="resentViewTopRow"><div class="topLeftCurve"></div><div class="topRightCurve"></div></div>
								<div class="clear"></div>
								<div class="resentViewContentDiv">
									<div class="ViewContent">

										<spd:getRecentlyViewed />
										<!-- Model List -->
										<c:forEach var="model" items="${rvModelList}">
											<div class="view_row">
												<div class="row_col1">
													Model <a href="${model.itemURL}">${model.itemName}</a><br />
													<a href="${model.itemURL}">${model.itemDescription}</a>
												</div>
											</div>
										</c:forEach>
										<!-- Part List -->
										<c:forEach var="part" items="${rvPartList}">
											<div class="view_row">
												<div class="row_col2">
													<c:choose>
														<c:when test='${part.itemImageURL != null && part.itemImageURL != "null"}'>
															<a href="${part.itemURL}"><img src="${part.itemImageURL}" alt="${part.itemDescription}"/></a>
														</c:when>
														<c:otherwise>
															<a href="${part.itemURL}"><img src="/assets/img/images/no_part_100x100.gif" alt="No part image available" height="20px" width="20px"/></a>
														</c:otherwise>
													</c:choose>
												</div>
												<div class="row_col3">
													<a href="${part.itemURL}">${part.itemName}<br /> ${part.itemDescription}</a>
												</div>
											</div>
										</c:forEach>

										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
								<div class="resentViewBottopRow"><div class="bottomLeftCurve"></div><div class="bottomRightCurve"></div></div>
							</div>
						</div>
					</li>
					<li id="MyModels">
						<div id="modelListBtn" class="my_owned_models" ><a href="javascript:void(0);" id="modelCountBtn">My Models (
							<label id="modelCount">0</label>
							)<span></span></a> </div>
					</li>
					<li id="ItemsCarts" class="Btn_ghostCart ">
						<spd:getShoppingCart/>
						<a class="shoppCart_leftImg" href="${mainSitePath}/partsdirect/showCart.pd" title="Shopping"><span class="cart"></span> Cart&nbsp;<span id="scCartItemCountHeader">${cartItemCount}</span><span></span></a></li>
				</ul>
			</div>