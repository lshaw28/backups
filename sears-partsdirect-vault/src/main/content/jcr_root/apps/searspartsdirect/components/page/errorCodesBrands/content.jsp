<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="errorCodesTableHeader" resourceType="searspartsdirect/components/content/errorCodesTableHeader" />
					<cq:include path="errorCodesTable" resourceType="searspartsdirect/components/content/errorCodesTable" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
			</div>
		</div>
	</div>
</article>