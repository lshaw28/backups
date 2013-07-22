<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span9">
					<cq:include path="overviewImage" resourceType="searspartsdirect/components/content/responsiveImage" />
					<cq:include path="overview" resourceType="searspartsdirect/components/content/text" />
				</div>
				<div class="span3">
					<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="categoryListing" resourceType="searspartsdirect/components/content/categoryListing" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
			</div>
		</div>
	</div>
</article>