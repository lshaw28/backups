<%@ include file="/apps/searspartsdirect/global.jsp" %>
<ul>
	<li class="loginNavHome"><a href="#">Home</a>
	<li><a data-toggle="modal" data-target="#loginModal"><spd:getLoginStatus/></a></li>
	<li><a data-toggle="modal" data-target="#registerModal"><spd:getRegisterStatus/></a></li>
	<li><a href="${mainSitePath}/partsdirect/orderStatus.pd?pop=flush" title="Order status">Order lookup</a></li>
	<li><a href="javascript:void(0);" id="getHelpTopLink">Get help</a></li>
	<li><a href="http://searshc.us2.qualtrics.com/SE/?SID=SV_5BkCXqmfocMN11W" onClick="window.open(this.href, 'GiveFeedback', 'resizable=no,status=no,location=no,toolbar=no,menubar=no,fullscreen=no,scrollbars=yes,dependent=no, width=950, height =650, top=0, left=150'); return false;">Feedback</a></li>
</ul>