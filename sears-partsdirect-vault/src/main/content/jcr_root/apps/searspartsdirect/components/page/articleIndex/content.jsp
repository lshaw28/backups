<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span8">
					<cq:include path="articleIndexCopy" resourceType="searspartsdirect/components/content/articleIndexCopy" />
					<cq:include path="category101Detail" resourceType="searspartsdirect/components/content/category101Detail" />
					<cq:include path="categoryArticleList" resourceType="searspartsdirect/components/content/categoryArticleList" />
				</div>
				<div class="span3 offset1 pull-right ad-span">
					<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
				</div>
			</div>
		</div>
	</div>
</div></article>