<%@ include file="/apps/searspartsdirect/global.jsp"%>

<h2>Browse Glossary</h2>
<spd:getBrowseGlossary/>
<c:if test="${not empty glossary}">
	<select data-toggle="responsive-dropdown" data-buttonclass="new-btn-dropdown" data-buttoncontent="Select" data-navigate="true">
	<c:forEach var="glossary" items="${glossary}">
		<option value="${fn:substring(glossary, 0, 1)}"><c:out value="${glossary}"/></option>
	</c:forEach>
	</select>
</c:if>