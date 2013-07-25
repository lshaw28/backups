<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:errorCodeTable/>

<c:if test="${not empty errorCodeTableData.errorCodeType}">
	<div class="errorCodesTable-header">
		${errorCodeTableData.errorCodeType}
	</div>
</c:if>

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
			<c:forEach var="errorCode" items="${errorCodeTableData.errorCodes}">
				<tr>
					<td><c:out value="${errorCode.code}" /></td>
					<td><c:out value="${errorCode.condition}" /></td>
					<td>
						<c:choose>
							<c:when test="${not empty errorCode.repairPath}">
								<spd:linkResolver value="${errorCode.repairPath}" />
									<a href="${url}">Repair or Installation guide link</a>
							</c:when>
							<c:otherwise>
								${errorCode.repairText}
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${not empty errorCode.shopPartsText}">
							${errorCode.shopPartsText}&nbsp;
						</c:if>
						<c:if test="${not empty errorCode.shopPartsLink}">
							<spd:linkResolver value="${errorCode.shopPartsLink}" />
							<a href="${url}">Browse for Parts</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
