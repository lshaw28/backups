<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory"/>

<p>error code list</p>
<h3><c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}</c:if> <cq:text property="errorCodeTitle" placeholder=""/></h3>

<cq:text property="errorCodeDescription" placeholder=""/>

<spd:getErrorCodesData categoryName="${productCategoryRelation.path}" />
<c:forEach var="item" items="${finalErrorCodeList}">
	<p> ${item.key.title} -- ${item.key.description} <spd:displayImage path="${item.key.logoPath}"/></p>
	<c:forEach var="subCategory" items="${item.value}">
		<a href="errorCodeTablePageUrl">${subCategory.code} -- ${subCategory.condition}</a><br/>
	</c:forEach>
</c:forEach>


