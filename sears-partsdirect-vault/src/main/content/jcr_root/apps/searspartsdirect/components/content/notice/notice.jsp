<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:ResolveHazardTipWarning adhocField="noticeText" choiceField="warningChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="icon-warning-sign"/></c:set>
<cq:include path="/etc/spdAssets/globalConfig/warningPrefix" resourceType="searspartsdirect/components/content/warningPrefix" />

<div class="row-fluid">
	<div class="span12">
		<p><i class="${noticeIcon} pull-left">&nbsp;</i><em>${warningPrefix}</em> ${htwText}</p>
	</div>
</div>