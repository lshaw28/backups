<%@ include file="/apps/searspartsdirect/global.jsp" %>
<ul>
	<li class="loginNavHome"><a href="${mainSitePath}"><i class="icon-home">&nbsp;</i><span class="text-home">Home</span></a>
	<li>
		<c:choose>
			<c:when test="${loggedIn}">
				Hello, <strong><c:out value="${firstName} "/></strong><a href="${mainSitePath}/partsdirect/myprofile/logout.action">Logout</a>
			</c:when>
			<c:otherwise>
				<a data-toggle="modal" data-target="#loginModal">Login</a>
			</c:otherwise>
		</c:choose>
	</li>
	<li>
		<c:choose>
			<c:when test="${loggedIn}">
				<a href="${mainSitePath}/partsdirect/myProfile.pd">My Profile</a>
			</c:when>
			<c:otherwise>
				<a data-toggle="modal" data-target="#registerModal">Register</a>
			</c:otherwise>
		</c:choose>
	</li>
	<li><a href="${mainSitePath}/partsdirect/orderStatus.pd?pop=flush" title="Order status">Order lookup</a></li>
	<li><a href="#GetHelp" id="loginNavGetHelp">Get help</a></li>
	<li><a href="http://searshc.us2.qualtrics.com/SE/?SID=SV_5BkCXqmfocMN11W" onClick="window.open(this.href, 'GiveFeedback', 'resizable=no,status=no,location=no,toolbar=no,menubar=no,fullscreen=no,scrollbars=yes,dependent=no, width=950,height=650,top=0,left=150'); return false;">Feedback</a></li>
</ul>