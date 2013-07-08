<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span2 offset1 ad-span pull-right">
			<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
		</div>
		<div class="span9">
			<cq:include path="authorDisplay" resourceType="searspartsdirect/components/content/article/authorDisplay" />
			<cq:include path="socialBar" resourceType="searspartsdirect/components/content/socialBar" />
			<cq:include path="parsys" resourceType="foundation/components/parsys" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="relatedArticles" resourceType="searspartsdirect/components/content/relatedArticles" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<cq:include path="comments" resourceType="searspartsdirect/components/content/comments" />
		</div>
	</div>
</article>