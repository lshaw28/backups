<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:ResolveHazardTipWarning adhocField="noticeText" choiceField="hazardChosen" placeholder=""/>
<c:set var="noticeIcon"><cq:text property="noticeIcon" placeholder="notice-icon-hazard"/></c:set>

<div class="${noticeIcon}">&nbsp;</div><cq:include path="/etc/spdAssets/globalConfig/hazardPrefix" resourceType="searspartsdirect/components/content/hazardPrefix" /> ${htwText}