<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHazardTipWarning adhocField="noticeText" choiceField="warningChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder=""/></c:set>
<c:if test="${empty noticeIcon}"><c:set var="noticeIcon" value="icon-warning-sign" /></c:if>
<cq:include path="/etc/spdAssets/globalConfig/warningPrefix" resourceType="searspartsdirect/components/content/warningPrefix" />

<div class="row-fluid">
	<div class="span12">
		<p><i class="${noticeIcon} pull-left">&nbsp;</i><em><c:out value="${warningPrefix}" /></em> <c:out value="${htwText}" /></p>
	</div>
</div>