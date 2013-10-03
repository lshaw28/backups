<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="CommonQuestionspage"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span8">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<cq:include path="commonQuestionsIntro" resourceType="searspartsdirect/components/content/text" />
					<cq:include path="commonQuestionsTopicJump" resourceType="searspartsdirect/components/content/jumpTopicMenu" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
				<div class="span4 pull-right">
					<div class="row-fluid">
						<div class="span12">
							<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
						</div>
					</div>
					<div class="desktopAdUnit tabletAdUnit adUnitContainer span12 offset1 pull-right">
						<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit" />
					</div>
					<div class="mobileAdUnit adUnitContainer">
						<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit300x50" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div></article>