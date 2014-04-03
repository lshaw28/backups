<%@ include file="/apps/searspartsdirect/global.jsp" %>

<article id="content" data-templatename="Basepage">
    <div class="articleFix">
        <div class="row-fluid">
            <div class="span10 desktop-offset1">
                <div class="parsys">

                    <div class="airFilterPartDetails">
                        <h1 class="partName"></h1>
                        <div class="partNumber">Part #: <span id="partNumber"></span> - <span id="packNumber"></span></div>
                        <div class="productDetail">
                            <div class="span5">
                                <cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
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
                            </div><!-- /span5 -->

                            <div class="span7">
                                <div class="price row-fluid pull-left">
                                    <h2 id="price" class="price"></h2>
                                    <p id="inStock" class="inStock">In stock</p>
                                </div>

                                <p class="packOptions">Choose your pack size</p>
                                <div class="row-fluid">
                                    <button id="fourPack" class="packBox active">4 Pack</button>
                                    <button id="sixPack" class="packBox">6 Pack</button>
                                    <button id="twelvePack" class="packBox">12 Pack</button>
                                </div>

                                <p class="packOptions">How often would you like to receive this product?</p>
                                <div class="row-fluid">
                                    <button id="oneTime" class="packBox wide active">One-time order</button>
                                    <button id="threeMonths" class="packBox wide js_automatic" data-subper="3">Every 3 months</button>
                                    <button id="sixMonths" class="packBox wide js_automatic" data-subper="6">Every 6 months</button>
                                    <button id="twelveMonths" class="packBox wide js_automatic" data-subper="12">Every 12 months</button>
                                </div>

                                <div class="row-fluid">
                                    <div class="freeShippingBox">
                                        <p id="js_getFreeShipping">
                                            <img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>
                                            Set up automatic reorder and get <br />
                                            <span class="freeSS">FREE Standard Shipping</span> today!
                                            <a data-toggle="modal" data-target="#airFilterPromoModal"> Details</a>
                                        </p>
                                        <p id="js_hasFreeShipping" class="hide">
                                            <img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>
                                            Enjoy <span class="freeSS">FREE Standard Shipping</span> today!
                                            <a data-toggle="modal" data-target="#airFilterPromoModal"> Details</a>
                                        </p>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="pdpQuantityLine">
                                        <label>Qty</label>
                                        <input type="text" class="addToCartQuantity_js" value="1" maxlength="3" />
                                        <button type="button" id="addFilterToCart" data-partnumber="partNumber" data-divid="productGroupID" data-plsid="supplierID" data-subper=0 data-location="Part Detail Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
                                    </div>
                                </div>

                                <div class="row-fluid sameDayShip">
                                    <i class="icon-truck icon-flip-horizontal"></i> <a data-toggle="modal" data-target="#estimatedDeliveryModal">Estimated Delivery Date </a>&#124;<a data-toggle="modal" data-target="#returnPolicyModal"> Return Policy</a>
                                </div>

                                <div class="row-fluid shopYourWay">
                                    <img  class="pull-left" src="/etc/designs/searspartsdirect/clientlib_base/img/shopYourWay.png">
                                    <p>SHOP YOUR WAY REWARDS<sup>SM</sup> Members earn <span id="swyPoints"></span> points if they purchase this item. <a data-toggle="modal" data-target="#shopYourWayModal"> Learn more</a></p>
                                </div>

                                <div class="partInfo">
                                    <h3 class="partInfoHeading">Part Information</h3>
                                    <p><a href="/replacement-parts/hvac-air-filters/merv-rating.html">MERV Rating</a>: <span id="mervRating">13</span></p>
                                     <!-- <p>Need help installing your air filter? Read our repair guide, <a href="#">how to replace an HVAC air filter</a></p> -->
                                    <h3 class="partInfoHeading">Specifications</h3>
                                    <ul>
                                        <li><span class="bold">Category: </span><span id="filterCat">Refrigerators and Freezers</span></li>
                                        <li><span class="bold">Shipping Weight: </span><span id="shippingWeight">0.01</span> lbs</li>
                                        <li><span class="bold">Shipping Length: </span><span id="shippingLength">1</span> in</li>
                                        <li><span class="bold">Shipping Width: </span><span id="shippingWidth">0.75</span> in</li>
                                        <li><span class="bold">Shipping Height: </span><span id="shippingHeight">0.5</span> in</li>
                                    </ul>
                                </div>

                            </div><!-- /span7 -->
                        </div><!-- /productDetail -->
                    </div><!-- /airFilterPartDetails -->

                </div><!-- /parsys -->
            </div><!-- /span10 desktop-offset1 -->
        </div><!-- /row-fluid -->
    </div><!-- /articleFix -->
 </article>


