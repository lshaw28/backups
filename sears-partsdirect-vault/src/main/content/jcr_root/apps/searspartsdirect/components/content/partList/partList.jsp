<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.JSONObject,
				com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus" %>
<cq:includeClientLib categories="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String formattedModelNumber = (request.getParameter("formattedModelNumber") != null) ? request.getParameter("formattedModelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String diagramPageId = (request.getParameter("diagramPageId") != null) ? request.getParameter("diagramPageId") : "";
String documentId = (request.getParameter("documentId") != null) ? request.getParameter("documentId") : "";
String brandName = (request.getParameter("brandName") != null) ? request.getParameter("brandName") : "";
String modelDescription = (request.getParameter("modelDescription") != null) ? request.getParameter("modelDescription") : "";
%>
<%@include file="/apps/searspartsdirect/components/content/headerPD/headerPD.jsp"%>
				
<div class="row-fluid">
	<div class="new-span-general partListDiagram">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div>
	<div class="new-span-general partListItems" id="partListItems">
	</div>
</div>
<%
	PSFlagStatus flagStatus = sling.getService(PSFlagStatus.class);	// calling PSFlagStatus -- to get data from Felix
	JSONObject flagMessage = flagStatus.getStockAvailabilityMessage();
%>
<script>modelDiagramPartList('<%=modelNumber%>', '<%=formattedModelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=diagramPageId%>', '<%=documentId%>', '<%=flagMessage%>','<%=brandName%>','<%=modelDescription%>');</script>

<script>
    function getDiagramPagePreview(){
        $('.partListItemQuantity').remove();
        $('.partListItemAdd').remove();
		var pageHtml= $('#partListItems').parent().html();
        return pageHtml;
    }
    
</script>