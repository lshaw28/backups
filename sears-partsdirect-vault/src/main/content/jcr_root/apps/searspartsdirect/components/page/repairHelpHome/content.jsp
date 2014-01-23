<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="RepairHelpHomepage"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<div class="row-fluid">
				<div class="span12 repairHelpHomeTitle">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="span8">
					<div class="repairHelpHomeIcon">
						<span class="icon-stack pull-left">
							<i class="icon-circle icon-stack-base"></i>
							<i class="icon-wrench icon-light"></i>
						</span>
					</div>
					<cq:include path="repairHelpHomeIntro" resourceType="searspartsdirect/components/content/text" />
					<div class="clearfix"></div>
				</div>
				<div class="span4">
					<cq:include path="modelSearch" resourceType="searspartsdirect/components/content/responsiveFindThisPart" />
				</div>
			</div>
			<div class="row-fluid">
			    <c:choose>
                    <c:when test="${afBannerFlag eq 'y'}">
                        <div class="visible-desktop hidden-phone hidden-tablet">
                            <div class="span10">
                                <cq:include path="categoryListing" resourceType="searspartsdirect/components/content/categoryListing" />
                                <cq:include path="parsys" resourceType="foundation/components/parsys" />
                            </div>
                            <div class="span2">
                                  <cq:include path="banners" resourceType="searspartsdirect/components/content/banners" />
                            </div>
                        </div>
                        <div class="hidden-desktop visible-phone visible-tablet">
                            <div class="span12">
                                <cq:include path="categoryListing" resourceType="searspartsdirect/components/content/categoryListing" />
                                <cq:include path="parsys" resourceType="foundation/components/parsys" />
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="span12">
                            <cq:include path="categoryListing" resourceType="searspartsdirect/components/content/categoryListing" />
                            <cq:include path="parsys" resourceType="foundation/components/parsys" />
                        </div>
                    </c:otherwise>
                </c:choose>
			</div>
		</div>
	</div>
</div></article>