<%@ include file="/apps/searspartsdirect/global.jsp"%><%
%><spd:getRelation single="true" assetType="productCategory" /><%
%><c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if><%
%><spd:getRelatedArticles categoryPath="${productCategoryRelation.path}"/><%
%><c:if test="${not empty articles && not empty productCategoryRelation}">
	<h2><%
%><c:set var="newHeader"><cq:text property="newHeader" placeholder="" /></c:set><%
%><c:if test="${empty newHeader}"><c:set var="newHeader" value="Related Articles" /></c:if><%
%><c:out value="${newHeader}" /><%
%></h2>
	<div class="carousel-mobile-only">
		<div class="carouselWrapper row-fluid">
			<c:forEach var="article" items="${articles}">
			<div class="carousel-mobile-item span3">
				<spd:linkResolver value="${article.url}" />
				<p><a href="${url}"><spd:displayImage path="${article.imagePath}" decorated="false" /></a></p>
				<p><a href="${url}"><c:out value="${article.title}" /></a><br />
				<c:out value="${article.description}" /></p>
			</div>
			</c:forEach>
		</div>
	</div>
	<c:if test="${fn:length(articles) eq 4}">
	<div class="view-all">
		<c:set var="suffix" value="-repair/repair-articles" /><%
%><spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${productCategoryRelation.trueName}${suffix}" /><%
%><a href="${url}" class="new-btn">View All Articles</a>
	</div>
	</c:if>
</c:if>