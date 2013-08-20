<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>
<spd:uniqueID />

<div class="accordion" id="parent_${uniqueId}1">
	<div class="accordion-group">
		<div class="accordion-heading cafHeadingOpen">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}1" href="#${uniqueId}1">
				1 &nbsp; Select Water Filter and Reorder Frequency
			</a>
		</div>
		<div id="${uniqueId}1" class="accordion-body in">
			<div class="accordion-inner">
				<form id="cafSelectFilterFrequencyForm" method="post" action="" data-regulagroup="cafSelectFilterFrequency">
					<div class="alert alert-error hidden">&nbsp;</div>
					<div class="cafField">
						<p>Search by fridge model number or water filter part number</p>
						<div class="radioOptions">
							<input type="radio" name="numberType" id="ntModel" value="model" data-constraints="@Checked([message='Please select a search type'] , [groups='cafSelectFilterFrequency'])" />
							<label for="ntModel">Fridge Model #</label>
							<input type="radio" name="numberType" id="ntPart" value="part" data-constraints="@Checked([message='Please select a search type'] , [groups='cafSelectFilterFrequency'])" />
							<label for="ntPart">Filter Part #</label>
						</div>
						<input class="cafSpacedInput" name="number" value="Enter your part or model number" data-inputhelp="Enter your part or model number" />
						<p><a href="#findMyModel" class="searchPanelFinder_js">Can't locate the model number? Use our finder</a></p>
					</div>
					<div class="cafField">
						<p>Select water filter automatic reorder frequency</p>
						<div class="radioOptions">
							<input type="radio" name="frequency" id="f3" value="3" data-constraints="@Checked([message='Please select a frequency'] , [groups='cafSelectFilterFrequency'])" />
							<label for="f3">Every 3 months</label>
							<input type="radio" name="frequency" id="f6" value="6" data-constraints="@Checked([message='Please select a frequency'] , [groups='cafSelectFilterFrequency'])" />
							<label for="f6">Every 6 months</label>
							<input type="radio" name="frequency" id="f12" value="12" data-constraints="@Checked([message='Please select a frequency'] , [groups='cafSelectFilterFrequency'])" />
							<label for="f12">Every 12 months</label>
						</div>
					</div>
					<div class="cafField">
						<p>Starting from</p>
						<select name="startMonth" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
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
						<select name="startDay" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
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
						<select name="startYear" data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
						</select>
					</div>
					<div class="cafField">
						<p>Qty</p>
						<input class="cafQuantity" name="quantity" data-constraints="@Required([message='Please enter a quantity'] , [groups='cafSelectFilterFrequency']) @Numeric @NotBlank" />
					</div>
					<button type="button" class="new-btn new-btn-search cafSubmit" data-submit="true">Continue</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}2">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}2" href="#${uniqueId}2">
				2 &nbsp; Enter Shipping Address
			</a>
		</div>
		<div id="${uniqueId}2" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="cafField">
					<p>First Name</p>
					<input name="fname" />
				</div>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}3">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}3" href="#${uniqueId}3">
				3 &nbsp; Enter Billing Address
			</a>
		</div>
		<div id="${uniqueId}3" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="cafField">
					<p>First Name</p>
					<input name="fname" />
				</div>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}4">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}4" href="#${uniqueId}4">
				4 &nbsp; Enter Payment Information
			</a>
		</div>
		<div id="${uniqueId}4" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="cafField">
					<p>First Name</p>
					<input name="fname" />
				</div>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="parent_${uniqueId}5">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#parent_${uniqueId}5" href="#${uniqueId}5">
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