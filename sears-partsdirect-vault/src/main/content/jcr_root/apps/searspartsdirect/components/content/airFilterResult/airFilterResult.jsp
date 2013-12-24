<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%>
<spd:getAirFilterParts width="12" height="12" length="12" />
<c:choose>
    <c:when test="${airFilterPartList != null}">
        <c:if test="${airFilterPartList.bestAirFilters.quality == 'Best'}">
            <div class="row-fluid">
                <div class="span12">
                    <h4><b>${fn:length(airFilterPartList.bestAirFilters.part)} results for ${width}. X ${height}. X ${length}in.</b></h4> 
                </div>
            </div>
            <div class="row-fluid boxed-callout-af">
                <div class="span6">
                    <h4>Best Quality Filters</h4>
                    <p><b>MERV Ratings :</b> 13 - 16 <a href="/content/searspartsdirect/en/merv-rating.html">Learn about MERV Ratings</a></p>
                    <p><b>Removes : </b> 95% or more airborne contaminants
                        0.3 microns or larger</p>
                </div>    
                <div class="span6">
                    <p><b>Ideal For :</b> The most efficient filteration, 
                        suitable for hospitals and  healthcare facilities</p>
                    <p><b>Life : </b> Lasts upto 90 days</p>
                </div>    
            </div>
            <c:forEach var="objPart" items="${airFilterPartList.bestAirFilters.part}">
                <div class="result_display_row"></div>
                    <div class="bestAirFilterParts">
                        <div class="row-fluid partDetailSection">
                            <div class="span1">
                                <img src="${objPart.imageUrl}" />
                            </div>
                            <div class="span7">
                                <h4><a href="#">${objPart.partDesc} - Merv ${objPart.mervRating}</a></h4>
                                <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                                <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a> </p>
                            </div>
                            <div class="span4 partDetailSectionRight ">
                                <p><b>${objPart.availablePacks}</b> <span class="packPrice">${objPart.priceForParts}</span> </p>
                                <button class="new-btn">Product Details</button>
                            </div>
                        </div>
                    </div>
            </c:forEach>
        </c:if>

        <c:if test="${airFilterPartList.betterAirFilters.quality == 'Better'}">
            <div class="row-fluid">
                <div class="span12">
                    <h4><b>${fn:length(airFilterPartList.betterAirFilters.part)} results for ${width}. X ${height}. X ${length}in.</b></h4> 
                </div>
            </div>
            <div class="row-fluid boxed-callout-af">
                <div class="span6">
                    <h4>Better Quality Filters</h4>
                    <p><b>MERV Ratings :</b> 13 - 16 <a href="/content/searspartsdirect/en/merv-rating.html">Learn about MERV Ratings</a></p>
                    <p><b>Removes : </b> Upto 89% of airborne contaminants 
                        one micron or larger</p>
                </div>    
                <div class="span6">
                    <p><b>Ideal For :</b> A very high level of filtertion, 
                        good for homes prone to allergies or asthma</p>
                    <p><b>Life : </b> Lasts upto 90 days</p>
                </div>    
            </div>
            <c:forEach var="objPart" items="${airFilterPartList.betterAirFilters.part}">
                <div class="result_display_row"></div>
                    <div class="bestAirFilterParts">
                        <div class="row-fluid partDetailSection">
                            <div class="span1">
                                <img src="${objPart.imageUrl}" />
                            </div>
                            <div class="span7">
                                <h4><a href="#">${objPart.partDesc} - Merv ${objPart.mervRating}</a></h4>
                                <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                                <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a> </p>
                            </div>
                            <div class="span4 partDetailSectionRight ">
                                <p><b>${objPart.availablePacks}</b> <span class="packPrice">${objPart.priceForParts}</span> </p>
                                <button class="new-btn">Product Details</button>
                            </div>
                        </div>
                    </div>
            </c:forEach>
        </c:if>

        <c:if test="${airFilterPartList.goodAirFilters.quality == 'Good'}">
            <div class="row-fluid">
                <div class="span12">
                    <h4><b>${fn:length(airFilterPartList.goodAirFilters.part)} results for ${width}. X ${height}. X ${length}in.</b></h4> 
                </div>
            </div>
            <div class="row-fluid boxed-callout-af">
                <div class="span6">
                    <h4>Good Quality Filters</h4>
                    <p><b>MERV Ratings :</b> 13 - 16 <a href="/content/searspartsdirect/en/merv-rating.html">Learn about MERV Ratings</a></p>
                    <p><b>Removes : </b> Upto 80% contaminants 
                        three microns or larger</p>
                </div>    
                <div class="span6">
                    <p><b>Ideal For :</b> Efficient air filteration for homes on a budget, 
                        good for homes on minor allergies</p>
                    <p><b>Life : </b> Lasts upto 90 days</p>
                </div>    
            </div>
            <c:forEach var="objPart" items="${airFilterPartList.goodAirFilters.part}">
                <div class="result_display_row"></div>
                    <div class="bestAirFilterParts">
                        <div class="row-fluid partDetailSection">
                            <div class="span1">
                                <img src="${objPart.imageUrl}" />
                            </div>
                            <div class="span7">
                                <h4><a href="#">${objPart.partDesc} - Merv ${objPart.mervRating}</a></h4>
                                <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                                <a data-toggle="modal" data-target="#airFilterPromoModal">Details</a> </p>
                            </div>
                            <div class="span4 partDetailSectionRight ">
                                <p><b>${objPart.availablePacks}</b> <span class="packPrice">${objPart.priceForParts}</span> </p>
                                <button class="new-btn">Product Details</button>
                            </div>
                        </div>
                    </div>
            </c:forEach>
        </c:if>
    </c:when>
    <c:otherwise>
       <div id="noDataFound"><H1>Sorry We didn't find any Filters that match those dimensions</H1></div>
    	<cq:include path="instructionsParsys" resourceType="foundation/components/parsys" />
    </c:otherwise>
</c:choose>

