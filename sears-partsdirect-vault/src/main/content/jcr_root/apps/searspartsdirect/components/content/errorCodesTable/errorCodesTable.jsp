<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory"/>
<spd:getRelation single="true" assetType="brand"/>
<spd:tagsByPage tagType="subcategories"/>

<h3> <c:if test="${brandRelation != null}">${brandRelation.title}&nbsp;</c:if>
	 <c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}&nbsp;</c:if>
	 <c:if test="${fn:length(subcategoriesList) eq 1}">
	 		${subcategoriesList[0].title} 
	 		<c:set var="subCatUrl" value="${subcategoriesList[0].tagID}"/>
	 </c:if>
	 <cq:text property="errorCodeText"  placeholder=""/>
</h3>
<cq:text property="errorCodeDesc"  placeholder=""/>


<%-- 
<spd:getErrorCodesData categoryPath="${productCategoryRelation.path}" subCategoryPath="${subCatUrl}" brandPath="${brandRelation.path}" />
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
--%>

<spd:errorCodeTable/>
<table>
	<tr>
		<td>Error Code</td>
		<td>condition</td>
		<td>check/repair</td>
		<td>shop parts</td>
	</tr>
	<c:forEach var="item" items="${errorCodeTableData}">
		<tr><td><b>${item.key}</b></td></tr>
		<c:forEach var="model" items="${item.value}">
			<tr>
				<td>${model.code}</td>
			 	<td>${model.condition}</td>
			 	<td>${model.repairPath}</td>
			 	<td>no parts</td>
			 </tr>
		</c:forEach>
	</c:forEach>
</table>
