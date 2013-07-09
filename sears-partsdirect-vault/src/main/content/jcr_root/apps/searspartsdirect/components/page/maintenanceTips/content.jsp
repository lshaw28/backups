<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span8">
			<cq:include path="maintenanceIndexTitle" resourceType="searspartsdirect/components/content/maintenanceIndexTitle" />
			<cq:include path="maintenanceTipsIntro" resourceType="searspartsdirect/components/content/text" />
			<cq:include path="maintenanceTopicJump" resourceType="searspartsdirect/components/content/maintenanceTopicJump" />
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
		<div class="span3 offset1 pull-right">
			<cq:include path="topAccessories" resourceType="searspartsdirect/components/content/topAccessories" />
		</div>
		<div class="span3 ad-span offset1 pull-right">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
	</div>
</article>