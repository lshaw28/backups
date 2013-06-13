<%@ include file="/apps/searspartsdirect/global.jsp" %>
<ul>
	<div class="cartNavShadow"></div>
	<li>
		<div class="btn-group">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Recently Viewed <i class="icon-caret-down">&nbsp;</i></a>
			<ul>
				<li>Dummy item one</li>
				<li>Dummy item two</li>
				<spd:getRecentlyViewed />
				<!-- Model List -->
				<c:forEach var="model" items="${rvModelList}">
					<li>Model <a href="${model.itemURL}">${model.itemName}</a><br />
					<a href="${model.itemURL}">${model.itemDescription}</a></li>
				</c:forEach>
				<!-- Part List -->
				<c:forEach var="part" items="${rvPartList}">
					<li>
						<a href="${part.itemURL}">
						<c:choose>
							<c:when test='${part.itemImageURL != null && part.itemImageURL != "null"}'>
								<img src="${part.itemImageURL}" alt="${part.itemDescription}" />
							</c:when>
							<c:otherwise>
								<img src="/assets/img/images/no_part_100x100.gif" alt="No part image available" />
							</c:otherwise>
						</c:choose>
						${part.itemName}<br /> ${part.itemDescription}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</li>
	<li>
		<div class="btn-group">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">My Models (0) <i class="icon-caret-down">&nbsp;</i></a>
			<ul>
				<li>Dummy item one</li>
				<li>Dummy item two</li>
			</ul>
		</div>
	</li>
	<li>
		<div class="btn-group">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-shopping-cart">&nbsp;</i> Cart <i class="icon-caret-down">&nbsp;</i></a>
			<ul>
				<li>Dummy item one</li>
				<li>Dummy item two</li>
			</ul>
		</div>
	</li>
</ul>