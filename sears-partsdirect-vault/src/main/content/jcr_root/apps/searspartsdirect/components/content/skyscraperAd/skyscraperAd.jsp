<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation assetType="productCategory" single="true" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<c:if test="${not empty productCategoryRelation}">
	<div class="responsiveImage">
		<spd:getImagePathTag resourcePath="${productCategoryRelation.path}" />
		
		<div data-desktopimage="${desktopImage}" data-tabletimage="${tabletImage}" data-mobileimage="${mobileImage}" data-width="${displayWidth}" data-height="${displayHeight}" data-linkalt="${linkAlt}" data-linkurl="${linkURL}" data-linktarget="${linkTarget}"></div>
		
		<c:if test="${imageCaption ne ''}">
			<p>${imageCaption}</p>
		</c:if>
	</div>
</c:if>