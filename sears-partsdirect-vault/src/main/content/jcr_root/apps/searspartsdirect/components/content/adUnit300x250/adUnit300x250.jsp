<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><spd:getRelation assetType="productCategory" single="true" /><%
%><c:if test="${empty productCategoryRelation}"><%
%><spd:getUrlRelation relationType="productCategory" /><%
%></c:if><%
%><c:if test="${not empty productCategoryRelation}"><%
%><spd:getImagePathTag resourcePath="${productCategoryRelation.path}" /><%
%><div class="adUnit_js" data-imageurl="${squareImage}" data-imagealt="${linkAlt}" data-linkurl="${linkURL}" data-linktarget="${linkTarget}"></div><%
%><c:if test="${imageCaption ne ''}">
<p><c:out value="${imageCaption}" /></p><%
%></c:if><%
%></c:if>