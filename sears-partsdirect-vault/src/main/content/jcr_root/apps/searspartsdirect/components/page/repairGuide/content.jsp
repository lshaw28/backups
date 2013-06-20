<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<cq:include path="guideNavigation" resourceType="searspartsdirect/components/content/guideNavigation" />
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="header" resourceType="searspartsdirect/components/content/header" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span2 ad-span pull-right">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
		<div class="span5">
			<a name="template_toolsRequiredRepair"></a>
			<cq:include path="toolsRequiredRepair" resourceType="searspartsdirect/components/content/toolsRequiredRepair" />
		</div>
		<div class="span5">
			<a name="template_partsRequiredRepair"></a>
			<cq:include path="partsRequiredRepair" resourceType="searspartsdirect/components/content/partsRequiredRepair" />
		</div>
		<div class="span10">
			<a name="template_beforeYouBegin"></a>
			<cq:include path="beforeYouBegin" resourceType="searspartsdirect/components/content/text" />
		</div>
		<div class="span10">
			<a name="template_repairInstructions"></a>
			<cq:include path="repairInstructions" resourceType="searspartsdirect/components/content/repairInstructions" />
		</div>
		<div class="span10">
			<a name="template_comments"></a>
			<cq:include path="comments" resourceType="searspartsdirect/components/content/comments" />
		</div>
	</div>
</article>