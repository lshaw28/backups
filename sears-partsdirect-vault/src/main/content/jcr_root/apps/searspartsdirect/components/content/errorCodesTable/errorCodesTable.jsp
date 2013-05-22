<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p>error code table</p>
<spd:tagsByPage/>
<h3> <c:if test="${brandTag != null}">${brandTag.title}&nbsp;</c:if>
     <c:if test="${productTag != null}">${productTag.title}&nbsp;</c:if>
     <c:if test="${subCategoryTag != null}">${subCategoryTag.title}&nbsp;</c:if>
     <cq:text property="errorCodeText"  placeholder=""/></h3>
<cq:text property="errorCodeDesc"  placeholder=""/>