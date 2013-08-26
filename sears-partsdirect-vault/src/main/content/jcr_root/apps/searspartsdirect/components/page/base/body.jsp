<%@ include file="/apps/searspartsdirect/global.jsp"%>
<c:choose>
	<c:when test="${isEditMode}">
		<c:set var="cqCssClass" value="edit_cq" />
	</c:when>
	<c:when test="${isDesignMode}">
		<c:set var="cqCssClass" value="design_cq" />
	</c:when>
	<c:when test="${isPreviewMode}">
		<c:set var="cqCssClass" value="preview_cq" />
	</c:when>
	<c:otherwise>
		<c:set var="cqCssClass" value="publish_cq" />
	</c:otherwise>
</c:choose>
<c:set var="pageClientLib"><cq:text property="pageClientLib" placeholder="" /></c:set><body class="<cq:text property="pageCssClassName" placeholder="" /> ${cqCssClass}">
	<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext" />
	<a name="brandBar"></a>
	<cq:include path="brandBar" resourceType="/apps/searspartsdirect/components/base/brandBar" />
	<div id="viewport">
		<div role="main" class="container-fluid">
			<cq:include script="header.jsp" />
			<cq:include script="content.jsp" />
			<cq:include script="footer.jsp" />
			<cq:include script="jsbody.jsp" />
		</div>
	</div>
	<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents" />
	<cq:include path="loginModal" resourceType="searspartsdirect/components/base/loginModal" />
	<cq:include path="registerModal" resourceType="searspartsdirect/components/base/registerModal" />
    <cq:include path="forgotPasswordModal" resourceType="searspartsdirect/components/base/forgotPasswordModal" />
    <div id="addToCartAnimation" class="addToCartAnimation">
        <span><i class="icon-shopping-cart">&nbsp;</i>Added to cart</span>
    </div>
</body>