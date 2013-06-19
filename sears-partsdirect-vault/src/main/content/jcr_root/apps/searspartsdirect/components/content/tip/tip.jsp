<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:ResolveHazardTipWarning adhocField="noticeText" choiceField="tipChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="notice-icon-tip"/></c:set>

<div class="${noticeIcon}">&nbsp;</div><cq:include path="/etc/spdAssets/globalConfig/tipPrefix" resourceType="searspartsdirect/components/content/tipPrefix" /> ${htwText}