<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHazardTipWarning adhocField="noticeText" choiceField="hazardChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="icon-ban-circle"/></c:set>
<cq:include path="/etc/spdAssets/globalConfig/hazardPrefix" resourceType="searspartsdirect/components/content/hazardPrefix" />

<div class="row-fluid">
	<div class="span12">
		<p><i class="${noticeIcon} pull-left">&nbsp;</i><em><c:out value="${hazardPrefix}" ></em> <c:out value="${htwText}" /></p>
	</div>
</div>