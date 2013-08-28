<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="com.day.cq.commons.jcr.JcrConstants" %>

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