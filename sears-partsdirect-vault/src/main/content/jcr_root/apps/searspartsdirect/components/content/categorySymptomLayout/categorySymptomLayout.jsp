<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id ="${symptomRelation.id}" />

<div class="row-fluid">
	<div class="span12">
		<h1><c:out value="${symptom.title} "/></h1>
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<div class="span3"><cq:include path="overviewImage" resourceType="searspartsdirect/components/content/categoryIcon" /></div>
		<div class="span9"><c:out value="${symptom.description} "/></div>
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