<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="javax.jcr.Property, javax.jcr.Value, com.day.cq.wcm.api.WCMMode, com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings"%>

<cq:includeClientLib categories="apps.searspartsdirect,apps.searspartsdirect.base" />

<% 
	String brand=properties.get("brand", "");
	String productType=properties.get("prodtype", "");

	String partNumber=properties.get("partnumber1", "");
	String productGroupId=properties.get("divId1", "");
	String supplierId=properties.get("plsId1", "");
	
	String partNumber2=properties.get("partnumber2", "");
	String productGroupId2=properties.get("divId2", "");
	String supplierId2=properties.get("plsId2", "");
	
	String partNumber3=properties.get("partnumber3", "");
	String productGroupId3=properties.get("divId3", "");
	String supplierId3=properties.get("plsId3", "");
	
	String partNumber4=properties.get("partnumber4", "");
	String productGroupId4=properties.get("divId4", "");
	String supplierId4=properties.get("plsId4", "");
	
	String externalPrefix = "";
	EnvironmentSettings envSettings = sling.getService(EnvironmentSettings.class);
	externalPrefix = envSettings.getExternalAddedPrefix();
%>

<% if(currentNode.hasProperty("brand")){
%>
<div class="new-span-general partListItems">
	<h3>Top Selling Parts for <%=brand%> <%=productType%>s</h3>	
	<div id="div1" class="span3">
		<div class="partListItem">
			<div class="partListItemCart">
				<div class="partImage">
					<a href="#"><img src="" /></a>
				</div>
				<div class="partDescription">
					<strong>
						<a href="#"></a>
					</strong>
				</div>
				<div class="partNo">
					<strong>
						Part #:
						<span></span>
					</strong>
				</div>
				<div class="partPts">
					Earn <span></span> points
				</div>
				<div class="partInfo">
					<div class="partListItemPrice">
						<strong></strong>
					</div>
					<div class="partAvailability">
						<strong></strong>
					</div>
				</div>
				<div class="partListItemQuantity hidden-phone">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" maxlength="3" />
				</div>
				<div class="clear"></div>
				<div class="partListItemAdd hidden-phone">
					<button type="button" data-partnumber="${part.number}" data-divid="${part.productGroupId}" data-plsid="${part.supplierId}" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
		<a class="mobile-curtain hidden-desktop"></a>
	</div>
	<div id="div2" class="span3">
		<div class="partListItem">
			<div class="partListItemCart">
				<div class="partImage">
					<a href="#"><img src="" /></a>
				</div>
				<div class="partDescription">
					<strong>
						<a href="#"></a>
					</strong>
				</div>
				<div class="partNo">
					<strong>
						Part #:
						<span></span>
					</strong>
				</div>
				<div class="partPts">
					Earn <span></span> points
				</div>
				<div class="partInfo">
					<div class="partListItemPrice">
						<strong></strong>
					</div>
					<div class="partAvailability">
						<strong></strong>
					</div>
				</div>
				<div class="partListItemQuantity hidden-phone">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" maxlength="3" />
				</div>
				<div class="clear"></div>
				<div class="partListItemAdd hidden-phone">
					<button type="button" data-partnumber="${part.number}" data-divid="${part.productGroupId}" data-plsid="${part.supplierId}" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
		<a class="mobile-curtain hidden-desktop"></a>
	</div>
	<div id="div3" class="span3">
		<div class="partListItem">
			<div class="partListItemCart">
				<div class="partImage">
					<a href="#"><img src="" /></a>
				</div>
				<div class="partDescription">
					<strong>
						<a href="#"></a>
					</strong>
				</div>
				<div class="partNo">
					<strong>
						Part #:
						<span></span>
					</strong>
				</div>
				<div class="partPts">
					Earn <span></span> points
				</div>
				<div class="partInfo">
					<div class="partListItemPrice">
						<strong></strong>
					</div>
					<div class="partAvailability">
						<strong></strong>
					</div>
				</div>
				<div class="partListItemQuantity hidden-phone">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" maxlength="3" />
				</div>
				<div class="clear"></div>
				<div class="partListItemAdd hidden-phone">
					<button type="button" data-partnumber="${part.number}" data-divid="${part.productGroupId}" data-plsid="${part.supplierId}" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
		<a class="mobile-curtain hidden-desktop"></a>
	</div>
	<div id="div4" class="span3">
		<div class="partListItem">
			<div class="partListItemCart">
				<div class="partImage">
					<a href="#"><img src="" /></a>
				</div>
				<div class="partDescription">
					<strong>
						<a href="#"></a>
					</strong>
				</div>
				<div class="partNo">
					<strong>
						Part #:
						<span></span>
					</strong>
				</div>
				<div class="partPts">
					Earn <span></span> points
				</div>
				<div class="partInfo">
					<div class="partListItemPrice">
						<strong></strong>
					</div>
					<div class="partAvailability">
						<strong></strong>
					</div>
				</div>
				<div class="partListItemQuantity hidden-phone">
					<label>Qty</label>
					<input type="text" class="addToCartQuantity_js" value="1" maxlength="3" />
				</div>
				<div class="clear"></div>
				<div class="partListItemAdd hidden-phone">
					<button type="button" data-partnumber="${part.number}" data-divid="${part.productGroupId}" data-plsid="${part.supplierId}" data-location="Symptom Part List Page" data-component="<%=resource.getResourceType()%>" class="new-btn new-btn-search addToCart_js">Add to Cart</button>
				</div>
			</div>
		</div>
		<a class="mobile-curtain hidden-desktop"></a>
	</div>
</div>
<%}
	else if(WCMMode.fromRequest(request) != WCMMode.DISABLED){
    %>
		<img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>
		<%
	}
%>
<script>
    topAccessoryparts('<%=productGroupId%>','<%=supplierId%>','<%=partNumber%>', 'div1', '<%=externalPrefix %>');
    topAccessoryparts('<%=productGroupId2%>','<%=supplierId2%>','<%=partNumber2%>', 'div2', '<%=externalPrefix %>');
    topAccessoryparts('<%=productGroupId3%>','<%=supplierId3%>','<%=partNumber3%>', 'div3', '<%=externalPrefix %>');
    topAccessoryparts('<%=productGroupId4%>','<%=supplierId4%>','<%=partNumber4%>', 'div4', '<%=externalPrefix %>');
</script>