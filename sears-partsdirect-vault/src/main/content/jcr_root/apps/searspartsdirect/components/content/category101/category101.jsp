<%@ include file="/apps/searspartsdirect/global.jsp"%>
<spd:uniqueID />
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getMultifieldArticles  category="${productCategoryRelation}" />

<c:if test="${not empty articles}">
	<c:choose>
		<c:when test="${fn:length(productCategoryRelation.title) lt 38 && fn:length(category101header) eq 0}">
			<c:set var="category101Title" value="${productCategoryRelation.title + ' 101'}" />
		</c:when>
		<c:when test="${fn:length(category101header) eq 0}">
			<c:set var="category101Title" value="${fn:substring(productCategoryRelation.title,0,37) + ' 101'}" />
		</c:when>
		<c:otherwise>
			<c:set var="category101Title" value="${category101header}" />
		</c:otherwise>
	</c:choose>
	<div class="category101Header">
		<a class="category101Mobile category101_js"><i class="icon-chevron-up"></i><i class="icon-chevron-down"></i>${category101Title}</a>
		<span class="category101Desktop">${category101Title}</span>
	</div>
	<div class="category101Body">
		<ul>
			<c:forEach var="article" items="${articles}" varStatus="currentItem">
				<spd:linkResolver value="${article.url}" />
				<c:choose>
					<c:when test="${currentItem.last}">
						<li class="last"><a href="${url}"><c:out value="${article.title} "/></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${url}"><c:out value="${article.title} "/></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<c:if test="${fn:length(category101linkText) gt 0}">
			<c:set var="suffix" value="-repair/repair-articles" />
			<spd:linkResolver value="${Constants.CATEGORIES_ROOT}/${productCategoryRelation.trueName}${suffix}" />
			<p><a href="${url}" class="new-btn-small"><c:out value="${category101linkText} "/></a></p>
		</c:if>
	</div>
</c:if>