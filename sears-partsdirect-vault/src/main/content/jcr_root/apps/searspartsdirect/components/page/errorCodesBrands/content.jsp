<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span12">
			<spd:getRelation single="true" assetType="productCategory"/>
			<spd:getRelation single="true" assetType="brand"/>
			<spd:tagsByPage tagType="subcategories"/>
			<h3><c:if test="${brandRelation != null}">${brandRelation.title}&nbsp;</c:if>
				<c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}&nbsp;</c:if>
				<c:if test="${fn:length(subcategoriesList) eq 1}">
						${subcategoriesList[0].title}
						<c:set var="subCatUrl" value="${subcategoriesList[0].tagID}"/>
				</c:if>
				<cq:include path="tableHeader" resourceType="/apps/searspartsdirect/components/contents/text" />
			</h3>
			<cq:include path="tableDescription" resourceType="/apps/searspartsdirect/components/contents/text" />
			
			<cq:include path="errorCodesTable" resourceType="searspartsdirect/components/content/errorCodesTable" />
		</div>
	</div>
</article>