<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory"/>
<h2>
	<cq:text property="text1" placeholder=""/>  
	<c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}</c:if>  
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





