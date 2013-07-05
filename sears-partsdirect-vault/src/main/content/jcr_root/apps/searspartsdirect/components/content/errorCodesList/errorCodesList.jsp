<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getUrlRelation relationType="productCategory" />
<spd:getUrlRelation relationType="brand" />

<h3>
	<c:if test="${productCategoryRelation != null}">
		<c:out value="${productCategoryRelation.title}" />
	</c:if>&nbsp;
	<cq:text property="errorCodeTitle" placeholder=""/>
</h3>
<cq:text property="errorCodeDescription" placeholder=""/>

<spd:getErrorCodesList categoryPath="${productCategoryRelation.path}" />
<c:choose>
	<c:when test="${not empty brandRelation}">
		<c:forEach var="item" items="${errorCodeList}">
			<c:if test="${brandRelation.title eq item.key.title}">
				<table border="1">
					<tr><td><c:out value="${item.key.title}" /> <!-- ${item.key.description}--></td><td><spd:displayImage path="${item.key.logoPath}"/></td>
					<c:forEach var="errorCodeTable" items="${item.value}">
						<spd:linkResolver value="${errorCodeTable.path}" />
						<tr><td colspan="2"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></td></tr>
					</c:forEach>
				</table>
			</c:if>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${errorCodeList}">
			<table border="1">
				<tr><td><c:out value="${item.key.title}" /> <!-- ${item.key.description}--></td><td><spd:displayImage path="${item.key.logoPath}"/></td>
				<c:forEach var="errorCodeTable" items="${item.value}">
					<spd:linkResolver value="${errorCodeTable.path}" />
					<tr><td colspan="2"><a href="${url}"><c:out value="${errorCodeTable.title}" /></a></td></tr>
				</c:forEach>
			</table>
		</c:forEach>
	</c:otherwise>
</c:choose>
