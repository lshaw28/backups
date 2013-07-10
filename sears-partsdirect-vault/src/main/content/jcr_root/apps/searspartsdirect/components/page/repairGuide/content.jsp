<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<spd:displayModelHeader/>
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
		</div>
		<div class="span12">
			<cq:include path="authorDisplay" resourceType="searspartsdirect/components/content/article/authorDisplay" />
		</div>
		<div class="span12">
			<cq:include path="socialBar" resourceType="searspartsdirect/components/content/socialBar" />
		</div>
	</div>
	<div class="clearfix">&nbsp;</div>
	<cq:include path="guideNavigation" resourceType="searspartsdirect/components/content/guideNavigation" />
	<div class="clearfix">&nbsp;</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="guideDetails" resourceType="/apps/searspartsdirect/components/content/guideDetails" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span5">
			<a name="template_toolsRequiredRepair"></a>
			<cq:include path="toolsRequiredRepair" resourceType="searspartsdirect/components/content/toolsRequiredRepair" />
		</div>
		<div class="span4">
			<a name="template_partsRequiredRepair"></a>
			<cq:include path="partsRequiredRepair" resourceType="searspartsdirect/components/content/partsRequiredRepair" />
		</div>
		<div class="span9">
			<a name="template_beforeYouBegin"></a>
			<cq:include path="beforeYouBegin" resourceType="searspartsdirect/components/content/text" />
		</div>
		<div class="span9">
			<a name="template_repairInstructions"></a>
			<cq:include path="repairInstructions" resourceType="searspartsdirect/components/content/repairInstructions" />
		</div>
		<div class="span2 offset1 ad-span pull-right">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span9">
			<a name="template_comments"></a>
			<cq:include path="comments" resourceType="searspartsdirect/components/content/comments" />
		</div>
	</div>
</article>