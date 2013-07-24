<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id ="${symptomRelation.id}" />

<div class="row-fluid">
	<div class="span12">
		<h1>${symptom.title}</h1>
	</div>
</div>
<div class="row-fluid">
	<div class="span9">
		<p><cq:include path="overviewImage" resourceType="searspartsdirect/components/content/categoryIcon" />${symptom.description}</p>
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