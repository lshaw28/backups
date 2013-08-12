<%@ include file="/apps/searspartsdirect/global.jsp"%>

<cq:include path="browseGlossary" resourceType="searspartsdirect/components/content/browseGlossary" />

<spd:getUrlRelation relationType="productCategory" />
<spd:getCommonParts categoryPath="${productCategoryRelation.path}"/>
<spd:uniqueID />
<%-- Within a single component, uniqueID is always the same, since it is generated off the component path within the page --%>
<c:set var="uniquer" value="0" />
<c:forEach var="commonPart" items="${commonParts}">
	<%-- @TODO: Check the first letter of the title --%>
	<%-- If this is the first instance of the first letter in this title, make it the name of a named anchor --%>
	<%-- So, replace A with whatever letter it is --%>
	<a name="A"></a>
	<%-- End If --%>
	<h2><c:out value="${commonPart.title}"/></h2>
	<div class="commonPartsContainerIndent">
		<p><c:out value="${commonPart.description}"/></p>

		<c:if test="${not empty commonPart.guides}">
			<c:set var="uniquer" value="${uniquer + 1}" />
			<div class="accordion" id="parent_${uniqueId}${uniquer}">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#parent_${uniqueId}${uniquer}" href="#${uniqueId}${uniquer}">
							<p>Common repairs using
							<c:choose>
								<c:when test="${not empty commonPart.pluralTitle}">
									<c:out value="${fn:toLowerCase(commonPart.pluralTitle)}"/>
								</c:when>
								<c:otherwise>
									<c:out value="${fn:toLowerCase(commonPart.title)}"/>
								</c:otherwise>
							</c:choose>
							</p>
							<i class="icon-chevron-up"></i><i class="icon-chevron-down"></i>
						</a>
					</div>
					<div id="${uniqueId}${uniquer}" class="accordion-body collapse">
						<div class="accordion-inner">
							<c:forEach var="guide" items="${commonPart.guides}">
								<spd:linkResolver value="${guide.url}"/>
									<a href="${url}"><c:out value="${guide.title}"/></a>
									<br />
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:set var="uniquer" value="${uniquer + 1}" />
		<div class="accordion" id="parent_${uniqueId}${uniquer}">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#parent_${uniqueId}${uniquer}" href="#${uniqueId}${uniquer}">
						<p>Shop for
							<c:choose>
								<c:when test="${not empty commonPart.pluralTitle}">
									<c:out value="${fn:toLowerCase(commonPart.pluralTitle)}"/>
								</c:when>
								<c:otherwise>
									<c:out value="${fn:toLowerCase(commonPart.title)}"/>
								</c:otherwise>
							</c:choose>
						</p>
						<i class="icon-chevron-up"></i><i class="icon-chevron-down"></i>
					</a>
				</div>
				<div id="${uniqueId}${uniquer}" class="accordion-body collapse">
					<div class="accordion-inner">
						<a href="${mainSitePath}/partsdirect/product-types/${productCategoryRelation.title}-Parts">Shop <c:choose>
							<c:when test="${not empty commonPart.pluralTitle}">
								<c:out value="${fn:toLowerCase(commonPart.pluralTitle)}"/>
							</c:when>
							<c:otherwise>
								<c:out value="${fn:toLowerCase(commonPart.title)}"/>
							</c:otherwise>
						</c:choose></a>, or for best results, search using your model number.
						<a data-toggle="reveal" data-target="#findMyModel" id="newFinderModel">Can't find your model number? Use our finder.</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>