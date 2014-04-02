
<!-- Story 7812 (Part Detail compoent), -->

<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%
    %><%@ page import="com.day.cq.commons.jcr.JcrConstants"%>
<cq:includeClientLib
	categories="apps.searspartsdirect,apps.searspartsdirect.base" />
<% 
    
    String productGroupId=(request.getParameter("productGroupId") != null) ? request.getParameter("productGroupId") : "";
String supplierId=(request.getParameter("supplierId") != null) ? request.getParameter("supplierId") : "";
String partNumber=(request.getParameter("partNumber") != null) ? request.getParameter("partNumber") : "";
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String brandName = (request.getParameter("brandName") != null) ? request.getParameter("brandName") : "";
String modelDescription = (request.getParameter("modelDescription") != null) ? request.getParameter("modelDescription") : "";



%>
<cq:includeClientLib
	categories="apps.searspartsdirect,apps.searspartsdirect.base" />
<div class="partDetails">
	<%@include
		file="/apps/searspartsdirect/components/content/headerPD/headerPD.jsp"%>
	<h1 class="partName"></h1>
	<div class="partNumber">
		Part Number: <span id="partNumber"></span>
		<div class="productDetail">
			<div class="span5">

				<cq:include path="responsivePinchImage"
					resourceType="searspartsdirect/components/content/responsivePinchImage" />
				<!--<div class="productMainImage">
<img src="/etc/designs/searspartsdirect/clientlib_base/img/no_part.gif" />
<div class="plus_minus  hidden-phone">
<div class="plus">+</div>
<div class="minus">-</div>
</div>
</div>
<div class="print">
<i class="icon-zoom-in color"></i><a href="#" class="margin20px"> Full Size</a>
<i class="icon-print color"></i><a href="#" class="margin20px"> Print</a>
</div>-->
			</div>

			<div class="span7">
				<div class="price row-fluid pull-left">
					<p id="refundStatus"></p>
					<h2 id="price" class="price"></h2>
					<p id="inStock" class="inStock"></p>
					<p id="onlineAvl"></p>
				</div>

				<p class="packOptions">Choose your pack size</p>
				<div class="row-fluid">
					<button id="fourPack" class="packBox active">4 Pack</button>
					<button id="sixPack" class="packBox">6 Pack</button>
					<button id="twelvePack" class="packBox">12 Pack</button>
				</div>

				<p class="packOptions">How often would you like to receive this
					product?</p>
				<div class="row-fluid">
					<button id="oneTime" class="packBox wide active">One-time
						order</button>
					<button id="threeMonths" class="packBox wide">Every 3
						months</button>
					<button id="sixMonths" class="packBox wide">Every 6 months</button>
					<button id="twelveMonths" class="packBox wide">Every 12
						months</button>
				</div>

				<div class="row-fluid">
					<div class="freeShippingBox">
						<p>
							<img
								src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png" />
							Set up automatic reorder and get <br /> <span class="freeSS">FREE
								Standard Shipping</span> today! <a> <img id="partImage" /></a>
							<!--<p><i class="svg-icon-truck"></i>  Eligible for  <span class="freeSS">FREE Standard Shipping</span> <br /> with Automatic Reorder today! <a href="#"> Details</a></p>-->
						</p>
					</div>
				</div>


				<div class="row-fluid">
					<div class="pdpQuantityLine">
						<label>Qty</label> <input type="text" class="addToCartQuantity_js"
							value="1" onfocus="this.value = '';" />
						<button type="button" id="addFilterToCart"
							data-partnumber="partNumber" data-divid="productGroupID"
							data-plsid="supplierID" data-subper=0
							class="new-btn new-btn-search addToCart_js">Add to Cart</button>
					</div>
				</div>

				<div class="row-fluid sameDayShip">
					<i class="icon-truck icon-flip-horizontal"></i> <a
						data-toggle="modal" data-target="#estimatedDeliveryModal">Estimated
						Delivery Date </a>&#124;<a data-toggle="modal"
						data-target="#returnPolicyModal"> Return Policy</a>
				</div>

				<div class="row-fluid shopYourWay">
					<img class="pull-left"
						src="/etc/designs/searspartsdirect/clientlib_base/img/shopYourWay.png">
					<p>
						SHOP YOUR WAY REWARDS&#8480; Members earn <span id="swyPoints">XXX</span>
						points if they purchase this item. <a data-toggle="modal"
							data-target="#shopYourWayModal"> Learn more</a>
					</p>
				</div>
				<!-- bundle block -->
				<div id="zoom">
                <cq:include path="bundleZoom" resourceType="searspartsdirect/components/content/bundleZoom" />
                </div>
				<!-- end bundle block-->
				<div class="partInfo">
					<h3 class="partInfoHeading">Part Information</h3>
					<p id="featureCopyVal"></p>
					<!-- <p>Need help installing your air filter? Read our repair guide, <a href="#">how to replace an HVAC air filter</a></p> -->
					<h3 class="partInfoHeading">Specifications</h3>
					<ul>
						<li><span class="bold">Category: </span><span id="filterCat">Refrigerators
								and Freezers</span></li>
						<li><span class="bold">Shipping Weight: </span><span
							id="shippingWeight"></span> lbs</li>
						<li><span class="bold">Shipping Length: </span><span
							id="shippingLength"></span> in</li>
						<li><span class="bold">Shipping Width: </span><span
							id="shippingWidth"></span> in</li>
						<li><span class="bold">Shipping Height: </span><span
							id="shippingHeight"></span> in</li>
					</ul>
				</div>
			</div>
		</div>

		<div>

			<div id="commonPairs"></div>
			<div />
		</div>
		<script>
            partDetail('<%=productGroupId%>','<%=supplierId%>','<%=partNumber%>');
		</script>