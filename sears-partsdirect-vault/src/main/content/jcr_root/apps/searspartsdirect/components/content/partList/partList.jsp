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
    <div class="pageTitleHeader">
        <h1>Diagram Name</h1>
    </div>
	<div id="partListDiagram" class="new-span-general partListDiagram">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div>

    <div id="partListTitle">
        <p>Parts in this Diagram</p>
    </div>

	<div id="partListItems" class="new-span-general partListItems">
<%// This should be a for each going through each part
	// I don't know how these are supposed to be populated %>
		<div class="partListItem row-fluid">
			<div id="diagramPosition" class="new-span-general diagramPosition">
				<p><span>1</span><br />on diagram</p>
			</div>
			<div id="partListItemDescription" class="new-span-general partListItemDescription">
				<% // If there is an image URL %>
				<div class="partListItemImage">
					<img src="http://www.urlforthepartimage.com/image/jpg" />
				</div>
				<% // End if %>
				<p><a href="http://www.urlforthepart.com">The long name for the part</a><br />
				Part #: XXXXXXXX
				<% // If the part has a substitution %>
				<br /><small><i class="icon-share flip-vertical">&nbsp;</i> Substitution: YYYYYYYY</small>
				<% // End If
				// If the item is not returnable %>
				<br /><span class="error">This item is not returnable</span>
				<% // End if %>
				</p>
			</div>
			<div id="partListItemCart" class="new-span-general partListItemCart">
				<% // If the user needs to contact customer support %>
				<p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>
				<% // If the item is no longer available %>
				<p>We're sorry, this item is no longer available.</p>
				<% // Otherwise %>
				<div id="partListItemPrice" class="partListItemPrice">
					<strong>$X.XX</strong> In stock
				</div>
				<% // End If %>
				<div id="partListItemQuantity" class="partListItemQuantity">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" />
				</div>
				<div class="partListItemAdd">
					<button type="button" data-partnumber="partNumber" data-divid="productGroupID" data-plsid="supplierID" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
<% // End for each
	// Second item to test styles %>
		<div class="partListItem row-fluid">
			<div class="new-span-general diagramPosition">
				<p><span>1</span><br />on diagram</p>
			</div>
			<div class="new-span-general partListItemDescription">
				<% // If there is an image URL %>
				<div class="partListItemImage">
					<img src="http://www.urlforthepartimage.com/image/jpg" />
				</div>
				<% // End if %>
				<p><a href="http://www.urlforthepart.com">The long name for the part</a><br />
				Part #: XXXXXXXX
				<% // If the part has a substitution %>
				<br /><small><i class="icon-share flip-vertical">&nbsp;</i> Substitution: YYYYYYYY</small>
				<% // End If
				// If the item is not returnable %>
				<br /><span class="error">This item is not returnable</span>
				<% // End if %>
				</p>
			</div>
			<div class="new-span-general partListItemCart">
				<% // If the user needs to contact customer support %>
				<p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>
				<% // If the item is no longer available %>
				<p>We're sorry, this item is no longer available.</p>
				<% // Otherwise %>
				<div class="partListItemPrice">
					<strong>$X.XX</strong> In stock
				</div>
				<% // End If %>
				<div class="partListItemQuantity">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" />
				</div>
				<div class="partListItemAdd">
					<% // Please replace the values in the data- attributes with actual values %>
					<button type="button" data-partnumber="partNumber" data-divid="productGroupID" data-plsid="supplierID" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
<% // End second item %>

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
