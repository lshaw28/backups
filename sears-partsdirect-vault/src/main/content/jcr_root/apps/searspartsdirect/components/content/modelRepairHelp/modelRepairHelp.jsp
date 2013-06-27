<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!-- make the PD api call by passing the model, brand id and category id and show the information (brand name, category name, symptoms, frequencies etc) below -->
<h2>Model Repair help<cq:text property="text" placeholder=""/></h2>

<p><cq:text property="description" placeholder=""/></p>
<spd:RepairModelHelp />

<c:if test="${errorCodesExist eq true}">
	<Refrigerator> returning an error code? <a href="errorCodePagePath">View <Refrigerator> Error Codes</a>
</c:if>