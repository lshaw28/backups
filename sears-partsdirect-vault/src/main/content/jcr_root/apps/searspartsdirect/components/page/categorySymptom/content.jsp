<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span12">
			<% // @TODO: Make this an if/else %>
			<cq:include path="categorySymptomLayout" resourceType="searspartsdirect/components/content/categorySymptomLayout" />
			<cq:include path="modelSymptomLayout" resourceType="searspartsdirect/components/content/modelSymptomLayout" />
		</div>
	</div>
</article>