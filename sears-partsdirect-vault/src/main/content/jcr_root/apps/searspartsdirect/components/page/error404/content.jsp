<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="errorHeader" resourceType="searspartsdirect/components/content/responsiveImage" />
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<cq:include path="errorBody" resourceType="searspartsdirect/components/content/text" />
				</div>
			</div>
		</div>
	</div>
</article>