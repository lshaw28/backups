<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />
<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" categoryName="${productCategoryRelation.trueName}" />
<c:choose>
	<c:when test="${not empty brandRelation}">
		<c:forEach var="item" items="${errorCodeList}">
			<c:if test="${brandRelation.title eq item.key.title}">
				<div class="errorCodesListHeader">
					<c:set var="logoPath" value="${item.key.logoPath}" />
					<c:set var="brandLogo" value="${item.key.brandLogo}" />
					<h4><c:out value="${item.key.title} "/><c:choose>
						<c:when test="${logoPath ne null and logoPath ne '' and (fn:indexOf(logoPath, '.jpg') > -1 or fn:indexOf(logoPath, '.gif') > -1 or fn:indexOf(logoPath, '.png') > -1)}">
							<spd:displayImage path="${item.key.logoPath}" altText="${item.key.title}" decorated="false" />
						</c:when>
						<c:when test="${brandLogo ne ''}">
							<i class="${brandLogo}">&nbsp;</i>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose></h4>
				</div>
				<c:forEach var="errorCodeTable" items="${item.value}">
					<spd:linkResolver value="${errorCodeTable.path}" />
					<div class="errorCodesListItem"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
				</c:forEach>
			</c:if>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${errorCodeList}">
			<div class="errorCodesListHeader">
				<c:set var="logoPath" value="${item.key.logoPath}" />
				<c:set var="brandLogo" value="${item.key.brandLogo}" />
				<h4><c:out value="${item.key.title} "/><c:choose>
					<c:when test="${logoPath ne null and logoPath ne '' and (fn:indexOf(logoPath, '.jpg') > -1 or fn:indexOf(logoPath, '.gif') > -1 or fn:indexOf(logoPath, '.png') > -1)}">
						<spd:displayImage path="${item.key.logoPath}" altText="${item.key.title}" decorated="false" />
					</c:when>
					<c:when test="${brandLogo ne ''}">
						<i class="${brandLogo}">&nbsp;</i>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></h4>
			</div>
			<c:forEach var="errorCodeTable" items="${item.value}">
				<spd:linkResolver value="${errorCodeTable.path}" />
				<div class="errorCodesListItem"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
			</c:forEach>
		</c:forEach>
	</c:otherwise>
</c:choose>