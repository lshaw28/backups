<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:resolveHazardTipWarning adhocField="noticeText" choiceField="tipChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder=""/></c:set>
<c:if test="${empty noticeIcon}"><c:set var="noticeIcon" value="icon-info" /></c:if>
<cq:include path="/etc/spdAssets/globalConfig/tipPrefix" resourceType="searspartsdirect/components/content/tipPrefix" />

<div class="row-fluid">
	<div class="span12">
		<p><i class="${noticeIcon} pull-left">&nbsp;</i><em><c:out value="${tipPrefix}" /></em> <c:out value="${htwText}" /></p>
	</div>
</div>