<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation />
<c:if test="${not empty brandRelation and not empty productCategoryRelation and not empty modelRelation}">
	<spd:getModelHeader model="${modelRelation}" />
	<c:if test="${not empty pseudoTabs}">

<spd:getHeaderHelperData />
<c:choose>
	<c:when test="${template eq '/apps/searspartsdirect/templates/categorySymptom'}" >
		<spd:getUrlRelation relationType="symptom" />
		<spd:getSymptomDetail id="${symptomRelation.id}" />
		<h1><c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /> <c:out value="${symptom.title}" /></h1>
	</c:when>
	<c:otherwise>
		<h1>Repair Symptoms for <c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> Model #<c:out value="${modelRelation}" /></h1>
	</c:otherwise>
</c:choose>	
		<ul class="visible-desktop visible-tablet">
			<c:forEach items="${pseudoTabs}" var="tab">
				<li<c:if test="${tab.linkText eq 'Repair Help'}"> class="active"</c:if>><a href="${tab.href}"><c:out value="${tab.linkText} "/></a></li>
			</c:forEach>
		</ul>
		<select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-groupclass="visible-phone" data-link="true">
			<option value="" selected="selected"><c:out value="${jumpToString}" /></option>
			<c:forEach items="${pseudoTabs}" var="tab">
				<option<c:if test="${tab.linkText eq 'Repair Help'}"> selected="selected"</c:if> value="${tab.href}"><c:out value="${tab.linkText} "/></option>
			</c:forEach>
		</select>
	</c:if>
</c:if>