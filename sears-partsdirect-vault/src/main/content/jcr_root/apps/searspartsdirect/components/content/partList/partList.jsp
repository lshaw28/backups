<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.JSONObject,
 	com.spd.cq.searspartsdirect.common.model.ModelWithPartList,
    com.spd.cq.searspartsdirect.common.model.ModelPart,
    com.spd.cq.searspartsdirect.common.model.Component,
    com.spd.cq.searspartsdirect.common.model.PartCompositeKey,
    com.spd.cq.searspartsdirect.common.model.PartRestriction,
    com.spd.cq.searspartsdirect.common.model.PriceAndAvailability,
    com.spd.cq.searspartsdirect.common.model.PartImage,
	com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus" %>
<cq:includeClientLib categories="apps.searspartsdirect,apps.searspartsdirect.base" />
<spd:modelPartList modelNumber="9030" brandId="1232" productCategoryId="1220000" diagramPageId="00002" documentId="10037297"/>
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

    <c:forEach var="component" items="${jsonResponse.components}">
    <div class="pageTitleHeader">
        <h1>${component.componentDescription}&nbsp;(${component.partCount})&nbsp;Parts</h1>
        <c:set var="diagramImage" value="${component.diagramImage.imageURL}"/>
    </div>
    </c:forEach>

<div class="row-fluid">
	<div class="new-span-general partListDiagram styleFix">
        <div id="partListDiagramImage">
            <img id="diagramImage" src="" />

            <div class="pinchImage row-fluid">
                <div class="new-span-responsive">
                    <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> Full Size</a>
                </div>
                <div class="new-span-responsive">
                    <a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print</a>
                </div>
                <div class="new-span-responsive">
                    <a data-toggle="pinch-fullscreen" target="_blank"><i class="icon-zoom-in"></i> View Full Size</a>
                </div>
                <div class="new-span-responsive">
                    <a data-toggle="pinch-print" target="_blank"><i class="icon-print"></i> Print Diagram</a>
                </div>
            </div><!-- /responsivePinchImage --->
        </div>
	</div>

    <div class="partListTitle">
        <p>Parts in this Diagram</p>
    </div>

	<div class="new-span-general partListItems">

    <c:forEach var="part" items="${jsonResponse.modelPart}">

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
                    <p><a href="http://www.urlforthepart.com">${part.description}</a><br />
                    Part #: ${part.priceAndAvailability.originalPartNumber}
                    <c:if test="${part.priceAndAvailability.originalPartNumber != part.partCompositeKey.partNumber}">
                    <br /><small><i class="icon-share flip-vertical">&nbsp;</i> Substitution:&nbsp;${part.partCompositeKey.partNumber}</small>
                    </c:if>
                    <c:if test="${part.priceAndAvailability.partReturnable=='false'}">
                    <br /><span class="error">This item is not returnable</span>
                    </c:if>
                    </p>
                </div>
                <div class="new-span-general partListItemCart">
                    <c:if test="${part.priceAndAvailability.availabilityStatus=='PNF'}">
                        <p>Contact customer support for availability: <strong>1-800-252-1698</strong></p>
                    </c:if>
                    <c:if test="${part.priceAndAvailability.availabilityStatus=='NLA'}">
                        <p>We're sorry, this item is no longer available.</p>
                    </c:if>
                    <c:if test="${part.priceAndAvailability.availabilityStatus=='INST'}">
                        <div class="partListItemPrice">
                            <strong>$${part.priceAndAvailability.sellingPrice}</strong> In stock
                        </div>
                        <div class="partListItemQuantity">
                            <label>Qty</label>
                            <input type="text" class="addToCartQuantity_js" value="1" />
                        </div>
                        <div class="partListItemAdd">
                            <button type="button" data-partnumber="partNumber" data-divid="productGroupID" data-plsid="supplierID" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
                        </div>
                    </c:if>
                </div>
            </div>

    </c:forEach>

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
    $(document).ready(function(){
        var imageURL = '<c:out value="${diagramImage}"/>';
        var diagramImage = document.getElementById('diagramImage');
        diagramImage.setAttribute('src',imageURL);
    });
</script>
