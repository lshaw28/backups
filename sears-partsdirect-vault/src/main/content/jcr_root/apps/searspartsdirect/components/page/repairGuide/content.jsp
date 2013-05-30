<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="header" resourceType="searspartsdirect/components/content/header" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<cq:include path="toolsRequiredRepair" resourceType="searspartsdirect/components/content/toolsRequiredRepair" />
		</div>
		<div class="span6">
			<cq:include path="partsRequiredRepair" resourceType="searspartsdirect/components/content/partsRequiredRepair" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span10">
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
		<div class="span2 ad-span">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="comments" resourceType="searspartsdirect/components/content/comments" />
		</div>
	</div>
</article>