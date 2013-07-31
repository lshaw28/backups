<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span8">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<cq:include path="maintenanceTipsIntro" resourceType="searspartsdirect/components/content/text" />
					<cq:include path="maintenanceTopicJump" resourceType="searspartsdirect/components/content/jumpTopicMenu" />
					<cq:include path="parsys" resourceType="foundation/components/parsys" />
				</div>
				<div class="span3 offset1 pull-right">
					<div class="row-fluid">
						<div class="span12">
							<%-- <cq:include path="topAccessories" resourceType="searspartsdirect/components/content/topAccessories" />--%>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12 ad-span">
							<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</article>