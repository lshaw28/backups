<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>
<spd:uniqueID />

<div class="accordion" id="parent_${uniqueId}1">
	<div class="accordion-group">
		<div class="accordion-heading cafHeadingOpen">
			<a class="accordion-toggle" id="toggle_${uniqueId}1" data-toggle="false" data-status="incomplete" data-parent="#parent_${uniqueId}1" data-href="#${uniqueId}1">
				1 &nbsp; Select Water Filter and Reorder Frequency
			</a>
		</div>
		<div id="${uniqueId}1" class="accordion-body in">
			<div class="accordion-inner">
				<form id="cafSelectFilterFrequencyForm" method="post" action="">
					<div class="alert alert-error hidden" id="alert_${uniqueId}1">&nbsp;</div>
					<fieldset>
						<div class="cafField">
							<p>Search by fridge model number or water filter part number</p>
							<div class="radioOptions">
								<input type="radio" name="numberType" id="ntModel" value="model" />
								<label for="ntModel">Fridge Model #</label>
								<input type="radio" name="numberType" id="ntPart" value="part" />
								<label for="ntPart">Filter Part #</label>
							</div>
							<input class="cafSpacedInput" name="number" value="Enter your part or model number" data-inputhelp="Enter your part or model number" />
							<p><a href="#findMyModel" class="searchPanelFinder_js">Can't locate the model number? Use our finder</a></p>
						</div>
						<div class="cafField">
							<p>Select water filter automatic reorder frequency</p>
							<div class="radioOptions">
								<input type="radio" name="frequency" id="frequency3" value="3" />
								<label for="frequency3">Every 3 months</label>
								<input type="radio" name="frequency" id="frequency6" value="6" />
								<label for="frequency6">Every 6 months</label>
								<input type="radio" name="frequency" id="frequency12" value="12" />
								<label for="frequency12">Every 12 months</label>
							</div>
						</div>
						<div class="cafField">
							<p>Starting from</p>
							<select name="startMonth" id="startMonth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
								<option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>
							</select>
							<select name="startDay" id="startDay" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
							<select name="startYear" id="startYear" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
								<option value="2013">2013</option>
								<option value="2014">2014</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
							</select>
						</div>
						<div class="cafField">
							<label for="waterFilterQuantity">Qty</label>
							<input class="cafQuantity" name="quantity" id="waterFilterQuantity" />
						</div>
					</fieldset>
					<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="1" data-alert-id="alert_${uniqueId}1" data-this-toggle-id="toggle_${uniqueId}1">Continue</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}2">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" id="toggle_${uniqueId}2" data-toggle="false" data-status="unavailable" data-parent="#parent_${uniqueId}2" data-href="#${uniqueId}2">
				2 &nbsp; Enter Shipping Address
			</a>
		</div>
		<div id="${uniqueId}2" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafShippingAddressForm" method="post" action="">
					<div class="alert alert-error hidden" id="alert_${uniqueId}2">&nbsp;</div>
					<fieldset>
						<div class="cafField">
							<label for="shippingFirst">First Name <span>Required</span></label>
							<input name="fname" id="shippingFirst" />
						</div>
						<div class="cafField">
							<label for="shippingLast">Last Name <span>Required</span></label>
							<input name="lname" id="shippingLast" />
						</div>
						<div class="cafField cafCheckboxField">
							<input type="checkbox" name="po" id="shippingPO" />
							<label for="shippingPO">This is a P.O. Box or Military Address. View shipping restrictions</label>
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="shippingAddress">Street Address <span>Required</span></label>
								<input name="address" id="shippingAddress" />
							</div>
							<div class="cafSubField">
								<label for="shippingApt">Apt. # <span>Optional</span></label>
								<input name="apt" id="shippingApt" />
							</div>
						</div>
						<div class="cafField">
							<label for="shippingCity">City <span>Required</span></label>
							<input name="city" id="shippingCity" />
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="shippingState">State <span>Required</span></label>
								<select name="state" id="shippingState" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
									<option value="AA">AA</option>
									<option value="AE">AE</option>
									<option value="AL">AK</option>
									<option value="AP">AP</option>
								</select>
							</div>
							<div class="cafSubField">
								<label for="shippingZip">ZIP Code <span>Required</span></label>
								<input name="zip" id="shippingZip" data-mask="99999" />
							</div>
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="shippingPhone">Phone Number <span>Required</span></label>
								<input name="phone" id="shippingPhone" data-mask="999-999-9999" />
							</div>
							<div class="cafSubField">
								<label for="shippingExt">Ext. # <span>Optional</span></label>
								<input name="ext" id="shippingExt" />
							</div>
						</div>
						<div class="cafField">
							<label for="shippingEmail">Email Address <span>Required</span></label>
							<input name="email" id="shippingEmail" />
						</div>
						<div class="cafField">
							<label for="shippingConfirm">Confirm Email Address <span>Required</span></label>
							<input name="confirm" id="shippingConfirm" />
						</div>
						<div class="cafField cafCheckboxField">
							<input type="checkbox" name="same" id="shippingSame" />
							<label for="shippingSame">Billing address same as shipping address</label>
						</div>
					</fieldset>
					<button type="button" class="new-btn new-btn-search cafSubmit" id="shippingSubmit" data-submit="true" data-form-number="2" data-alert-id="alert_${uniqueId}2" data-this-toggle-id="toggle_${uniqueId}2">Continue</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}3">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" id="toggle_${uniqueId}3" data-toggle="false" data-status="unavailable" data-parent="#parent_${uniqueId}3" data-href="#${uniqueId}3">
				3 &nbsp; Enter Billing Address
			</a>
		</div>
		<div id="${uniqueId}3" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafBillingAddressForm" method="post" action="">
					<div class="alert alert-error hidden" id="alert_${uniqueId}3">&nbsp;</div>
					<fieldset>
						<div class="cafField">
							<label for="billingFirst">First Name <span>Required</span></label>
							<input name="fname" id="billingFirst" />
						</div>
						<div class="cafField">
							<label for="billingLast">Last Name <span>Required</span></label>
							<input name="fname" id="billingLast" />
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="billingAddress">Street Address <span>Required</span></label>
								<input name="address" id="billingAddress" />
							</div>
							<div class="cafSubField">
								<label for="billingApt">Apt. # <span>Optional</span></label>
								<input name="apt" id="billingApt" />
							</div>
						</div>
						<div class="cafField">
							<label for="billingCity">City <span>Required</span></label>
							<input name="city" id="billingCity" />
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="billingState">State <span>Required</span></label>
								<select name="state" id="billingState" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
									<option value="AA">AA</option>
									<option value="AE">AE</option>
									<option value="AL">AK</option>
									<option value="AP">AP</option>
								</select>
							</div>
							<div class="cafSubField">
								<label for="billingZip">ZIP Code <span>Required</span></label>
								<input name="zip" id="billingZip" data-mask="99999" />
							</div>
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="billingPhone">Phone Number <span>Required</span></label>
								<input name="phone" id="billingPhone" data-mask="999-999-9999" />
							</div>
							<div class="cafSubField">
								<label for="billingExt">Ext. # <span>Optional</span></label>
								<input name="ext" id="billingExt" />
							</div>
						</div>
						<div class="cafField cafCheckboxField">
							<input type="checkbox" name="same" id="billingSame" />
							<label for="billingSame">Use Shipping Address instead</label>
						</div>
					</fieldset>
					<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="3" data-alert-id="alert_${uniqueId}3" data-this-toggle-id="toggle_${uniqueId}3">Continue</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}4">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" id="toggle_${uniqueId}4" data-toggle="false" data-status="unavailable" data-parent="#parent_${uniqueId}4" data-href="#${uniqueId}4">
				4 &nbsp; Enter Payment Information
			</a>
		</div>
		<div id="${uniqueId}4" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafPaymentForm" method="post" action="">
					<div class="alert alert-error hidden" id="alert_${uniqueId}4">&nbsp;</div>
					<fieldset>
						<div class="cafField">
							<label for="payNumber">Card Number <span>Required</span></label>
							<input name="number" id="payNumber" maxlength="16" />
						</div>
						<div class="cafField">
							<label for="payCode">Security Code <span>Required</span></label>
							<input name="code" id="payCode" data-mask="999" />
						</div>
						<div class="cafField">
							<div class="cafSubField">
								<label for="payMonth">State <span>Required</span></label>
								<select name="month" id="payMonth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
								</select>
								<select name="year" id="payYear" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
								</select>
							</div>
						</div>
						<div class="cafField">
							<label for="payName">Name on Card <span>Required</span></label>
							<input name="name" id="payName" />
						</div>
					</fieldset>
					<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="4" data-alert-id="alert_${uniqueId}4" data-this-toggle-id="toggle_${uniqueId}4">Continue</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}5">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" id="toggle_${uniqueId}5" data-toggle="false" data-status="unavailable" data-parent="#parent_${uniqueId}5" data-href="#${uniqueId}5">
				5 &nbsp; Review Subscription
			</a>
		</div>
		<div id="${uniqueId}5" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="cafField">
					<p>First Name</p>
					<input name="fname" />
				</div>
			</div>
		</div>
	</div>
</div>