<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory" />

<c:if test="${not empty productCategoryRelation}">
	<spd:GetErrorCodesList categoryPath="${productCategoryRelation.path}" />

	<c:if test="${not empty errorCodeList}">
		<p><c:out value="${productCategoryRelation.title}" /> returning an error code?</p>
		<c:set var="numErrorCodes" value="${fn:length(errorCodeList)}" />
		<c:choose>
			<c:when test="${numErrorCodes gt 1}">
				<a href="#">View <c:out value="${productCategoryRelation.title}" /> error code(s)</a>
			</c:when>
			<c:otherwise>
				<a href="#">View <c:out value="${productCategoryRelation.title}" /> error code</a>
			</c:otherwise>
		</c:choose>
	</c:if>
</c:if>