<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<div class="row-fluid">
						<div class="span8">
							<div class="row-fluid">
								<div class="span12">
									<cq:include path="overviewIcon" resourceType="searspartsdirect/components/content/categoryIcon" />
									<cq:include path="overview" resourceType="searspartsdirect/components/content/categoryDescription" />
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />
								</div>
								<div class="span6">
									<cq:include path="text" resourceType="searspartsdirect/components/content/text" />
								</div>
							</div>
						</div>
						<div class="span3 offset1">
							<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
						</div>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<cq:include path="categorySymptoms" resourceType="searspartsdirect/components/content/categorySymptoms" />
							<cq:include path="errorCodeChecker" resourceType="searspartsdirect/components/content/errorCodeChecker" />
							<cq:include path="relatedGuides" resourceType="searspartsdirect/components/content/relatedGuides" />
						</div>
						<div class="span2 adspan offset1">
							<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</article>