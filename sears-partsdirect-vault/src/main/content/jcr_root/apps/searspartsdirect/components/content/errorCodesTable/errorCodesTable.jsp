<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory"/>
<spd:getRelation single="true" assetType="brand"/>
<spd:tagsByPage tagType="subcategories"/>

<h3><c:if test="${brandRelation != null}">${brandRelation.title}&nbsp;</c:if>
	<c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}&nbsp;</c:if>
	<c:if test="${fn:length(subcategoriesList) eq 1}">
			${subcategoriesList[0].title}
			<c:set var="subCatUrl" value="${subcategoriesList[0].tagID}"/>
	</c:if>
	<cq:text property="errorCodeText" placeholder=""/>
</h3>

<cq:text property="errorCodeDesc" placeholder="" tagName="p" />

<spd:errorCodeTable/>

<div class="errorCodesTable-header">
	<%-- @TODO Add Type headline --%>
	Error Code Type
</div>

<div class="table-wrapper">
	<table class="responsive-table">
		<thead>
			<tr>
				<th class="tbl-col-error-code">Error Code</th>
				<th class="tbl-col-condition">Condition</th>
				<th class="tbl-col-check-repair">Check/Repair</th>
				<th class="tbl-col-show-parts">Shop Parts</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${errorCodeTableData}">
				<c:forEach var="model" items="${item.value}">
					<tr>
						<td>${model.code}</td>
						<td>${model.condition}</td>
						<c:choose>
							<c:when test='${fn:contains(model.repairPath, "/")}'>
								<spd:linkResolver value="${model.repairPath}" />
								<td>
									<a href="${url}">Repair or Installation guide link</a>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									${model.repairPath}
								</td>
							</c:otherwise>
						</c:choose>
						<td>
							<%-- @TODO determine requirements for this column and impl --%>
							Browse Path Link
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</div>