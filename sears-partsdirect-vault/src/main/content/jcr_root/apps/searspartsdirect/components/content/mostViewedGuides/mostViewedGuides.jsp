<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spd:getRelation single="true" assetType="productCategory" />
<spd:getMostViewedGuides categoryPath="${productCategoryRelation.path}" />
<cq:text property="guidePreText"/> <c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}</c:if> <cq:text property="guidePostText"/><br />
<c:if test="${not empty guides}">
	<cq:text property="guidePreText"/> <c:if test="${productCategoryRelation != null}">${productCategoryRelation.title}</c:if> <cq:text property="guidePostText"/><br />
	
	<c:forEach var="guide" items="${guides}">
		<a href="${guide.pagePath}"><spd:displayImage path="${guide.imagePath}"/></a>
		<a href="${guide.pagePath}">${guide.title}</a>
		<br /><br />
	</c:forEach>
	
	<c:if test="${fn:length(guides) ge 4}">
		<a href="<cq:text property="viewAllItemsLink"/>.html"><cq:text property="viewAllItemsText"/></a>
	</c:if>
</c:if>
