<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%-- Ben's tag to get the product name based on the current url.
  assume output will be a ${productCategory} Model object. adjust later
  
  tag name will be spd:GetUrlRelation,
  only parameter will be relationType,
  domain is "productCategory","brand","model",
  for brand or category return type will be an instance of the model type,
  for model will just be the string.
  --%>
  
<spd:GetUrlRelation relationType="productCategory" />

<h2>
<c:choose>
	<c:when test="${fn:length(productCategoryRelation.title) lt 38 }">
		<cq:text property="header" placeholder="${productCategoryRelation.title} 101" />
	</c:when>
	<c:otherwise>
		<cq:text property="header" placeholder="${fn:substring(productCategoryRelation.title,0,37)} 101" />
	</c:otherwise>
	</c:choose>
</h2>

<%-- tag to get the product category pages based on category and cat101 tag
  assume output will be a ${category101Models} Model object. adjust later --%>
 
<spd:getCategory101Models category="${productCategoryRelation.path}"/>

<c:forEach var="category101Model" items="${category101Models}" varStatus="currentItem">
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 1}">
			<div class="row-fluid">
		</c:when>
	</c:choose>
	<div class="span6">
		<spd:LinkResolver value="${category101Model.url}" />
	<c:if test="${not empty category101Model.imagePath}">
		<a href="${url}" ><spd:displayImage path="${category101Model.imagePath}" decorated="false" /></a>
	</c:if>
	<h4>
		<a href="${url}">${category101Model.title}</a>
	</h4>
	<p>${category101Model.description}</p>
	</div>
	<c:choose>
		<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
			</div>
		</c:when>
	</c:choose>
</c:forEach>






<%--
<spd:tagsByPage tagType="parent_categories" />
<spd:getMultifieldCategories />
<spd:getRelation single="true" assetType="productCategory" />
<spd:getNameByNodePath nodePath="${productCategoryRelation.path}" />

<c:if test="${not empty categories}">
	<h2>
		<c:choose>
			<c:when test="${fn:length(parent_categoriesList[0].title) lt 38 }">
				<cq:text property="header" placeholder="${parent_categoriesList[0].title} 101" />
			</c:when>
			<c:otherwise>
				<cq:text property="header" placeholder="${fn:substring(parent_categoriesList[0].title,0,37)} 101" />
			</c:otherwise>
		</c:choose>
	</h2>

	<c:forEach var="category" items="${categories}" varStatus="currentItem">
		<c:choose>
			<c:when test="${currentItem.count % 2 eq 1}">
				<div class="row-fluid">
			</c:when>
		</c:choose>
		<div class="span6">
			<spd:LinkResolver value="${category.url}" />
			<c:if test="${not empty category.imagePath}">
				<a href="${url}" ><spd:displayImage path="${category.imagePath}" decorated="false" /></a>
			</c:if>
			<h4>
				<a href="${url}">${category.title}</a>
			</h4>
			<p>${category.description}</p>
		</div>
		<c:choose>
			<c:when test="${currentItem.count % 2 eq 0 or currentItem.last}">
				</div>
			</c:when>
		</c:choose>
	</c:forEach>
	
	<p>
	<c:set var="linkText"><cq:text property='viewAllLinkText'/></c:set>
	<c:if test="${not empty linkText}">
		<c:set var="homePath" value="/content/searspartsdirect/en/allarticles" />
		<c:set var="suffix" value="-repair/repair-articles" />
		<spd:LinkResolver value="${homePath}/${nodeName}${suffix}" />
    	<a class="new-btn-small" href="${url}">${linkText}</a>
	</c:if>
	</p>
</c:if>
--%>