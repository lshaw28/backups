<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<cq:include path="authorDisplay" resourceType="searspartsdirect/components/content/article/authorDisplay" />
					<cq:include path="socialBar" resourceType="searspartsdirect/components/content/socialBar" />
				</div>
			</div>
			<div class="clearfix">&nbsp;</div>
			<cq:include path="guideNavigation" resourceType="searspartsdirect/components/content/guideNavigation" />
			<div class="clearfix">&nbsp;</div>
			<div class="row-fluid">
				<div class="span8">
					<div class="row-fluid">
						<div class="span12">
							<cq:include path="parsys" resourceType="foundation/components/parsys" />
							<a name="template_toolsRequiredRepair"></a>
							<cq:include path="toolsRequiredRepair" resourceType="searspartsdirect/components/content/toolsRequiredRepair" />
							<a name="template_beforeYouBegin"></a>
							<cq:include path="beforeYouBegin" resourceType="searspartsdirect/components/content/text" />
							<a name="template_repairInstructions"></a>
							<cq:include path="repairInstructions" resourceType="searspartsdirect/components/content/repairInstructions" />
						</div>
					</div>
				</div>
				<div class="span3 offset1 pull-right">
					<div class="row-fluid">
						<div class="span12">
							<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12 ad-span">
							<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span9">
					<%-- <a name="template_comments"></a>
					<c:set var="displayedCommentsType" scope="request">Guide</c:set>
					<cq:include path="comments" resourceType="searspartsdirect/components/content/comments" /> --%>
				</div>
			</div>
		</div>
	</div>
</div></article>