<%@ include file="/apps/searspartsdirect/global.jsp" %>
<!-- Global JavaScript -->
<script type="text/javascript" language="javascript" src="http://c.searspartsdirect.com/pd-web-consumer-3.110.20130521-03/assets/js/third-party/livePerson/mtagconfig.js"></script>
<script type="text/javascript" language="javascript" src="http://c.searspartsdirect.com/pd-web-consumer-3.110.20130521-03/assets/js/partsdirect/PartsDirectLivePerson.js"></script>
<cq:includeClientLib js="apps.searspartsdirect" />
<c:if test="${isEditMode or isDesignMode}">
	<!-- Author Mode JavaScript -->
	<cq:includeClientLib js="apps.searspartsdirect.cq.edit" />
</c:if>