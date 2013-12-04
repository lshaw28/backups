<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>
<spd:uniqueID />

<div class="accordion" id="parent_${uniqueId}1">
	<div class="accordion-group">
		<div class="accordion-heading cafHeadingOpen">
			<a class="accordion-toggle" id="toggle_${uniqueId}1" data-toggle="false" data-status="incomplete" data-parent="#parent_${uniqueId}1" data-href="#${uniqueId}1">
				1 &nbsp; Select Water Filter and Reorder Frequency
				<span class="hidden"><i class="icon-pencil"></i> EDIT</span>
			</a>
		</div>
		<div id="${uniqueId}1" class="accordion-body in">
			<div class="accordion-inner">
				<form id="cafSelectFilterFrequencyForm" method="post" action="">
					<div class="alert alert-error filterAlert hidden">Enter your part or model number to find a filter</div>
					<fieldset>
						<div class="row-fluid">
							<div class="span10 offset1">
								<p>Search by refrigerator model number or water filter part number</p>
								<div class="radioOptions">
									<input type="radio" name="numberType" id="ntPart" value="part" checked="checked" />
									<label for="ntPart">Filter Part #</label>
									<input type="radio" name="numberType" id="ntModel" value="model" />
									<label for="ntModel">Fridge Model #</label>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<input type="text" class="numberField" name="partNumber" value="Enter your part or model number" data-inputhelp="Enter your part or model number" />
								<div class="searchText">
									<span class="filterFound hidden"><a class="filterDescription" href="#" target="_blank"></a> is a match for your refrigerator.</span>
									<span class="filterError hidden">*We could not find a water filter matching this model or part number. Please try again or <a href="http://partsbetavip.qa.ch3.s.com/partsdirect/merchandiser/show.pd?description=Water%20Filters" target="_blank">browse water filters</a></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1 radioToDropDown">
								<p>Select water filter automatic reorder frequency</p>
								<div class="radioOptions">
									<input type="radio" class="filFreq" name="frequency" id="frequency3" value="3" />
									<label for="frequency3">Every 3 months</label>
									<input type="radio" class="filFreq" name="frequency" id="frequency6" value="6" checked="checked" />
									<label for="frequency6">Every 6 months</label>
									<input type="radio" class="filFreq" name="frequency" id="frequency12" value="12" />
									<label for="frequency12">Every 12 months</label>
								</div>
								<div class="comboContainer freqDropCont">
									<select name="freqSel" id="freqDrop" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-display="true">
										<option value="3">3</option>
										<option value="6" selected="selected">6</option>
										<option value="12">12</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<p>Place first order on:</p>
								<div class="input-append date" id="orderDate">
									<input class="dateInput" id="odInput" size="16" type="text">
									<span class="add-on"><i class="icon-th"></i></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="waterFilterQuantity">Qty</label>
								<input class="cafText" name="quantity" id="waterFilterQuantity" value="1" pattern="[0-9]*" />
							</div>
						</div>
					</fieldset>
					<div class="row-fluid">
						<div class="span2 offset1">
							<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="1" data-alert-id="alert_${uniqueId}1" data-this-toggle-id="toggle_${uniqueId}1">Continue</button>
						</div>
					</div>
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
				<span class="hidden"><i class="icon-pencil"></i> EDIT</span>
			</a>
		</div>
		<div id="${uniqueId}2" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafShippingAddressForm" method="post" action="">
					<fieldset>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingFirst">First Name <span>Required</span></label>
								<input type="text" class="cafText cafNameInput" name="fname" id="shippingFirst" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingLast">Last Name <span>Required</span></label>
								<input type="text" class="cafText cafNameInput" name="lname" id="shippingLast" />
							</div>
						</div>
						<div class="row-fluid cafCheckboxField">
							<div class="span10 offset1">
								<input type="checkbox" name="po" id="shippingPO" />
								<label for="shippingPO">This is a P.O. Box or Military Address. <a href="#modalShipping" data-toggle="modal">View shipping restrictions</a></label>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<div class="cafSameRow">
									<label for="shippingAddress">Street Address <span>Required</span></label>
									<input type="text" class="cafText cafAddress" name="address" id="shippingAddress" />
								</div>
								<div class="cafSameRow">
									<label for="shippingApt">Apt. # <span>Optional</span></label>
									<input type="text" class="cafText cafApt" name="apt" id="shippingApt" />
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingCity">City <span>Required</span></label>
								<input type="text" class="cafText cafCity" name="city" id="shippingCity" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingState">State <span>Required</span></label>
								<div class="comboContainer">
									<select name="state" id="shippingState" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-display="true">
										<option value="ZZ">--</option>
										<option value="AA">AA</option>
										<option value="AE">AE</option>
										<option value="AL">AL</option>
										<option value="AK">AK</option>
										<option value="AP">AP</option>
										<option value="AZ">AZ</option>
										<option value="AR">AR</option>
										<option value="CA">CA</option>
										<option value="CO">CO</option>
										<option value="DE">DE</option>
										<option value="DC">DC</option>
										<option value="FL">FL</option>
										<option value="GA">GA</option>
										<option value="GU">GU</option>
										<option value="HI">HI</option>
										<option value="ID">ID</option>
										<option value="IL">IL</option>
										<option value="IN">IN</option>
										<option value="IA">IA</option>
										<option value="KS">KS</option>
										<option value="KY">KY</option>
										<option value="LA">LA</option>
										<option value="ME">ME</option>
										<option value="MD">MD</option>
										<option value="MA">MA</option>
										<option value="MI">MI</option>
										<option value="MN">MN</option>
										<option value="MS">MS</option>
										<option value="MO">MO</option>
										<option value="MT">MT</option>
										<option value="NE">NE</option>
										<option value="NV">NV</option>
										<option value="NH">NH</option>
										<option value="NJ">NJ</option>
										<option value="NM">NM</option>
										<option value="NY">NY</option>
										<option value="NC">NC</option>
										<option value="ND">ND</option>
										<option value="OH">OH</option>
										<option value="OK">OK</option>
										<option value="OR">OR</option>
										<option value="PA">PA</option>
										<option value="PR">PR</option>
										<option value="RI">RI</option>
										<option value="SC">SC</option>
										<option value="SD">SD</option>
										<option value="TN">TN</option>
										<option value="TX">TX</option>
										<option value="UT">UT</option>
										<option value="VA">VA</option>
										<option value="VI">VI</option>
										<option value="VT">VT</option>
										<option value="WA">WA</option>
										<option value="WV">WV</option>
										<option value="WI">WI</option>
										<option value="WY">WY</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingZip">ZIP Code <span>Required</span></label>
								<input type="text" class="cafText cafZip" name="zip" id="shippingZip" pattern="[0-9]*" data-mask="99999" data-placeholder=" " />
							</div>
						</div>
						<div class="row-fluid countyRow hidden">
							<div class="span10 offset1">
								<label for="shippingCounty">Select County <span>Required</span></label>
								<div class="comboContainer countyWidth">
									<select name="year" id="shippingCounty" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-display="true">
										<option value="ZZ">----</option>
										<option value="DN">I don't know</option>
										<option value="NL">My City/County is not listed</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<div class="cafSameRow phoneWidth">
									<label for="shippingPhone">Phone Number <span>Required</span></label>
									<input type="text" class="cafText cafZip" name="phone" id="shippingPhone" pattern="[0-9]*" data-mask="999-999-9999" data-placeholder=" " />
								</div>
								<div class="cafSameRow">
									<label for="shippingExt">Ext. # <span>Optional</span></label>
									<input type="text" class="cafText cafExt" name="ext" id="shippingExt" pattern="[0-9]*" />
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingEmail">Email Address <span>Required</span></label>
								<input type="text" class="cafText cafCity" name="email" id="shippingEmail" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="shippingConfirm">Confirm Email Address <span>Required</span></label>
								<input type="text" class="cafText cafCity" name="confirm" id="shippingConfirm" />
							</div>
						</div>
						<div class="row-fluid cafCheckboxField">
							<div class="span10 offset1">
								<input type="checkbox" name="same" id="shippingSame" />
								<label for="shippingSame">Billing address same as shipping address</label>
							</div>
						</div>
					</fieldset>
					<div class="row-fluid">
						<div class="span2 offset1">
							<button type="button" class="new-btn new-btn-search cafSubmit hidden" id="shippingSubmit" data-submit="true" data-form-number="2" data-alert-id="alert_${uniqueId}2" data-this-toggle-id="toggle_${uniqueId}2">Continue</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}3">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle billingToggle" id="toggle_${uniqueId}3" data-toggle="false" data-status="unavailable" data-parent="#parent_${uniqueId}3" data-href="#${uniqueId}3">
				3 &nbsp; Enter Billing Address
				<span class="hidden"><i class="icon-pencil"></i> EDIT</span>
			</a>
		</div>
		<div id="${uniqueId}3" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafBillingAddressForm" method="post" action="">
					<fieldset>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="billingFirst">First Name <span>Required</span></label>
								<input type="text" class="cafText cafNameInput" name="fname" id="billingFirst" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="billingLast">Last Name <span>Required</span></label>
								<input type="text" class="cafText cafNameInput" name="lname" id="billingLast" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<div class="cafSameRow">
									<label for="billingAddress">Street Address <span>Required</span></label>
									<input type="text" class="cafText cafAddress" name="address" id="billingAddress" />
								</div>
								<div class="cafSameRow">
									<label for="billingApt">Apt. # <span>Optional</span></label>
									<input type="text" class="cafText cafApt" name="apt" id="billingApt" />
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="billingCity">City <span>Required</span></label>
								<input type="text" class="cafText cafCity" name="city" id="billingCity" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="billingState">State <span>Required</span></label>
								<div class="comboContainer billingStateDrop">
									<select name="state" id="billingState" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-display="true">
										<option value="ZZ">--</option>
										<option value="AA">AA</option>
										<option value="AE">AE</option>
										<option value="AL">AL</option>
										<option value="AK">AK</option>
										<option value="AP">AP</option>
										<option value="AZ">AZ</option>
										<option value="AR">AR</option>
										<option value="CA">CA</option>
										<option value="CO">CO</option>
										<option value="DE">DE</option>
										<option value="DC">DC</option>
										<option value="FL">FL</option>
										<option value="GA">GA</option>
										<option value="GU">GU</option>
										<option value="HI">HI</option>
										<option value="ID">ID</option>
										<option value="IL">IL</option>
										<option value="IN">IN</option>
										<option value="IA">IA</option>
										<option value="KS">KS</option>
										<option value="KY">KY</option>
										<option value="LA">LA</option>
										<option value="ME">ME</option>
										<option value="MD">MD</option>
										<option value="MA">MA</option>
										<option value="MI">MI</option>
										<option value="MN">MN</option>
										<option value="MS">MS</option>
										<option value="MO">MO</option>
										<option value="MT">MT</option>
										<option value="NE">NE</option>
										<option value="NV">NV</option>
										<option value="NH">NH</option>
										<option value="NJ">NJ</option>
										<option value="NM">NM</option>
										<option value="NY">NY</option>
										<option value="NC">NC</option>
										<option value="ND">ND</option>
										<option value="OH">OH</option>
										<option value="OK">OK</option>
										<option value="OR">OR</option>
										<option value="PA">PA</option>
										<option value="PR">PR</option>
										<option value="RI">RI</option>
										<option value="SC">SC</option>
										<option value="SD">SD</option>
										<option value="TN">TN</option>
										<option value="TX">TX</option>
										<option value="UT">UT</option>
										<option value="VA">VA</option>
										<option value="VI">VI</option>
										<option value="VT">VT</option>
										<option value="WA">WA</option>
										<option value="WV">WV</option>
										<option value="WI">WI</option>
										<option value="WY">WY</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="billingZip">ZIP Code <span>Required</span></label>
								<input type="text" class="cafText cafZip" name="zip" id="billingZip" pattern="[0-9]*" data-mask="99999" data-placeholder=" " />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<div class="cafSameRow phoneWidth">
									<label for="billingPhone">Phone Number <span>Required</span></label>
									<input type="text" class="cafText cafZip" name="phone" id="billingPhone" pattern="[0-9]*" data-mask="999-999-9999" data-placeholder=" " />
								</div>
								<div class="cafSameRow">
									<label for="billingExt">Ext. # <span>Optional</span></label>
									<input type="text" class="cafText cafExt" name="ext" id="billingExt" pattern="[0-9]*" />
								</div>
							</div>
						</div>
						<div class="row-fluid cafCheckboxField">
							<div class="span10 offset1">
								<input type="checkbox" name="same" id="billingSame" />
								<label for="billingSame">Use Shipping Address instead</label>
							</div>
						</div>
					</fieldset>
					<div class="row-fluid">
						<div class="span2 offset1">
							<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="3" data-alert-id="alert_${uniqueId}3" data-this-toggle-id="toggle_${uniqueId}3">Continue</button>
						</div>
					</div>
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
				<span class="hidden"><i class="icon-pencil"></i> EDIT</span>
			</a>
		</div>
		<div id="${uniqueId}4" class="accordion-body collapse">
			<div class="accordion-inner">
				<form id="cafPaymentForm" method="post" action="">
					<div class="row-fluid">
						<div class="span10 offset1">
							<div class="alert alert-error payAlert hidden">Not a valid credit card type.</div>
							<ul class="creditCards">
								<li class="iconSearsIcon"></li>
								<li class="iconSearsMasterCard creditCardIcon"></li>
								<li class="iconSearsCard creditCardIcon"></li>
							</ul>
							<ul class="creditCards">
								<li class="iconVisaCard creditCardIcon"></li>
								<li class="iconMasterCard creditCardIcon"></li>
								<li class="iconExpress creditCardIcon"></li>
								<li class="iconDiscover creditCardIcon"></li>
							</ul>
						</div>
					</div>
					<fieldset>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="payNumber">Card Number <span>Required</span></label>
								<input type="text" class="cafText cafCredit" name="cardNumber" id="payNumber" maxlength="16" pattern="[0-9]*" />
								<span id="payCardType"></span>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="payCode" class="narrowLabel">Security Code <span>Required</span></label>
								<a href="#modalCode" class="linkByLabel" data-toggle="modal">What's this?</a>
								<div class="clear"></div>
								<input type="text" class="cafText cafCode" name="code" id="payCode" maxlength="4" pattern="[0-9]*" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="payMonth">Expiration Date <span>Required</span></label>
								<div class="comboContainer monthWidth">
									<select name="month" id="payMonth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Month" data-display="true">
										<option value="ZZ">Month</option>
										<option value="01">January</option>
										<option value="02">February</option>
										<option value="03">March</option>
										<option value="04">April</option>
										<option value="05">May</option>
										<option value="06">June</option>
										<option value="07">July</option>
										<option value="08">August</option>
										<option value="09">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
								</div>
								<div class="comboContainer">
									<select name="year" id="payYear" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Year" data-display="true">
										<option value="ZZ">Year</option>
										<option value="2013">2013</option>
										<option value="2014">2014</option>
										<option value="2015">2015</option>
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
										<option value="2021">2021</option>
										<option value="2022">2022</option>
										<option value="2023">2023</option>
										<option value="2024">2024</option>
										<option value="2025">2025</option>
										<option value="2026">2026</option>
										<option value="2027">2027</option>
										<option value="2028">2028</option>
										<option value="2029">2029</option>
										<option value="2030">2030</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span10 offset1">
								<label for="payName">Name On Card <span>Required</span></label>
								<input type="text" class="cafText cafCity" name="name" id="payName" />
							</div>
						</div>
					</fieldset>
					<div class="row-fluid">
						<div class="span5 offset1">
							<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true" data-form-number="4" data-alert-id="alert_${uniqueId}4" data-this-toggle-id="toggle_${uniqueId}4">Continue</button>
							<p class="noteText">PLEASE NOTE: The credit card will not be charged at this time. The first charge will appear prior to the first water filter(s) in the subscription are shipped.</p>
						</div>
					</div>
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
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid darkRow pseudoTableRow">
							<div class="span2">
								<p>Product</p>
							</div>
							<div class="span8">
								<p id="filterLink">Product description</p>
							</div>
						</div>
						<div class="row-fluid pseudoTableRow">
							<div class="span2">
								<p>Price</p>
							</div>
							<div class="span8">
								<p id="cafFilterPrice">$34.23*</p>
								<p>*Price subject to change. Auto-reorders are fulfilled at the then current price of the water filter.  Email notification will be sent two weeks prior to auto-reorder date, which will include the final order price.</p>
							</div>
						</div>
						<div class="row-fluid darkRow pseudoTableRow">
							<div class="span2">
								<p>Order Qty</p>
							</div>
							<div class="span8">
								<p id="subQty">1</p>
							</div>
						</div>
						<div class="row-fluid pseudoTableRow">
							<div class="span2">
								<p>Reorder Frequency</p>
							</div>
							<div class="span8">
								<p>Every <span id="freqSel">[freq]</span> months, starting <span id="startDate">[date]</span>.</p>
							</div>
						</div>
						<div class="row-fluid darkRow pseudoTableRow">
							<div class="span2">
								<p>Shipping Method</p>
							</div>
							<div class="span8">
								<p>Standard Shipping (<span class="alert-ship">FREE</span>)</p>
							</div>
						</div>
						<div class="row-fluid pseudoTableRow">
							<div class="span2">
								<p>Estimated Delivery Date</p>
							</div>
							<div class="span8">
								<p>Approximately 2-4 business days after credit card is charged</p>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span4">
								<form id="finalForm" method="post" action="">
									<input type="hidden" name="partNumber" id="finalPartNumber" value="">
									<input type="hidden" name="productGroupId" id="finalGroupId" value="">
									<input type="hidden" name="supplierId" id="finalSupplierId" value="">
									<input type="hidden" name="po" id="finalPO" value="false">
									<input type="hidden" name="cardType" id="finalCardType" value="">
									<input type="hidden" name="geocode" id="finalGeocode" value="">
									<button type="button" class="new-btn new-btn-search" id="finalSubmit">Start subscription</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="modalShipping" class="modal hide fade in" style="display: none; ">
	<h3>Shipping Restrictions</h3>
	<h4>Can I ship an order to a P.O. Box?</h4>
	<p>The U.S. Postal Service delivers all packages addressed to P.O. boxes within the Continental U.S. We can't deliver to P.O. boxes outside of the Continental U.S.</p>
	<p><strong>Please note:</strong> Some items can't be shipped to a P.O. box and require a physical address for delivery.</p>
	<h4>Can orders be shipped to a military address?</h4>
	<p>The U.S. Postal Service delivers all orders to military APO/FPO addresses. Please allow an additional 4-6 weeks for the military postal service to deliver your package to its final overseas destination.</p>
	<a href="#" class="btn" data-dismiss="modal">Close</a>
</div>
<div id="modalCode" class="modal hide fade in" style="display: none; ">
	<h3>How to find your security code</h3>
	<p>The security code can be found on either the front or back of your credit card.</p>
	<div class="row-fluid">
		<div class="span7">
			<div class="cardSears creditCard"></div>
		</div>
		<div class="span5">
			<p>The security code is on the back of Sears Card and Sears Premier Card. <strong>If you have a 13-digit Sears Card, the security code is not required.</strong></p>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span7">
			<div class="cardOther creditCard"></div>
		</div>
		<div class="span5">
			<p>The security code is on the back of MasterCard, Sears Gold MasterCard VISA, and Discover cards.</p>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span7">
			<div class="cardExpress creditCard"></div>
		</div>
		<div class="span5">
			<p>The security code is on the front of American Express cards.</p>
		</div>
	</div>
	<a href="#" class="btn" data-dismiss="modal">Close</a>
</div>
<div id="processingIcon" class="addToCartAnimation">
	<i class="icon-refresh">&nbsp;</i>
	<p>Placing order</p>
</div>