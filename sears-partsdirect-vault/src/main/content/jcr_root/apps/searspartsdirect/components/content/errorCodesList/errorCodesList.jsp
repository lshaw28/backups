<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />

<h1>
	<c:if test="${not empty productCategoryRelation}">
		<c:out value="${productCategoryRelation.title}" />
		&nbsp;Error Codes
	</c:if>
</h1>
<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" categoryName="${productCategoryRelation.trueName}" />
<c:choose>
	<c:when test="${not empty brandRelation}">
		<c:forEach var="item" items="${errorCodeList}">
			<c:if test="${brandRelation.title eq item.key.title}">
				<div class="errorListing-header row-fluid">
                    <div class="new-span-five-sixths">
                        <h4>${item.key.title}</h4>
                    </div>
                    <div class="new-span-sixth">
                        <spd:displayImage path="${item.key.logoPath}" altText="${item.key.title}"/>
                    </div>
                 </div>
				<c:forEach var="errorCodeTable" items="${item.value}">
					<spd:linkResolver value="${errorCodeTable.path}" />
					<div class="errorListing-item"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
				</c:forEach>
			</c:if>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${errorCodeList}">
			<div class="errorListing-header row-fluid">
                <div class="new-span-five-sixths">
                    <h4>${item.key.title}</h4>
                </div>
                <div class="new-span-sixth">
				    <spd:displayImage path="${item.key.logoPath}" altText="${item.key.title}"/>
                </div>
			</div>
			<c:forEach var="errorCodeTable" items="${item.value}">
				<spd:linkResolver value="${errorCodeTable.path}" />
				<div class="errorListing-item"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
			</c:forEach>
		</c:forEach>
	</c:otherwise>
</c:choose>