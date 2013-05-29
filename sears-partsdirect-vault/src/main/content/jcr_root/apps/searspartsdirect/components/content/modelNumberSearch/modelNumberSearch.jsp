<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p><cq:text property="searchInstructions" placeholder="For manuals, repair guides, and specific part recommendations, enter your model number."/></p>
<form class="form-inline">
	<input type="text" id="modelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter your model number" data-inputhelpmobile="Model #" />
	<button><cq:text property="buttonTitle" placeholder="Search"/></button>
</form>
<p><a href="#manuals_help_tab"><cq:text property="linkText" placeholder="Help me find my model number"/></a></p>