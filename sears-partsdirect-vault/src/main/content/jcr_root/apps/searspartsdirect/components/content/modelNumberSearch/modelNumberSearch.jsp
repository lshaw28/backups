<%@ include file="/apps/searspartsdirect/global.jsp" %>
<p><cq:text property="searchInstructions"
			placeholder="For manuals, repair guides, and specific part recommendations, enter your model number."/></p>
<form class="form-inline">
	<div class="input-append">
		<input type="text" id="modelNumberSearchInput" name="modelNumberSearchInput" maxlength="42" data-inputhelp="Enter model number" data-inputhelpmobile="Model #" />
		<span class="add-on"><button><i class="icon-search">&nbsp;</i></button></span>
	</div>
</form>
<p><a href="#manuals_help_tab"><cq:text property="linkText" placeholder="Help me find my model number"/></a></p>