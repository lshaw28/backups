<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<spd:getUrlRelation />
					<c:choose>
						<c:when test="${not empty brandRelation and not empty productCategoryRelation and not empty modelRelation}">
							<cq:include path="modelSymptomLayout" resourceType="searspartsdirect/components/content/modelSymptomLayout" />
						</c:when>
						<c:otherwise>
							<cq:include path="categorySymptomLayout" resourceType="searspartsdirect/components/content/categorySymptomLayout" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</article>