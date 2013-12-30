<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getAirFilterDimensions /><%
%>

<H1>Air Filters for Furnace, HVAC, Air Conditioner</H1>
<H3>Air filters when you want them. Plus <span class="freeShipping">FREE SHIPPING*</span></H3>
<p><img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>&nbsp;&nbsp;*You choose how often to get delivery. We'll automatically ship your new filters when you want them. It's that simple. 
    Enjoy FREE SHIPPING on every order that includes an automatic reorder of air filters.<a data-toggle="modal" data-target="#airFilterPromoModal">Promotional Details</a></p>

<H4>To find the right filter, please select your dimensions</H4>

<div class="airfilterDimensionSection  row-fluid">
    <form id="airFilterSelectionForm" method="post" action="">
        <div class="row-fluid">
            <div class="span4">
                <select name="airFilterWidth" id="airFilterWidth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Width (in.)" data-display="true" data-changed="false">
					<c:forEach var="objDimension" items="${airFilterDimensionList.width}">
                        <option value="${objDimension}">${objDimension}</option>
    				</c:forEach>
                </select>
                <span class="dimensionCross hidden-phone">X</span>
            </div>
            <div class="span4">
                <select name="airFilterHeight" id="airFilterHeight" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Height (in.)" data-display="true" data-changed="false">
					<c:forEach items="${airFilterDimensionList.height}" var="objDimension">
                        <option value="${objDimension}">${objDimension}</option>
    				</c:forEach>
                </select>
                <span class="dimensionCross hidden-phone">X</span>
            </div>
            <div class="span4">
                <select name="airFilterDepth" id="airFilterDepth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Depth (in.)" data-display="true" data-changed="false">
                    <c:forEach items="${airFilterDimensionList.length}" var="objDimension">
                        <option value="${objDimension}">${objDimension}</option>
    				</c:forEach>
                </select>
            </div>
        </div>
    </form>
   <div id="afLandingImage" class="span12 hidden-phone"><img src="/etc/designs/searspartsdirect/clientlib_base/img/airFilterDimension.png"/> </div>
   <div id="afLandingMobileImage" class="span12 visible-phone"><img src="/etc/designs/searspartsdirect/clientlib_base/img/airFilterDimension-mobile.png"/> </div>
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
