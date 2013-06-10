<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory"/>
<h3>
	<c:if test="${productCategoryRelation != null}">
		${productCategoryRelation.title}
	</c:if>&nbsp;
	<cq:text property="errorCodeTitle" placeholder=""/>
</h3>
<cq:text property="errorCodeDescription" placeholder=""/>

<spd:GetErrorCodesList categoryPath="${productCategoryRelation.path}" />
<c:forEach var="item" items="${errorCodeList}">
	<table border="1">
		<tr><td>${item.key.title} <!-- ${item.key.description}--></td><td><spd:displayImage path="${item.key.logoPath}"/></td>
		<c:forEach var="errorCodeTable" items="${item.value}">
			<tr><td colspan="2"><a href="${errorCodeTable.path}.html">${errorCodeTable.title}</a></td></tr>
		</c:forEach>
	</table>
</c:forEach>


