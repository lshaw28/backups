<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />

<h1>
	<c:if test="${productCategoryRelation != null}">
		<c:out value="${productCategoryRelation.title}" />
	</c:if>&nbsp;
	<cq:text property="errorCodeTitle" placeholder=""/>
</h1>
<cq:text property="errorCodeDescription" placeholder=""/>

<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" categoryName="${productCategoryRelation.trueName}" />
<c:choose>
	<c:when test="${not empty brandRelation}">
		<c:forEach var="item" items="${errorCodeList}">
			<c:if test="${brandRelation.title eq item.key.title}">
						<div class="errorListing-header"><c:out value="${item.key.title}" /><spd:displayImage path="${item.key.logoPath}"/></div>
					<c:forEach var="errorCodeTable" items="${item.value}">
						<spd:linkResolver value="${errorCodeTable.path}" />
							<div class="errorListing-item"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
					</c:forEach>
			</c:if>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${errorCodeList}">
					<div class="errorListing-header">
						<c:out value="${item.key.title}" /> <!-- ${item.key.description}--><spd:displayImage path="${item.key.logoPath}"/>
					</div>
				<c:forEach var="errorCodeTable" items="${item.value}">
					<spd:linkResolver value="${errorCodeTable.path}" />
						<div class="errorListing-item"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></div>
				</c:forEach>
		</c:forEach>
	</c:otherwise>
</c:choose>