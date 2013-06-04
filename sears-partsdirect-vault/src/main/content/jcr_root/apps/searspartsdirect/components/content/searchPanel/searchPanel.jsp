<%@ include file="/apps/searspartsdirect/global.jsp" %>
<form id="searchBarForm" method="post" action="http://www.searspartsdirect.com/partsdirect/index.action">
	<div id="searchLeftShadow"></div>
	<div id="searchContainer">
		<ul id="searchTabs">
			<li id="searchTab" class="header-search-tab"> <a href="#search_input_tab"><i class="icon-search">&nbsp;</i> Search</a></li>
			<li id="headerManualTab" class="header-search-tab"><a href="#manuals_help_tab">Manuals & Repair Help</a></li>
		</ul>

		<div id="searchContent">
			<input id="searchBarField" class="text-input" type="text" maxlength="100" tabindex="1" data-inputhelp="Enter model or part number" data-inputhelpmobile="Model/Part #" name="searchModPar"><span class="large">in</span><div class="btn-group ">
				<a class="btn btn-type" href="#" data-toggle="dropdown"><span data-texthelp="Select Type" data-texthelpmobile="Type">Select Type</span> <i class="icon-chevron-sign-down">&nbsp;</i></a>
				<ul class="dropdown-menu">
					<li class="selected">
						<a href="#">Model #</a>
					</li>
					<li>
						<a href="#">Part #</a>
					</li>
				</ul>
			</div><a class="btn btn-search" href="javascript:void(0);">Search</a>
			<p>Can't locate your model number? <a href="javascript:void(0);" id="newFinderModel">Use our finder <i class="icon-chevron-sign-down">&nbsp;</i></a></p>
			<input name="pathTaken" id="pathTaken" value="partSearch" type="hidden"/>
			<input name="prst" id="prst" value="0" type="hidden"/>
			<input name="shdMod" id="shdMod" value="" type="hidden"/>
			<input name="shdPart" id="shdPart" value="" type="hidden"/>
		</div>
	</div>
	<div id="searchRightShadow"></div>
</form>