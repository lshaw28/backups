<%@ include file="/apps/searspartsdirect/global.jsp"%>
<div class="row-fluid">
	<div class="span12">
		<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
	</div>
</div>
<div class="row-fluid">
	<div class="span9">
		<cq:include path="overviewImage" resourceType="searspartsdirect/components/content/responsiveImage" />
		<cq:include path="overview" resourceType="searspartsdirect/components/content/text" />
	</div>
	<div class="span3">
		<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
	</div>
</div>
<div class="row-fluid">
	<div class="span9">
		<cq:include path="categorySymptomDetail" resourceType="searspartsdirect/components/content/categorySymptomDetail" />
	</div>
	<div class="span2 offset1 ad-span pull-right">
		<cq:include path="skyscraperAd" resourceType="searspartsdirect/components/content/skyscraperAd" />
	</div>
</div>