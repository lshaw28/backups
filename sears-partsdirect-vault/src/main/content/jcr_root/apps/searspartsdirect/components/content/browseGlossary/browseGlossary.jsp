<%@ include file="/apps/searspartsdirect/global.jsp"%>

<h2>Browse Glossary</h2>
<spd:getBrowseGlossary/>

<c:forEach var="glossary" items="${glossary}">
	<select>
	  <option value="${fn:substring(glossary, 0, 1)}"><c:out value="${glossary}"/></option>
	</select>
</c:forEach>