<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHazardTipWarning adhocField="noticeText" choiceField="tipChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="icon-info"/></c:set>
<cq:include path="/etc/spdAssets/globalConfig/tipPrefix" resourceType="searspartsdirect/components/content/tipPrefix" />

<div class="row-fluid">
	<div class="span12">
		<p><i class="${noticeIcon} pull-left">&nbsp;</i><em><c:out value="${tipPrefix}" /></em> <c:out value="${htwText}" /></p>
	</div>
</div>