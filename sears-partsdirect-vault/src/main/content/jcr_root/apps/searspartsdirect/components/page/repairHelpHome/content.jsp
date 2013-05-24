<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="header" resourceType="searspartsdirect/components/content/header" />
	<div class="row-fluid">
		<div class="span9">
			<cq:include path="overview" resourceType="searspartsdirect/components/content/text" />
		</div>
		<div class="span3">
			<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
	</div>
</article>