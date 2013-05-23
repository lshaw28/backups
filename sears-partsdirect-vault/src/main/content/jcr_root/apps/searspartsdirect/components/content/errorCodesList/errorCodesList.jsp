<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:tagsByPage tagType="product"/>

<p>error code list</p>
<spd:Image path="errorCodeIcon"/>
<cq:text property="linktext" placeholder=""/>
<cq:text property="linkurl" placeholder=""/>

<h3><c:if test="${productTag != null}">${productTag.title} <cq:text property="errorCodeTitle" placeholder=""/></h3>

<cq:text property="errorCodeDescription" placeholder=""/>

