<%@ include file="/apps/searspartsdirect/global.jsp"%><%
%><div class="row-fluid">
	<div class="new-span-general partListDiagram">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div>
	<div class="new-span-general partListItems">
<%	// This should be a for each going through each part
	// I don't know how these are supposed to be populated %>
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