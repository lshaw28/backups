<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>
<spd:uniqueID />

<div class="row-fluid">
	<div class="span12">
		<h1>Subscription Confirmation # <span id="confirmNo">9999999</span> <a href="${mainSitePath}/partsdirect/myprofile/logout.action" onclick="SPDUtils.setCookie(\'username\', \'\');" class="new-btn new-btn-search">Log Out</a></h1>
	</div>
</div>
<div class="row-fluid">
	<div class="span6 infoBlock">
		<h3>Shipping Info</h3>
		<p><strong>Order will be shipped to:</strong><br />
		<span id="confirmShipFirst">Fname</span> <span id="confirmShipLast">Lname</span><br />
		<span id="confirmShipAddress">Address</span><br />
		<span id="confirmShipCity">City</span>, <span id="confirmShipState">State</span> <span id="confirmShipZip">Zip</span></p>
		<p><strong>Shipping via:</strong><br />
		Standard Shipping (FREE)</p>
	</div>
	<div class="span6 infoBlock">
		<h3>Billing Info</h3>
		<p><strong>Order will be shipped to:</strong><br />
		<span id="confirmBillFirst">Fname</span> <span id="confirmBillLast">Lname</span><br />
		<span id="confirmBillAddress">Address</span><br />
		<span id="confirmBillCity">City</span>, <span id="confirmBillState">State</span> <span id="confirmBillZip">Zip</span></p>
		<p><strong>Payment type:</strong><br />
		Card: <span id="confirmBillCardType">Visa</span><br />
		************<span id="confirmBillCardNo">9999</span></p>
		<p>*Please Note*<br />
		The credit card on file will be charged around Month day, year. The first water filter(s) will arrive approximately 2-4 business days after the credit card is charged.</p>
	</div>
</div>
<div class="row-fluid">
	<div class="span12">
		<p>A confirmation email has been sent to <span id="confirmEmail">email@example.com</span>.</p>
		<p>Please remind the customer to record the Subscription Confirmation Number for reference when inquiring about this subscription.</p>
		<p>By enrolling in the Sears Filter Replacement Plan (FRP), the customer has consented that the credit card account designated will be charged for the current market price of each filter, as of the date of shipping, plus sales tax. They will be notified by email, at the email account provided, of the exact cost of the filter before it is shipped to them, unless they notify us to take other action.</p>
		<p>The customer will receive filter(s) shipped to their home <strong>every 6 months</strong>. If they have questions or would like to make updates to their membership, they can call 1-800-366-PART (7278).</p>
	</div>
</div>
<div class="row-fluid detailHeader">
	<div class="span12">
		<h3>Subscription Detail</h3>
	</div>
</div>
<div class="row-fluid">
	<div class="span10 offset1">
		<table class="responsiveTable alwaysOpen">
			<thead>
				<tr>
					<th>Part #</th>
					<th>Description</th>
					<th>Qty</th>
					<th>Unit Price</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span id="confirmPartNo">XXXXXXX</span></td>
					<td><span id="confirmPartDesc">desc</span></td>
					<td><span id="confirmQty">X</span></td>
					<td>$<span id="confirmUnitPrice">XX.XX</span></td>
					<td>$<span id="confirmTotalPrice">XX.XX</span></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>