<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory"/>
<spd:getRelation single="true" assetType="brand"/>
<%-- <spd:getRelation single="true" assetType="subcategory"/> --%>
  
<h3> <c:if test="${brandRelation != null}">${brandRelation.title}&nbsp;</c:if>
	 <c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}&nbsp;</c:if>
	 <c:if test="${subcategoryRelation != null}">${subcategory.title}&nbsp;</c:if>
	 <cq:text property="errorCodeText"  placeholder=""/>
</h3>
<cq:text property="errorCodeDesc"  placeholder=""/>

<spd:getErrorCodesData categoryName="${productCategoryRelation.path}" subCategoryName="" brandName="${brandRelation.path}" />
	<table>
		<th>Error Code</th>
		<th>condition</th>
		<th>check/repair</th>
		<th>shop parts</th>
		<c:forEach var="errorCode" items="${errorCodeTable}">
			<tr>
				<td>${errorCode.code}</td>
				<td>${errorCode.condition}</td>
				<td><a href="${errorCode.repairPath}">click here</a></td>
			</tr>	
		</c:forEach>
	</table>
<%-- 
<c:forEach var="code" items="${errorCodeTable}">
	<p>${code.key}</p>
	<c:forEach var="detail" items="${code.value}">
		${detail.code} ${detail.condition}</br>
		<c:if test="${detail.condition != null}">${detail.condition}</c:if>
		<c:if test="${detail.repairPath != null}">${detail.repairPath}</c:if>
	</c:forEach>
</c:forEach>
--%>