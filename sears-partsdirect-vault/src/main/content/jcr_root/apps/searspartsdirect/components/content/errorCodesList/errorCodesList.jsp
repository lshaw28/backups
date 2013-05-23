<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:tagsByPage tagType="product"/>

<p>error code list</p>
<h3><c:if test="${productTag != null}">${productTag.title}</c:if> <cq:text property="errorCodeTitle" placeholder=""/></h3>

<cq:text property="errorCodeDescription" placeholder=""/>

 
<spd:getPagesByTag categoryName="${productTag.title}" />

<c:forEach var="errorCode" items="${errorCodeList}">
	<p> ${errorCode.key}</p>
	<c:forEach var="subCategory" items="${errorCode.value}">
		${subCategory}<br/>
	</c:forEach>
</c:forEach>
