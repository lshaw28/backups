<%@ include file="/apps/searspartsdirect/global.jsp" %>


<spd:getRelation single="true" assetType="productCategory"/>

<c:choose>
	<c:when test="${productCategoryRelation != null}">
		<h2>
			<cq:text property="text1" placeholder=""/>
			${productCategoryRelation.title}
		</h2>
		<p><cq:text property="optionalDescription" placeholder=""/></p>

		<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
		<!--  now make the API call by passing the symptom id and get the data -->
		
		<spd:GetSymptomFrequency symptomList="${symptomList}"/>
		<!--  if any of the symptoms have frequency then show the results in table format -->
		
		
		
		<!--  else if no frequencies found for any of the symptoms -->
		<c:forEach var="symptom" items="${symptomList}" varStatus="currentItem">
		    <spd:getRelatedPages assetPath="${symptom.path}"/>

			<c:choose>
				<c:when test="${currentItem.count % 2 eq 1}">
					<div class="row-fluid">
				</c:when>
			</c:choose>
						<div class="span6">
							<c:choose>
								 <c:when test="${fn:length(relatedPages) eq 1}">
									<a href="${relatedPages[0].path}">${symptom.title}</a>
								 </c:when>
								 <c:otherwise>
									${symptom.title}
								 </c:otherwise>
							 </c:choose>
						</div>
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>No items are available.</p>
	</c:otherwise>
</c:choose>