<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory" />

<c:if test="${not empty productCategoryRelation}">
	<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" />

	<c:if test="${not empty errorCodeList}">
		<p><c:out value="${productCategoryRelation.title}" /> returning an error code?</p>

		<c:set var="numErrorCodes" value="${fn:length(errorCodeList)}" />
		<c:set var="catRepairUrl" value="/content/searspartsdirect/en/${productCategoryRelation.title}-repair/error-codes.html" />

		<a href="${fn:toLowerCase(catRepairUrl)}">
			View <c:out value="${productCategoryRelation.title}" /> error code<c:if test="${numErrorCodes gt 1}">(s)</c:if>
		</a>
	</c:if>
</c:if>

