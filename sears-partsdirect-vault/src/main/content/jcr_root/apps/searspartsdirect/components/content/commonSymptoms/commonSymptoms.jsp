<%@ include file="/apps/searspartsdirect/global.jsp" %>
 This is a test
<spd:getRelation single="true" assetType="productCategory"/>
<h2><cq:text property="text1" placeholder=""/>  <c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}</c:if>  <cq:text property="text2" placeholder=""/></h2>
<p><cq:text property="optionalDescription" placeholder=""/></p>

<spd:getAssets assetType="symptom" productCategoryFilter="${productCategoryRelation.path}" />
<c:forEach var="symptom" items="${symptomList}">
	<a href="urlForCommonSymptoms">${symptom.title}</a>
</c:forEach>





