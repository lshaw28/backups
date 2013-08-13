<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:getRelation single="true" assetType="productCategory" />
<h2><cq:text property="text1" placeholder=""/>
<spd:tagsByPage tagType="subcategories"/>
<c:if test="${fn:length(subcategoriesList) eq 1}"> ${subcategoriesList[0].title} </c:if>
${productCategoryRelation.title}
<cq:text property="text2" placeholder=""/>
<p><cq:text property="optionalDescription" placeholder=""/></p></h2>
<c:choose>
	<c:when test="${productCategoryRelation != null}">
		<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
		<c:forEach var="symptom" items="${symptomList}" varStatus="currentItem">
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 1}">
					<div class="row-fluid">
				</c:when>
			</c:choose>
						<div class="span6">
							<p>Item: ${currentItem.count}, length: ${fn:length(symptomList)}</p>
					<c:set var="symptomUrl" value="/content/searspartsdirect/en/categories/${productCategoryRelation.trueName}-repair/symptom/${symptom.id}.html" />
						<a href="${symptomUrl}"><c:out value="${symptom.title} "/></a>
				</div>
			<c:choose>
				<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
</c:choose>