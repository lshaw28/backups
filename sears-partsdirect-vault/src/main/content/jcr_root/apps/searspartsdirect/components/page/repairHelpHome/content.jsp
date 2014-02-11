<%@ include file="/apps/searspartsdirect/global.jsp"%>
<script type="text/javascript">//<![CDATA[
var _uzactions= _uzactions|| [];
_uzactions.push(['_setID', '0AA910F83490E31190590022196C4538']);
_uzactions.push(['_setSID', '09A910F83490E31190590022196C4538']);
_uzactions.push(['_start']);
(function() {
  var uz = document.createElement('script'); uz.type = 'text/javascript'; uz.async = true; uz.charset = 'utf-8';
  uz.src = ('https:' == document.location.protocol ? 'https://&#39; : 'http://&#39;) + 'cdn4.userzoom.com/trueintent/js/uz_til.js?cuid=AB85F1DE49F6DF1188490022196C4538';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(uz, s);
})();
//]]></script> 
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