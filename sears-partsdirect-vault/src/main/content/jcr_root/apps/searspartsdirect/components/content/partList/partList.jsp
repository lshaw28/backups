<%@ include file="/apps/searspartsdirect/global.jsp"%>
<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String diagramPageId = (request.getParameter("diagramPageId") != null) ? request.getParameter("diagramPageId") : "";
String documentId = (request.getParameter("documentId") != null) ? request.getParameter("documentId") : "";
String diagramUrl = (request.getParameter("diagramUrl") != null) ? request.getParameter("diagramUrl") : "";
request.setAttribute("diagramUrl", diagramUrl);
%>

<div class="row-fluid">
	<div class="new-span-general partListDiagram">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div>
	<div class="new-span-general partListItems" id="partListItems">
	</div>
</div>

<script>modelDiagramPartList('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=diagramPageId%>', '<%=documentId%>');</script>