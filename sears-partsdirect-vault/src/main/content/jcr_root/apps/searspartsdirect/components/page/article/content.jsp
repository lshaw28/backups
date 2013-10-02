<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="Articlepage"><div class="articleFix">
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
				<div class="span9">
					<cq:include path="authorDisplay" resourceType="searspartsdirect/components/content/article/authorDisplay" />
					<cq:include path="socialBar" resourceType="searspartsdirect/components/content/socialBar" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
				<div class="desktopAdUnit adDisabled span2 offset1 pull-right">
					<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit" />
				</div>
				<div class="tabletAdUnit adDisabled">
					<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit768x90" />
				</div>
				<div class="mobileAdUnit adDisabled">
					<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit300x50" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="relatedArticles" resourceType="searspartsdirect/components/content/article/relatedArticles" />
				</div>
			</div>
		</div>
	</div>
</div></article>