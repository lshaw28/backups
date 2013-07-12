<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
	<div class="row-fluid">
		<div class="span8">
			<cq:include path="modelSymptoms" resourceType="searspartsdirect/components/content/modelSymptoms" />
			<cq:include path="errorCodeChecker" resourceType="searspartsdirect/components/content/errorCodeChecker" />
			<cq:include path="mostViewedGuides" resourceType="searspartsdirect/components/content/mostViewedGuides" />
		</div>
		<div class="span3 offset1">
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 ad-span">
					<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 ad-span">
					<cq:include path="topParts" resourceType="searspartsdirect/components/content/topParts" />
				</div>
			</div>
		</div>
	</div>
</article>