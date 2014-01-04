<%@ include file="/apps/searspartsdirect/global.jsp" %><%
    %><%@ page import="com.day.cq.commons.jcr.JcrConstants" %><%
    %><spd:uniqueID /><%
    %>

<div class="airFilterPartDetail">
    <h1 class="partName">Fabreze 20x30x1 Pleated Replacement Air Filter - MERV 12</h1>
    <div class="partNumber">PART #: 8534854 - <span id="packNumber">4</span></div>
    <div class="productDetail">
        <div class="span5">
            <div class="productMainImage">
                <img src="/etc/designs/searspartsdirect/clientlib_base/img/no_part.gif" />
                <div class="plus_minus  hidden-phone">
                    <div class="plus">+</div>
                    <div class="minus">-</div>
                </div>
            </div>
            <div class="print">
                <i class="icon-zoom-in color"></i><a href="#" class="margin20px"> Full Size</a>
                <i class="icon-print color"></i><a href="#" class="margin20px"> Print</a>
            </div>
        </div>

        <div class="span7">
            <div class="price">
                <h2 id="price">$14.98</h2>
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
                <button id="threeMonths" class="packBox wide">Every 3 months</button>
                <button id="sixMonths" class="packBox wide">Every 6 months</button>
                <button id="twelveMonths" class="packBox wide">Every 12 months</button>
            </div>

            <div class="row-fluid pull-left">
                <div class="freeShippingBox">
                    <p>
                        <img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/> Eligible for  <span class="freeSS">FREE Standard Shipping</span> <br /> with Automatic Reorder today!
                        <a data-toggle="modal" data-target="#airFilterPromoModal"> Details</a>
                        <!--<p><i class="svg-icon-truck"></i> Set up automatic reorder and get <span class="freeSS">FREE Standard Shipping</span> today! <a href="#"> Details</a></p>-->
                    </p>
                </div>
                <div id="airFilterPromoModal" class="modal hide fade" role="dialog" aria-labelledby="afpromoModalLabel" aria-hidden="true">
                    <h1>Automatic Reorder Details</h1>
                    <p>Receive free shipping on all automatic reorders with subscription</p>
                    <p>Note: This offer is only valid in the contigious U.S.</p>
                    <div class="pull-right">
                        <button type="button" class="new-btn" data-dismiss="modal" data-cancel="true" aria-hidden="true">Close</button>
                    </div>
                </div>

            <div class="row-fluid">
                <div class="pdpQuantityLine">
                    <label>Qty</label>
                    <input type="text" class="addToCartQuantity_js" value="1" />
                    <button type="button" data-partnumber="partNumber" data-divid="productGroupID" data-plsid="supplierID" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
                </div>
            </div>

            <div class="sameDayShip">
                <i class="icon-truck icon-flip-horizontal"></i> <a>Estimated Delivery Date </a>&#124;<a> Return Policy</a>
            </div>

            <div class="row-fluid shopYourWay">
                <img  class="pull-left" src="/etc/designs/searspartsdirect/clientlib_base/img/shopYourWay.png">
                <p>SHOP YOUR WAY REWARDS&#8480; Members earn <span id="swyPoints">XXX</span> points if they purchase this item. <a href="#">Learn more</a></p>
            </div>

            <div class="partInfo">
                <h3>Part Information</h3>
                <p><a>MERV Rating</a>: 13</p>
                <p>Need help installing your air filter? Read our repair guide, <a href="#">how to replace an HVAC air filter</a></p>
                <h3>Specifications</h3>
                <ul>
                    <li><strong>Category: </strong>Refrigerators and Freezers</li>
                    <li><strong>Shipping Weight: </strong>0.01lbs</li>
                    <li><strong>Shipping Length: </strong>1in</li>
                    <li><strong>Shipping Width: </strong>0.75in</li>
                    <li><strong>Shipping Height: </strong>0.5in</li>
                </ul>
            </div>
        </div>
    </div>
</div>