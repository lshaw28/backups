<%@ include file="/apps/searspartsdirect/global.jsp"%>

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String diagramPageId = (request.getParameter("diagramPageId") != null) ? request.getParameter("diagramPageId") : "";
String documentId = (request.getParameter("documentId") != null) ? request.getParameter("documentId") : "";
%>

<div class="row-fluid">
	<div class="new-span-general partListDiagram">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div>
	<div class="new-span-general partListItems" id="partListItems">
	</div>
</div>
<script>modelDiagramPartList('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=diagramPageId%>', '<%=documentId%>');</script>

<script>
    function getDiagramPagePreview(){
        $('.partListItemQuantity').remove();
        $('.partListItemAdd').remove();
		var pageHtml= $('#partListItems').parent().html();
        return pageHtml;
    }
</script>