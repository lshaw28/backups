<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory"/>
<c:choose>
	<c:when test="${productCategoryRelation != null}">
		<h2>
			<cq:text property="text1" placeholder=""/>  
			${productCategoryRelation.title}
			<cq:text property="text2" placeholder=""/>
		</h2>
		<p><cq:text property="optionalDescription" placeholder=""/></p>
		
		<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
		
		<c:forEach var="symptom" items="${symptomList}">
		    <spd:getRelatedPages assetPath="${symptom.path}"/>
		   
			<c:choose>
				 <c:when test="${fn:length(relatedPages) eq 1}">
					<li><a href="${relatedPages[0].path}">${symptom.title}</a></li>
				 </c:when>
				 <c:otherwise>
				 	<li>${symptom.title}</li>
				 </c:otherwise>
			 </c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h2>Product category is not tagged</h2>
	</c:otherwise>
</c:choose>





