<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="MaintenanceTipspage"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="desktopAdUnit tabletAdUnit adUnitContainer span2 offset1 pull-right">
					<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit" />
				</div>
				<div class="span9">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<cq:include path="maintenanceTipsIntro" resourceType="searspartsdirect/components/content/text" />
					<cq:include path="maintenanceTopicJump" resourceType="searspartsdirect/components/content/jumpTopicMenu" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
			</div>
			<div class="mobileAdUnit adUnitContainer">
				<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit300x250" />
			</div>
		</div>
	</div>
</div></article>