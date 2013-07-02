<%@ include file="/apps/searspartsdirect/global.jsp" %>

<p>symptom details page</p>
<spd:GetModelSymptomDetail />

<h1>${modelSymptom.symptomModel.title}</h1>
<p>${modelSymptom.symptomModel.description}</p>


<c:forEach var="jobCode" items="${modelSymptom.jobCodeModels}">
	<c:choose>
		<c:when test="${jobCode.partTypeModel != null &&  jobCode.partTypeModel.imagePath != null}">
			<spd:displayImage path="${jobCode.partTypeModel.imagePath}"/>
		</c:when>
		<c:otherwise>
			show a default no jobcode part type image
		</c:otherwise>
	</c:choose>	
	<h3>${jobCode.title}</h3>
	<p>${jobCode.description}</p>
	
	Guides:-
	 <c:if test="${not empty jobCode.guides}">
	 	<c:forEach var="guide" items="${jobCode.guides}">
	 		<spd:LinkResolver value="${guide.url}"/>
	 		<p><a href="${url}">${guide.title}</a></p>
	 	</c:forEach>
	 </c:if>
	 
	 <c:choose>
		<c:when test="${not empty jobCode.parts}">	
			 <%-- <table border="1">
					 <c:forEach var="part" items="${jobCode.recoveryPartsModel}">
						 <tr>
						 	<td>Used in this repair ${part.recoveryFrequency}% of the time</td>
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
			</table> --%>
		</c:when>
		<c:otherwise>
			<a href="http://www.searspartsdirect.com/partsdirect/part-model/Frigidaire-Parts/Cooktop-Parts/Model-33003/1428/0121050&partType=${jobCode.partTypeModel.title}">find a ${jobCode.partTypeModel.title} in this model</a>
		</c:otherwise>
		</c:choose>		
		
</c:forEach>


<%-- 
<spd:GetJobCodesBySymptom />

These repairs may help solve your problem:

<c:if test="${symptomJobCodes != null}">
	<h2>${symptomJobCodes.description}</h2>
</c:if>

<c:forEach var="jobCode" items="${symptomJobCodes.recoveryCodesModel}">
	 <b>${jobCode.codeId}</b>
	 
	<c:choose>
		<c:when test="${jobCode.partTypeModel != null &&  jobCode.partTypeModel.imagePath != null}">
			<spd:displayImage path="${jobCode.partTypeModel.imagePath}"/>
		</c:when>
		<c:otherwise>
			show a default no jobcode part type image
		</c:otherwise>
	</c:choose>	
		
	 <p>${jobCode.description}</p>
	 Guide:-
	 <c:if test="${not empty jobCode.guides}">
	 	<c:forEach var="guide" items="${jobCode.guides}">
	 		<a href="${guide.url}">${guide.title}</a>
	 	</c:forEach>
	 </c:if>
	
	<c:choose>
		<c:when test="${not empty jobCode.recoveryPartsModel}">	
			 <table border="1">
					 <c:forEach var="part" items="${jobCode.recoveryPartsModel}">
						 <tr>
						 	<td>Used in this repair ${part.recoveryFrequency}% of the time</td>
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
		</c:when>
		<c:otherwise>
			<a href="http://www.searspartsdirect.com/partsdirect/part-model/Frigidaire-Parts/Cooktop-Parts/Model-33003/1428/0121050&partType=${jobCode.partTypeModel.title}">Install a ${jobCode.partTypeModel.title}</a>
		</c:otherwise>
		</c:choose>	
</c:forEach>
--%>
