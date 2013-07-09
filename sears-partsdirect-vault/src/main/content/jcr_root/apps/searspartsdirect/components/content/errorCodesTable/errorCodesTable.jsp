<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:errorCodeTable/>

<c:forEach var="item" items="${errorCodeTableData}">
	<c:forEach var="model" items="${item.value}">
		<div class="errorCodesTable-header">
			${item.key}
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
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:forEach>