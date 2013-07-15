<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />

<c:if test="${not empty productCategoryRelation}">
	<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" categoryName="${productCategoryRelation.trueName}" />

	<c:if test="${not empty errorCodeList}">
		<c:set var="numErrorCodes" value="${fn:length(errorCodeList)}" />
		<c:set var="catRepairUrl" value="/content/searspartsdirect/en/categories/${productCategoryRelation.trueName}-repair/error-codes.html" />

		<p><c:out value="${productCategoryRelation.title}" /> returning an error code? <a class="new-btn-small" href="${fn:toLowerCase(catRepairUrl)}">
			View <c:out value="${productCategoryRelation.title}" /> error code<c:if test="${numErrorCodes gt 1}">(s)</c:if>
		</a></p>
	</c:if>
</c:if>