<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<spd:DisplayModelHeader/>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="parsys_top" resourceType="foundation/components/parsys" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span10">
			<cq:include path="parsys_left" resourceType="foundation/components/parsys" />
		</div>
		<div class="span2">
			<cq:include path="parsys_right" resourceType="foundation/components/parsys" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="parsys_bottom" resourceType="foundation/components/parsys" />
		</div>
	</div>
</article>