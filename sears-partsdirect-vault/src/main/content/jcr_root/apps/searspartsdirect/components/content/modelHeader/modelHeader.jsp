<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation />
<c:if test="${not empty brandRelation and not empty productCategoryRelation and not empty modelRelation}">
	<spd:getModelHeader model="${modelRelation}" />
	<c:if test="${not empty pseudoTabs}">
		<h1>Model # <c:out value="${modelRelation}" /> <c:out value="${brandRelation.title}" /> <c:out value="${productCategoryRelation.title}" /> </h1>
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