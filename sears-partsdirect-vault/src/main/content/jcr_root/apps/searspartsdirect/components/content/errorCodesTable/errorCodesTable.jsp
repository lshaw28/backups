<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p>error code table</p>
<spd:tagsByPage/>
<h3> <c:if test="${brandTag != null}">${brandTag.title}&nbsp;</c:if>
     <c:if test="${productTag != null}">${productTag.title}&nbsp;</c:if>
     <c:if test="${subCategoryTag != null}">${subCategoryTag.title}&nbsp;</c:if>
     <cq:text property="errorCodeText"  placeholder=""/></h3>
<cq:text property="errorCodeDesc"  placeholder=""/>

<spd:getPagesByTag categoryName="${productTag.title}" subCategoryName="" brandName="" />

<c:forEach var="code" items="${errorCodeTable}">
	<p>${code.key}</p>
	<c:forEach var="detail" items="${code.value}">
		${detail.code} ${detail.description}</br>
		<c:if test="${detail.condition != null}">${detail.condition}</c:if>
		<c:if test="${detail.repair != null}">${detail.repair}</c:if>
	</c:forEach>
</c:forEach>