<%@ include file="/apps/searspartsdirect/global.jsp" %>
<h3><cq:text property="partsRequiredTitle" placeholder="Parts Required:" /></h3>
<% // @TODO: Make multifield per tools required %>
<ul>
	<% // @ TODO: For each %>
	<li data-partid="12345"><cq:text property="partsRequired" /></li>
	<li data-partid="12345">Part Name</li>
	<li data-partid="12345">Part Name</li>
</ul>

<cq:include path="modelNumberSearch" resourceType="searspartsdirect/components/content/modelNumberSearch" />