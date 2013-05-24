<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p>For the manuals, repair guides, and specific part recommendations, enter your model number.</p>
<form class="form-inline">
	<input type="text" id="modelNumberSearchInput" name="modelNumberSearchInput" maxlength="100" data-inputhelp="Enter Model #" data-inputhelpmobile="Model #" />
	<button><cq:text property="buttonTitle" placeholder="Search"/></button>
</form>
<p><a href="#manuals_help_tab">Help me find my model number</a></p>