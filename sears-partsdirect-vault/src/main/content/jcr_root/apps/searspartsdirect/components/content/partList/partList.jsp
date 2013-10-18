<%@ include file="/apps/searspartsdirect/global.jsp"%><%
	// useDiagram should be a boolean set by a dialog checkbox
	// For now, I am hardcoding it
%><c:set var="useDiagram"><cq:text property="useDiagram" placeholder=""/></c:set><%
%><c:choose>
	<c:when test="${useDiagram eq 'yes'}">
		<c:set var="partListClass" value="span8" />
	</c:when>
	<c:otherwise>
		<c:set var="partListClass" value="span12" />
	</c:otherwise>
</c:choose><%
%><div class="row-fluid"><%
%><c:if test="${useDiagram eq 'yes'}"><%
%>	<div class="span4">
		<cq:include path="responsivePinchImage" resourceType="searspartsdirect/components/content/responsivePinchImage" />
	</div><%
%></c:if>
	<div class="${partListClass}}">
<%	// This should be a for each going through each part
	// I don't know how these are supposed to be populated %>
		<div class="partListItem">
			<div class="span1 diagramPosition">
			</div>
			<div class="span6 partListItemDescription">
			</div>
			<div class="span4 partListItemCart">
			</div>
		</div>
<% // End for each %>
	</div>
</div>