<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
	<div class="row-fluid">
		<div class="span12">
			<spd:getRelation single="true" assetType="productCategory"/>
			<c:choose>
				<c:when test="${productCategoryRelation != null}">
				<cq:include path="categorySymptomLayout" resourceType="searspartsdirect/components/content/categorySymptomLayout" />
				</c:when>
				<c:otherwise>
					<cq:include path="modelSymptomLayout" resourceType="searspartsdirect/components/content/modelSymptomLayout" />
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</article>