<%@ include file="/apps/searspartsdirect/global.jsp" %>

<spd:tagsByPage tagType="product"/>
<h2><cq:text property="text1" placeholder=""/>  <c:if test="${productTag != null}">${productTag.title}</c:if>  <cq:text property="text2" placeholder=""/></h2>
<p><cq:text property="optionalDescription" placeholder=""/></p>

<c:if test="${productTag != null}">
	<spd:getCommonSymptoms categoryName="${productTag.title}" />
	<c:forEach items="${commonSymptoms}">
		 <a href="${commonSymptomUrl}">{commonSymptomText}</a>
	</c:forEach>
</c:if>