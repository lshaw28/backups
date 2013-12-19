<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%>
<spd:getAirFilterParts width="5" height="6" length="12" />
<c:choose>
    <c:when test="${fn:length(airFilterPartList) gt 0}">
        <c:forEach var="objPart" items="${airFilterPartList}">
        	<div class="row-fluid">
                <div class="span12">
                    <h4><b>${fn:length(airFilterPartList)} results for ${width}. X ${height}. X ${length}in.</b>${objPart.mervRating}</h4> 
                </div>
            </div>
            <div class="result_display_row"></div>
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
                            <img src="${objPart.imageUrl}" />
                        </div>
                        <div class="span7">
                            <h4><a href="#">${objPart.partDesc} - Merv ${objPart.mervRating}</a></h4>
                            <p class="span7 eligiblePartDetail"> <img src="dummyURL/test.png"/>Elegible for <span class="freeStandardShipping">FREE Standard Shipping</span>
                            <a href="#.">Details</a> </p>
                        </div>
						<div class="span4 partDetailSectionRight ">
                            <p><b>${objPart.availablePacks}</b> <span class="packPrice">${objPart.priceForParts}</span> </p>
                            <p>${objPart.availablePacks} ${objPart.priceForParts} </p>
                            <button class="new-btn">Product Details</button>
                        </div>
					</div>
                </div>
		</c:forEach>
	</c:when>
    <c:otherwise>
        <p>No response found </p> 
    </c:otherwise>
</c:choose>