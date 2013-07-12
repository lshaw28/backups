<%@ include file="/apps/searspartsdirect/global.jsp"%>

<spd:uniqueID />
<spd:getRelation single="true" assetType="productCategory" />
<c:if test="${empty productCategoryRelation}">
	<spd:getUrlRelation relationType="productCategory" />
</c:if>
<spd:getNameByNodePath nodePath="${productCategoryRelation.path}" />
<spd:getMultifieldArticles  categoryPath="${productCategoryRelation.path}" />
<c:if test="${not empty articles}">
	<div class="accordion" id="parent_${uniqueId}">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse101"
					data-parent="#parent_${uniqueId}" href="#${uniqueId}"> <c:choose>
						<c:when test="${fn:length(productCategoryRelation.title) lt 38 }">
							<cq:text property="header"
								placeholder="${productCategoryRelation.title} 101" />
						</c:when>
						<c:otherwise>
							<cq:text property="header"
								placeholder="${fn:substring(productCategoryRelation.title,0,37)} 101" />
						</c:otherwise>
					</c:choose> <i class="icon-chevron-up pull-right"></i> <i
					class="icon-chevron-down pull-right"></i>
				</a>
			</div>
			<div id="${uniqueId}" class="accordion-body collapse in">
				<div class="accordion-inner">
					<ul>
						<c:forEach var="article" items="${articles}">
							<spd:linkResolver value="${article.url}" />
							<li><a href="${url}">${article.title}</a></li>
						</c:forEach>
					</ul>
					<c:set var="linkText">
						<cq:text property='viewAllLinkText' />
					</c:set>
					<c:if test="${fn:length(linkText) gt 0}">
						<c:set var="suffix" value="-repair/repair-articles" />
						<spd:linkResolver
							value="${Constants.CATEGORIES_ROOT}/${nodeName}${suffix}" />
						<a href="${url}" class="new-btn-small accordion-inner-btn">${linkText}</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</c:if>