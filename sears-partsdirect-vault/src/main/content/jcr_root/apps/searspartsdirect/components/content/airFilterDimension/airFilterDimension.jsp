<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getAirFilterDimensions /><%
%>

<h1>Air Filters for Furnace, HVAC, Air Conditioner</h1>
<h3>Air filters when you want them. Plus <span class="freeShipping">FREE Standard Shipping</span>*</h3>
<i class="truckIcon svg-icon-truck"></i>
<p class="shippingDisclaimer">*You choose how often to get delivery. We'll automatically ship your new filters when you want them. It's that simple. Enjoy FREE SHIPPING on every order that includes an automatic reorder of air filters.</p>
<a class="shippingDisclaimer" data-toggle="modal" data-target="#airFilterPromoModal">Promotional Details</a>



<div class="airfilterDimensionSection  row-fluid">

    <form id="airFilterSelectionForm" method="post" action="">
    <h4>To find the right filter, please select your dimensions</h4>
        <div class="row-fluid">
            <div class="span4">
                <select name="airFilterWidth" id="airFilterWidth" data-toggle="responsive-filter-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Width (in.)" data-display="true" data-changed="false">
                        <option value="8">8</option>
                    <c:forEach items="${airFilterDimensionList.length}" var="objDimension">
                        <option value="${objDimension}">${objDimension}</option>
                    </c:forEach>
                </select>
                <span class="dimensionCross hidden-phone">X</span>
            </div>
            <div class="span4">
                <select name="airFilterHeight" id="airFilterHeight" data-toggle="responsive-filter-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Height (in.)" data-display="true" data-changed="false">
                    <option value="4">4</option>
                    <c:forEach items="${airFilterDimensionList.height}" var="objDimension">
                        <option value="${objDimension}">${objDimension}</option>
    				</c:forEach>
                </select>
                <span class="dimensionCross hidden-phone">X</span>
            </div>
            <div class="span4">
                <select name="airFilterDepth" id="airFilterDepth" data-toggle="responsive-filter-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Depth (in.)" data-display="true" data-changed="false">
                    <option value="14">14</option>
                    <c:forEach items="${airFilterDimensionList.length}" var="objDimension">
                        <option value="${objDimension}">${objDimension}</option>
    				</c:forEach>
                </select>
            </div>
        </div>
    </form>
   <div id="afLandingImage" class="span12 hidden-phone"><img src="/etc/designs/searspartsdirect/clientlib_base/img/airFilterDimension.png"/> </div>
   <div id="afLandingMobileImage" class="span12 visible-phone"><img src="/etc/designs/searspartsdirect/clientlib_base/img/airFilterDimension-mobile.png"/> </div>

    <section id="bestAirFilters" class="filterResults">
        <heading class="setHeading">
            <h2 class="bestHeading setDescription">Best Quality Filters</h2>
            <div class="row-fluid">
                <div class="span6">
                    <p><span class="bulletHeading">MERV ratings:</span> 13-16 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                    <p><span class="bulletHeading">Removes:</span> 95% or more airborne contaminants 0.3 microns or larger</p>
                </div>
                <div class="span6 rightDiv">
                    <p><span class="bulletHeading">Ideal for:</span> The most efficient filtration, suitable for hospitals and healthcare facilities </p>
                    <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                </div>
            </div>
        </heading>
        <ul class="setList">
        </ul>
    </section>

    <section id="betterAirFilters" class="filterResults">
        <heading class="setHeading setDescription">
            <h2 class="betterHeading">Better Quality Filters</h2>
        </heading>
        <ul class="setList">
        </ul>
    </section>

    <section id="goodAirFilters" class="filterResults">
        <heading class="setDescription">
            <h2 class="goodHeading setHeading">Good Quality Filters</h2>
        </heading>
        <ul class="setList">
        </ul>
    </section>

    <div id="noDataFound"><H1>Sorry We didn't found any Filters that match those dimensions</H1></div>
    <spd:resolveHazardTipWarning adhocField="noticeText" choiceField="tipChosen" placeholder=""/>
    <c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder=""/></c:set>
    <c:if test="${empty noticeIcon}"><c:set var="noticeIcon" value="icon-info" /></c:if>
    <cq:include path="/etc/spdAssets/globalConfig/tipPrefix" resourceType="searspartsdirect/components/content/tipPrefix" />
        <div class="toolTip">
            <div class="tipIcon"><i class="${noticeIcon} pull-left icon-3x">&nbsp;</i></div>
            <div class="tipIconText"><em><c:out value="${tipPrefix}" /></em> <c:out value="${htwText}" />The official dimensions of your air filter may be slightly different than the measured length and width. Use the dimensions shown on your filter's label</div>
        </div>	
</div>
<div class="afBrandsRow">
    <div class="powerdBy">Top air filter brands carried by Sears PartsDirect</div>
    <div class="brandImages">
        <img src="/etc/designs/searspartsdirect/clientlib_base/img/brands/png/afBrand1.png"/>
        <img src="/etc/designs/searspartsdirect/clientlib_base/img/brands/png/afBrand2.png"/>
        <img src="/etc/designs/searspartsdirect/clientlib_base/img/brands/png/afBrand3.png"/>
        <img src="/etc/designs/searspartsdirect/clientlib_base/img/brands/png/afBrand4.png" class="fourth_img" />
        <img src="/etc/designs/searspartsdirect/clientlib_base/img/brands/png/afBrand5.png"/>
    </div>
</div>
<p>Did you know that dirty air filters leave particles like dust, mites, pollen and pet dander in the air, which reduces air quality in your home? Dirty air filters also reduce the efficiency of your HVAC system. Sears PartsDirect has top air filter brands such as BMC Air Flow and DuPont for a variety of HVAC systems at great prices. Save energy and improve air quality with replacement furnace filters and air conditioner filters from Sears PartsDirect. Check your owner's manual or enter in your filter dimensions to find the right air filter for your furnace or AC unit.</p>
