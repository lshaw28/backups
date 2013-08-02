<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" />
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<div class="row-fluid">
						<div class="span8">
							<div class="row-fluid">
								<div class="span12">
									<cq:include path="categoryDescription" resourceType="searspartsdirect/components/content/categoryDescription" />
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
							<cq:include path="featuredGuide" resourceType="searspartsdirect/components/content/mostPopularGuide" />
							<cq:include path="guideListing" resourceType="searspartsdirect/components/content/guideListing" />
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