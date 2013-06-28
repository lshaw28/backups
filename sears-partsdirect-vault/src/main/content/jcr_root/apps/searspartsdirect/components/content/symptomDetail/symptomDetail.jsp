<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:GetJobCodesBySymptom />

These repairs may help solve your problem:

<c:if test="${symptomJobCodes != null}">
	<h2>${symptomJobCodes.description}</h2>
</c:if>

<c:forEach var="jobCode" items="${symptomJobCodes.recoveryCodesModel}">
	 <b>${jobCode.codeId}</b>
	 <p>${jobCode.description}</p>
	 
	 <table border="1">
	 <c:forEach var="part" items="${jobCode.recoveryPartsModel}">
	 <tr>
	 	<td></td>
	 	<td>
			 <a href="${mainSitePath}/partsdirect/part-number/${part.jobCodePart.partCompositeKey.partNumber}/${part.jobCodePart.partCompositeKey.productGroupId}/${part.jobCodePart.partCompositeKey.supplierId}">${part.jobCodePart.description}</a><br/>
			 	  Part #${part.jobCodePart.partCompositeKey.partNumber}
		</td>
	 	<td>
	 		<img src="${part.jobCodePart.partImage.imageURL}" height="75px" width="75px"/>
	 	</td>
	 	<td> $${part.jobCodePart.priceAndAvailability.sellingPrice} -- ${part.jobCodePart.priceAndAvailability.availabilityStatus}</td>
	 </tr>	   
	</c:forEach>
	</table>
</c:forEach>
