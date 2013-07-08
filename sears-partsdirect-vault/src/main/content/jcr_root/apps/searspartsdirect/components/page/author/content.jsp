<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span9">
			<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
			<cq:include path="intro" resourceType="searspartsdirect/components/content/text" />
			<cq:include path="socialBar" resourceType="searspartsdirect/components/content/socialBar" />
			<cq:include path="authorDetails" resourceType="searspartsdirect/components/content/authorDetails" />
			<cq:include path="authorArticles" resourceType="searspartsdirect/components/content/authorArticles" />
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
		<div class="span2 offset1 ad-span pull-right">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
	</div>
</article>