<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="ArticleIndexpage"><div class="articleFix">
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
					<cq:include path="articleIndexCopy" resourceType="searspartsdirect/components/content/articleIndexCopy" />
					<cq:include path="category101Detail" resourceType="searspartsdirect/components/content/category101Detail" />
					<cq:include path="categoryArticleList" resourceType="searspartsdirect/components/content/categoryArticleList" />
				</div>
				<div class="desktopAdUnit tabletAdUnit adUnitContainer span3 pull-right">
					<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit" />
				</div>
			</div>
		</div>
	</div>
	<div class="mobileAdUnit adUnitContainer">
		<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit300x250" />
	</div>
</div></article>