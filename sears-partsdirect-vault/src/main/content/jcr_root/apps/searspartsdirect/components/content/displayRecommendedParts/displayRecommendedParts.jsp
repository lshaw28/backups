
<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!--  Parts -->

<c:if test="${not empty jobCodeParts}">
	<c:forEach var="jobCode" items="${jobCodes}">

	 	<c:forEach var="part" items="${recommendedParts}">
            <div class="row-fluid">
                <span class="span2"> Used in <br/>this repair <br/><span class="partsfrequency"><c:out value="${part.frequency}" /> %</span><br/> of the time</span>
                    <span class="span2">
                        <c:choose>
					<c:when test="${not empty part.image.url}">
                        <img src="${part.image.url}" altText="${part.name}"/>
                    </c:when>
                    <c:otherwise>
                          <img src="http://www.searspartsdirect.com/partsdirect/assets/img/images/no_part.gif" altText="${part.name}"/>
                    </c:otherwise>
                        </c:choose>
                        </span>
                <span class="span3">

					<spd:linkResolver value="${part.url}"/>
                    <a href="${url}">${part.name} </a> 
                    <br/>
                    PART NUMBER: <c:out value="${part.number}" /></span>
                    <span class="span4">
                        <c:if test="${!part.restriction}">
                            <p>
                                This product is not returnable.
                            </p>
                         </c:if>
                    </span>
                	<span class="span4">

                           <c:if test="${!part.availabilityStatus}">
                            <p>
                                <a href="#.">Available online only</a>
                            </p>
                         </c:if>

                    </span>

                <span class="span2 offset3"> <span class="partsPrice">$<c:out value="${part.price}" /></span><br/> <c:out value="${part.availabilityStatus}" /></span>
                <span class="span1"> Qty <input type="text" class="span6" value="1"/></span>
                <span class="span2"><button type="button" class="btn">Add to Cart</button></span>
			</div>
		</c:forEach>
	</c:forEach>

</c:if>
