<%@ include file="/apps/searspartsdirect/global.jsp" %><%
    %><spd:getAirFilterParts width="8" height="24" length="14" /><%
    %>

<c:choose>
    <c:when test="${airFilterPartList != null}">
        <div class="airFilterSearchResult">
            <div class="totalProductCount">${fn:length(airFilterPartList.bestAirFilters.part)+fn:length(airFilterPartList.betterAirFilters.part)+fn:length(airFilterPartList.goodAirFilters.part)} results for ${width}. X ${height}. X ${length}in.</div>
            <div class="hidden-phone">
                <c:if test="${airFilterPartList.bestAirFilters.quality == 'Best'}">
                    <div class="typeOfAirFilterSearch">
                        <div class="AirFilterHeading"><h2><img src="/etc/designs/searspartsdirect/clientlib_base/img/BestQualityFiltersIcon.png"  height="30" width="30" />Best Quality Filters</h2></div>
                        <div>
                            <div class="span6">
                                <p><span class="bulletHeading">MERV ratings:</span> 13-16 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                <p><span class="bulletHeading">Removes:</span> 95% or more airborne contaminants 0.3 microns or larger</p>
                            </div>
                            <div class="span6 rightDiv">
                                <p><span class="bulletHeading">Ideal for:</span> The most efficient filtration, suitable for hospitals and healthcare facilities </p>
                                <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="objPart" items="${airFilterPartList.bestAirFilters.part}">
                        <div class="airFilterPorductRow">
                            <c:choose>
                                <c:when test="${objPart.imageUrl != null}">
                                    <div class="span2 productImage"><img src="${objPart.imageUrl}" /></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_desktop.png" /></div>
                                </c:otherwise>
                            </c:choose>
                            <div class="span6">
                                <div class="titleAnchor"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                <div class="freeShippingBox"><img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>&nbsp;&nbsp;Eligible for <span class="freeSS">FREE Standard Shipping</span> <br />with Automatic Reorder today! <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a></div>
                            </div>
                            <c:choose>
                                <c:when test="${not empty objPart.availablePacksList}">
                                    <div class="span4 productPrice">
                                        <c:forEach var="pack" items="${objPart.availablePacksList}">
                                            <p><strong>${pack.packs}-pack <span class="price">${pack.price}</span></strong></p>
                                            <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span4 productPrice">
                                        <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${airFilterPartList.betterAirFilters.quality == 'Better'}">
                    <div class="typeOfAirFilterSearch">
                        <div class="AirFilterHeading"><h2><img src="/etc/designs/searspartsdirect/clientlib_base/img/BetterQualityFiltersIcon.png"  height="30" width="30" />Better Quality Filters</h2></div>
                        <div>
                            <div class="span6">
                                <p><span class="bulletHeading">MERV ratings:</span> 9-12 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                <p><span class="bulletHeading">Removes:</span> Up to 89% of airborne contaminants one micron or larger</p>
                            </div>
                            <div class="span6 rightDiv">
                                <p><span class="bulletHeading">Ideal for:</span> A very high level of filtration, good for homes prone to allergies or asthma</p>
                                <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="objPart" items="${airFilterPartList.betterAirFilters.part}">
                        <div class="airFilterPorductRow">
                            <c:choose>
                                <c:when test="${objPart.imageUrl != null}">
                                    <div class="span2 productImage"><img src="${objPart.imageUrl}" /></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_desktop.png" /></div>
                                </c:otherwise>
                            </c:choose>
                            <div class="span6">
                                <div class="titleAnchor"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                <div class="freeShippingBox"><img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>&nbsp;&nbsp;Eligible for <span class="freeSS">FREE Standard Shipping</span> <br />with Automatic Reorder today! <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a></div>
                            </div>
                            <c:choose>
                                <c:when test="${not empty objPart.availablePacksList}">
                                    <div class="span4 productPrice">
                                        <c:forEach var="pack" items="${objPart.availablePacksList}">
                                            <p><strong>${pack.packs}-pack <span class="price">${pack.price}</span></strong></p>
                                            <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span4 productPrice">
                                        <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${airFilterPartList.goodAirFilters.quality == 'Good'}">
                    <div class="typeOfAirFilterSearch">
                        <div class="AirFilterHeading"><h2><img src="/etc/designs/searspartsdirect/clientlib_base/img/GoodQualityFiltersIcon.png"  height="30" width="30" />Good Quality Filters</h2></div>
                        <div>
                            <div class="span6">
                                <p><span class="bulletHeading">MERV ratings:</span> 5-8 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                <p><span class="bulletHeading">Removes:</span> Up to 80% of airborne contaminants three microns or larger</p>
                            </div>
                            <div class="span6 rightDiv">
                                <p><span class="bulletHeading">Ideal for:</span> Efficient air filtration for homes on a budget, good for homes with minor allergies </p>
                                <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="objPart" items="${airFilterPartList.goodAirFilters.part}">
                        <div class="airFilterPorductRow">
                            <c:choose>
                                <c:when test="${objPart.imageUrl != null}">
                                    <div class="span2 productImage"><img src="${objPart.imageUrl}" /></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_desktop.png" /></div>
                                </c:otherwise>
                            </c:choose>
                            <div class="span6">
                                <div class="titleAnchor"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                <div class="freeShippingBox"><img src="/etc/designs/searspartsdirect/clientlib_base/img/freeShippingPromoIcon.png"/>&nbsp;&nbsp;Eligible for <span class="freeSS">FREE Standard Shipping</span> <br />with Automatic Reorder today! <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a></div>
                            </div>
                            <c:choose>
                                <c:when test="${not empty objPart.availablePacksList}">
                                    <div class="span4 productPrice">
                                        <c:forEach var="pack" items="${objPart.availablePacksList}">
                                            <p><strong>${pack.packs}-pack <span class="price">${pack.price}</span></strong></p>
                                            <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span4 productPrice">
                                        <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        <p><a href="#" class="ProductDetailsAnchor">Product Details</a></p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="visible-phone">
                <div class="accordion" id="accordion2">
                    <c:if test="${airFilterPartList.bestAirFilters.quality == 'Best'}">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                                    <h2 class="AirFilterHeading_mobile"><img src="/etc/designs/searspartsdirect/clientlib_base/img/BestQualityFiltersIcon.png"  height="29" width="26" />Better Quality Filters<i class="icon-chevron-up"></i><i class="icon-chevron-down"></i></h2>
                                </a>
                            </div>
                            <div class="accordion-body collapse in row-fluid" id="collapseOne">
                                <div class="accordion-inner">
                                    <p><span class="bulletHeading">MERV ratings:</span> 9-12 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                    <p><span class="bulletHeading">Removes:</span> Up to 89% of airborne contaminants one micron or larger</p>
                                    <p><span class="bulletHeading">Ideal for:</span>  A very high level of filtration, good for homes prone to allergies or asthma </p>
                                    <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                                </div>
                            </div>
                        </div>
                        <c:forEach var="objPart" items="${airFilterPartList.bestAirFilters.part}">
                            <div class="airFilterPorductRow_mobile">
                                <c:choose>
                                    <c:when test="${objPart.imageUrl != null}">
                                        <div class="productImage_mobile"><img src="${objPart.imageUrl}" /></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_mobile.png" /></div>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${not empty objPart.availablePacksList}">
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${airFilterPartList.betterAirFilters.quality == 'Better'}">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                                    <h2 class="AirFilterHeading_mobile"><img src="/etc/designs/searspartsdirect/clientlib_base/img/BetterQualityFiltersIcon.png"  height="29" width="26" />Better Quality Filters<i class="icon-chevron-up"></i><i class="icon-chevron-down"></i></h2>
                                </a>
                            </div>
                            <div class="accordion-body collapse in row-fluid" id="collapseTwo">
                                <div class="accordion-inner">
                                    <p><span class="bulletHeading">MERV ratings:</span> 9-12 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                    <p><span class="bulletHeading">Removes:</span> Up to 89% of airborne contaminants one micron or larger</p>
                                    <p><span class="bulletHeading">Ideal for:</span>  A very high level of filtration, good for homes prone to allergies or asthma </p>
                                    <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                                </div>
                            </div>
                        </div>  
                        <c:forEach var="objPart" items="${airFilterPartList.betterAirFilters.part}">
                            <div class="airFilterPorductRow_mobile">
                                <c:choose>
                                    <c:when test="${objPart.imageUrl != null}">
                                        <div class="productImage_mobile"><img src="${objPart.imageUrl}" /></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_mobile.png" /></div>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${not empty objPart.availablePacksList}">
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${airFilterPartList.goodAirFilters.quality == 'Good'}">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                                    <h2 class="AirFilterHeading_mobile"><img src="/etc/designs/searspartsdirect/clientlib_base/img/GoodQualityFiltersIcon.png"  height="29" width="26" />Good Quality Filters<i class="icon-chevron-up"></i><i class="icon-chevron-down"></i></h2>
                                </a>
                            </div>
                            <div class="accordion-body collapse in row-fluid" id="collapseThree">
                                <div class="accordion-inner">
                                    <p><span class="bulletHeading">MERV ratings:</span> 5-8 <a href="/content/searspartsdirect/en/merv-rating.html" class="smallFont" target="_blank">Learn about MERV ratings</a></p>
                                    <p><span class="bulletHeading">Removes:</span> Up to 80% of airborne contaminants three microns or larger</p>
                                    <p><span class="bulletHeading">Ideal for:</span>  Efficient air filtration for homes on a budget, good for homes with minor allergies </p>
                                    <p><span class="bulletHeading">Life:</span> Lasts up to 90 days</p>
                                </div>
                            </div>
                        </div>
                        <c:forEach var="objPart" items="${airFilterPartList.goodAirFilters.part}">
                            <div class="airFilterPorductRow_mobile">
                                <c:choose>
                                    <c:when test="${objPart.imageUrl != null}">
                                        <div class="productImage_mobile"><img src="${objPart.imageUrl}" /></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="span2 productImage"><img src="/etc/designs/searspartsdirect/clientlib_base/img/NoImage_mobile.png" /></div>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${not empty objPart.availablePacksList}">
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="product_details">
                                            <div class="titleAnchor_mobile"><a href="#">${objPart.basePartNumber} - Merv ${objPart.mervRating}</a></div>
                                            <p><strong>${objPart.availablePacks}-pack <span class="price">${objPart.priceForParts}</span></strong></p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="noDataFound"><H1>Sorry We didn't find any Filters that match those dimensions mansi</H1></div>
        <cq:include path="instructionsParsys" resourceType="foundation/components/parsys" />
    </c:otherwise>
</c:choose>