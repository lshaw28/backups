<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p>error code table</p>

<spd:getProductCategoryName />
<h3> ${productCategoryName} <cq:text property="errorCodeText"  placeholder=""/></h3>
<cq:text property="errorCodeDesc"  placeholder=""/>