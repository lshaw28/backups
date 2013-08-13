<%@ include file="/apps/searspartsdirect/global.jsp" %>
<div class="modelNumberSearchIntro">
	<c:set var="searchInstructions"><cq:text property="searchInstructions" placeholder="" /></c:set><%--
	--%><c:if test="${empty searchInstructions}"><c:set var="searchInstructions">For manuals, repair guides, and specific part recommendations, enter your model number.</c:set></c:if><%--
	--%><p><c:out value="${searchInstructions}"/></p>
</div>
<div class="modelNumberSearchInputs">
	<form class="form-inline">
		<div class="input-append">
		<input type="text" id="modelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" />
		<span class="add-on"><button><i class="icon-search">&nbsp;</i></button></span>
		</div>
	</form>
	<c:set var="linkText"><cq:text property="linkText" placeholder=""/></c:set><%--
	--%><c:if test="${empty linkText}"><c:set var="linkText">Help me find my model number</c:set></c:if><%--
	--%><p><a href="#brandBar" class="searchPanelFinder_js"><c:out value="${linkText}" /></a></p>
	<p class="display-message">&nbsp;</p>
</div>
<div class="modelNumberSearchContent">
	<% // @TODO: Category-specific link for part type shopping, e.g. evaporators %>
</div>