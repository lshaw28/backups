<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>

<c:if test="${not empty productCategoryRelation}">
	<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" categoryName="${productCategoryRelation.trueName}" />

	<c:if test="${not empty errorCodeList}">
		<c:set var="numErrorCodes" value="${fn:length(errorCodeList)}" />
		<c:set var="catRepairUrl" value="/content/searspartsdirect/en/categories/${productCategoryRelation.trueName}-repair/error-codes.html" />
		<p><span class="primary-content">
			<span class="icon-container">
				<i class="svg-icon-er"></i>
				<span class="divider">&nbsp;</span>
			</span> <c:out value="${productCategoryRelation.title}" /> returning an error code?</span><a class="new-btn" href="${fn:toLowerCase(catRepairUrl)}">View <c:out value="${productCategoryRelation.title}" /> Error Code<c:if test="${numErrorCodes gt 1}">s</c:if></a></p>
	</c:if>
</c:if>