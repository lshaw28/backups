<%@ include file="/apps/searspartsdirect/global.jsp" %>

<ul class="breadcrumb"></ul>
<spd:getAirFilterParts width="5" height="6" length="12"/>
<c:choose>
    <c:when test="${fn:length(airFilterPartList) gt 0}">
        <c:forEach var="part" items="${airFilterPartList}">
        	<div class="row-fluid">
                <div class="span12">
                    <h4><b>${fn:length(airFilterPartList) results for ${width}. X ${height}. X ${length}in.</b></h4> 
                </div>
            </div>

            <div class="result_display_row">
            </div>
            <c:if test="${part.bestAirFilters}">
                <div class="bestAirFilterParts">
                    <div class="row-fluid boxed-callout-af">
                        <div class="span6">
                            <h4>Best Quality Filters</h4>
                            <p><b>MERV Ratings :</b> 13 - 16 <a href="/content/searspartsdirect/en/repair-help.html">Learn about MERV Ratings</a></p>
                            <p><b>Removes : </b> 95% or more airborne contaminants
                                0.3 microns or larger</p>
                        </div>    
                        <div class="span6">
                            <p><b>Ideal For :</b> The most efficient filteration, 
                                suitable for hospitals and  healthcare facilities</p>
                            <p><b>Life : </b> Lasts upto 90 days</p>
                        </div>    
                    </div>

                    <div class="row-fluid partDetailSection">
                        <div class="span1">
                            <img src="${part.imageUrl}" />
                        </div>
                        <div class="span7">
                            <h4><a href="#">${part.partDesc} - Merv {part.mervRating}</a></h4>
                            <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                            <a href="#.">Details</a> </p>
                        </div>

                        <div class="span4 partDetailSectionRight ">
                            <p><b>${part.availablePacks}</b> <span class="packPrice">${part.priceForParts}</span> </p>
                            <p>${part.availablePacks} ${part.priceForParts} </p>
                            <button class="new-btn">Product Details</button>
                        </div>

                    </div>
                </div>
            </c:if>
            <c:if test="${part.betterAirFilters}">
                <div class="betterAirFilterParts">
                    <div class="row-fluid boxed-callout-af">
                        <div class="span6">
                            <h4>Better Quality Filters </h4>
                            <p><b>MERV Ratings :</b> 9 - 12 <a href="/apps/searspartsdirect/components/content/mervRatingHelp/mervRatingHelp.jsp">Learn about MERV Ratings</a></p>
                            <p><b>Removes : </b> upto 89% of airborne contaminants 
                                one microns or larger</p>
                        </div>

                        <div class="span6">
                            <p><b>Ideal For :</b> A very high level of filteration, good for
                                homes prone to allergies or asthma</p>
                            <p><b>Life : </b> Last upto 90 days</p>
                        </div>

                    </div>
                    <div class="row-fluid partDetailSection">
                        <div class="span1">
                            <img src="${part.imageUrl}" />
                        </div>
                        <div class="span7">
                            <h4><a href="#">${part.partDesc} - Merv {part.mervRating}</a></h4>
                            <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                              <a href="#.">Details</a> </p>
                        </div>
                        <div class="span4 partDetailSectionRight ">
                            <p><b>${part.availablePacks}</b> <span class="packPrice">${part.priceForParts}</span> </p>
                            <p>${part.availablePacks} ${part.priceForParts} </p>
                            <button class="new-btn">Product Details</button>
                        </div>
    
                    </div>
                
                </div>
            </c:if>
            <c:if test="${part.goodAirFilters}">
                <div class="goodAirFilterParts">
                    <div class="row-fluid boxed-callout-af">
                        <div class="span6">
                            <h4>Good Quality Filters </h4>
                            <p><b>MERV Ratings :</b> 5 - 8 <a href="/apps/searspartsdirect/components/content/mervRatingHelp/mervRatingHelp.jsp">Learn about MERV Ratings</a></p>
                            <p><b>Removes : </b> Upto 80% of airborne contaminants
                                three microns or larger</p>
                        </div>

                        <div class="span6">
                            <p><b>Ideal For :</b> Efficient air filteration for homes on a 
                                budget, good for homes with miner allergies</p>
                            <p><b>Life : </b> Last upto 90 days</p>
                        </div>
                    </div>

                    <div class="row-fluid partDetailSection">
                        <div class="span1">
                            <img src="${part.imageUrl}" />
                        </div>
                        <div class="span7">
                            <h4><a href="#">${part.partDesc} - Merv {part.mervRating}</a></h4>
                            <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                               <a href="#.">Details</a> </p>
                        </div>
                        <div class="span4 partDetailSectionRight ">
                            <p><b>${part.availablePacks}</b> <span class="packPrice">${part.priceForParts}</span> </p>
                            <p>${part.availablePacks} ${part.priceForParts} </p>
                            <button class="new-btn">Product Details</button>
                        </div>

                    </div>
                </div>
            </c:if>
        </c:forEach>
	</c:when>
    <c:otherwise>
        <p>No response found </p> 
    </c:otherwise>
</c:choose>