<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:if test="${not empty jobCodeParts}">
	<c:forEach var="part" items="${recommendedParts}">
		<div class="row-fluid">
			<div class="span2">
				<p>Used in this repair <span class="partsfrequency"><c:out value="${part.frequency}" />%</span> of the time.</p>
			</div>
			<div class="span2 categorySymptomImage">
				<c:choose>
					<c:when test="${not empty part.image.url}">
						<img src="${part.image.url}" altText="${part.name}"/>
					</c:when>
					<c:otherwise>
						<img src="${mainSitePath}/partsdirect/assets/img/images/no_part.gif" altText="${part.name}" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="span3">
				<spd:linkResolver value="${mainSitePath}${part.url}"/>
				<p><a href="${url}">${part.name}</a>
					<br />
					PART NUMBER: <c:out value="${part.number}" /></p>
			</div>
			<div class="span4">
				<c:if test="${!part.returnable}">
					<p>This product is not returnable.</p>
				</c:if>
			</div>
			<div class="span4">
				<c:if test="${!part.availableInStore}">
					<p><a href="#">Available online only.</a></p>
				</c:if>
			</div>
			<div class="span8">

				<div class="span5">
					<!--<p>Found in diagram:<br />
						<a href="#">Chassis and enclosures</a></p>-->
				</div>
				<div class="span2">
					<span class="partsPrice">$<c:out value="${part.price}" /></span><br />
					<c:out value="${part.availabilityStatus}" />
				</div>
				<div class="span2">
					<span class="Qty">Qty </span><input type="text" class="addToCartQuantity_js" value="1" />
				</div>
				<div class="span3">
					<button type="button" data-partnumber="${part.number}" data-divid="${part.productGroupId}" data-plsid="${part.supplierId}" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
	</c:forEach>
</c:if>