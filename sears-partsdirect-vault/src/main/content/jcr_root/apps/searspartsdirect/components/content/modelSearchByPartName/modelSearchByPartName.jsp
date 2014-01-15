<%@ include file="/apps/searspartsdirect/global.jsp"%>
<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String description = (request.getParameter("description") != null) ? request.getParameter("description") : "";
%>

<div class="row-fluid">
	<div class="new-span-general partListItems" id="partNameResults">
	</div>
</div>

<script>modelSearchByPartName('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=description%>');</script>