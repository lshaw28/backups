<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:ResolveHazardTipWarning adhocField="noticeText" choiceField="warningChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="notice-icon-warning"/></c:set>

<div class="${noticeIcon}">&nbsp;</div><cq:include path="/etc/spdAssets/globalConfig/warningPrefix" resourceType="searspartsdirect/components/content/warningPrefix" /> ${htwText}