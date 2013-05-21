<%@ include file="/apps/searspartsdirect/global.jsp" %>
<cq:include path="brandBar" resourceType="/apps/searspartsdirect/components/content/brandBar" />

<!-- From Static -->
<div id="viewport">
	<div role="main" class="container-fluid">
		<div class="home_header ">
			<div class="inner_header ie_inner_header ">
				<div class="body_shadow_left"></div>
				<div class="body_shadow_right"></div>
				<div class="home_logo"></div>
				<div class="header_right_panel ">
					<div class="trigger"></div>
					<div class="login_navigation" id="logNav">
						<ul>
							<li class="home_menu_link"><a href="#">Home</a>
							<li class="first login_link"><a class="mainSignInModal" href="javascript:void(0)" title="Login"><spd:getLoginStatus/></a></li>
							<li class="register"><a class="mainRegisterModal" title="Register" href="javascript:void(0)"><spd:getRegisterStatus/></a></li>
							<li class="order_lookup"> <a href="/partsdirect/orderStatus.pd?pop=flush" title="Order status">Order lookup</a></li>
							<li><a href="javascript:void(0);" id="getHelpTopLink">Get help</a></li>
							<li class="feedback_link"> <a href="http://searshc.us2.qualtrics.com/SE/?SID=SV_5BkCXqmfocMN11W" onClick="window.open(this.href, 'GiveFeedback', 'resizable=no,status=no,location=no,toolbar=no,menubar=no,fullscreen=no,scrollbars=yes,dependent=no, width=950, height =650, top=0, left=150'); return false;">Feedback</a></li>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="cart_navigation ">
						<ul>
							<div class="li_shadow_curve"></div>
							<li id="RecentlyViewed"><a href="javascript:void(0);">Recently viewed&nbsp;&nbsp;<span></span></a></li>
							<li id="MyModels">
								<div id="modelListBtn" class="my_owned_models" ><a href="javascript:void(0);" id="modelCountBtn">My Models (
									<label id="modelCount">0</label>
									)<span></span></a> </div>
							</li>
							<li id="ItemsCarts" class="Btn_ghostCart ">
								<a class="shoppCart_leftImg" href="/partsdirect/showCart.pd" title="Shopping"><span class="cart"></span> Cart&nbsp;<span id="scCartItemCountHeader"></span> <span></span></a></li>
						</ul>
					</div>
				</div>
				<div class="mani_search_panel">
				<div class="tagline1"><img src="img/homepage/tagline.png" alt="" border="0" title="" /></div>
				<form id="searchBarForm" method="post">
					<input name="pathTaken" id="pathTaken" value="partSearch" type="hidden"/>
					<input name="prst" id="prst" value="0" type="hidden"/>
					<input name="shdMod" id="shdMod" value="" type="hidden"/>
					<input name="shdPart" id="shdPart" value="" type="hidden"/>
					<div class="solar_search_div">
						<div class="phone_menu_tabs">
							<ul>
								<li id="phone_menu_tab_search" class="phone_menu_tab mag_glass_icon"> <a href="#search_input_tab">Search</a></li>
								<li id="phone_menu_tab_manuals" class="phone_menu_tab"><a href="#manuals_help_tab">Manuals & Repair Help</a></li>
							</ul>
						</div>
						<div class="legacy_left_shadow"></div>
						<div class="legacy_search">
							<div class="man_img"></div>
							<div class="search_input_div">
								<div class="input2">
									<input id="searchBarField" class="input_2" type="text" maxlength="100" tabindex="1" value="Enter model or part number" name="searchModPar">
								</div>
								<div class="in">in</div>
								<div class="input3">
									<div id="searchBarSelectArea" class="new_DdArrow">
										<input type="text" name="" class="new_fFinputType" value="Select Type" readonly>
									</div>
									<div id="searchBarDropDown" class="new_popMenu">
										<ul class="selType">
											<li><a class="modelOption" href="javascript:void(0);">Model #</a></li>
											<li><a class="partOption" href="javascript:void(0);">Part #</a></li>
										</ul>
									</div>
								</div>
								<div class="input_btn"> <a id="searchBarGoBtn" href="javascript:void(0);">Search</a> </div>
							</div>
						</div>
						<p class="Our_finder "><span id="changeText">Can't locate your model number? </span><a href="javascript:void(0);" id="newFinderModel">Use our finder<span class="arrow"></span></a></p>
					</div>
					<div class="legacy_right_shadow"></div>
					</div>
				</form>
				<div class="waterfiltertagline">
					<p class="visible-phone visible-tablet">Get <span class="alert-ship">FREE SHIPPING</span> on water filters when you sign up for automatic reorder. <a href="" class="btn">Shop Water Filters</a></p>
					<p class="visible-desktop">Change your water filter every 3-6 months. Get <span class="alert-ship">FREE SHIPPING</span> with reorder. <a href="" class="btn">Shop Water Filters</a></p>
				</div>
			</div>
			<div class="clear"></div>
		</div>
<!-- End From Static -->

<!-- global logo -->
<!-- <cq:include path="/content/searspartsdirect/en/jcr:content/global_logo" resourceType="foundation/components/image" /> -->
<!-- <cq:include path="/content/searspartsdirect/en/jcr:content/global_logo" resourceType="foundation/components/text" /> -->
<!-- <cq:include path="errorCodesTable" resourceType="/apps/searspartsdirect/components/content/errorCodesList" />  -->
<p><spd:getRecentlyViewed/></p>
<p><spd:getMyModels/></p>
<p><spd:getShoppingCart/></p>
<p>include dynamic tags libs along with static html</p>
<cq:include path="commonSymptoms" resourceType="/apps/searspartsdirect/components/content/commonSymptoms" />    
<!-- <cq:include path="errorCodesTable" resourceType="/apps/searspartsdirect/components/content/errorCodesTable" /> -->