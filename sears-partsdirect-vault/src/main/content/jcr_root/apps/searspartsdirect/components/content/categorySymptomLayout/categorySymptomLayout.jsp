<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:getUrlRelation relationType="symptom" />
<spd:getSymptomDetail id="${symptomRelation.id}" />

<div class="row-fluid">
	<div class="span12">
		<h1><c:out value="${symptom.title}" /></h1>
	</div>
</div>
<div class="row-fluid">
	<div class="span8">
		<div class="categoryDescription">
			<p><cq:include path="categoryIcon" resourceType="searspartsdirect/components/content/categoryIcon" />${symptom.description}</p>
		</div>
	</div>
	<div class="span4">
		<cq:include path="category101" resourceType="searspartsdirect/components/content/category101" />
	</div>
	<div class="tabletAdUnit adUnitContainer">
		<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit728x90" />
	</div>
	<div class="mobileAdUnit adUnitContainer">
		<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit300x50" />
	</div>
</div>
<div class="row-fluid">
	<div class="span9">
		<cq:include path="categorySymptomDetail" resourceType="searspartsdirect/components/content/categorySymptomDetail" />
	</div>
	<div class="desktopAdUnit adUnitContainer span2 offset1 pull-right">
		<cq:include path="adUnit" resourceType="searspartsdirect/components/content/adUnit" />
	</div>
</div>