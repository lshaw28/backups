<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span8">
			<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
			<cq:include path="commonPartsIntro" resourceType="searspartsdirect/components/content/text" />
			<cq:include path="commonPartsTopicJump" resourceType="searspartsdirect/components/content/maintenanceTopicJump" />
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
		<div class="span3 offset1 pull-right">
			<cq:include path="topAccessories" resourceType="searspartsdirect/components/content/topAccessories" />
		</div>
		<div class="span3 offset1 pull-right">
			<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
		</div>
	</div>
</article>