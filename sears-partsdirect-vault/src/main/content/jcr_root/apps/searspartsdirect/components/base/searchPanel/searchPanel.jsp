<%@ include file="/apps/searspartsdirect/global.jsp" %>
<form id="searchBarForm" method="post">
	<div id="searchLeftShadow" class="search-shadow"></div>
	<div id="searchContainer">
		<ul id="searchTabs">
			<li id="searchTab" class="header-search-tab"> <a href="#search_input_tab"><i class="icon-search">&nbsp;</i> Search</a></li>
			<li id="headerManualTab" class="header-search-tab"><a href="#manuals_help_tab">Manuals & Repair Help</a></li>
		</ul>

		<div id="searchContent">
			<input id="searchBarField" class="new-input-small" type="text" maxlength="100" tabindex="1" data-inputhelp="Enter model or part number" data-inputhelpmobile="Model/Part #" name="searchModPar"><span class="large">in</span><div class="btn-group ">
				<a class="btn btn-type" href="#" data-toggle="dropdown"><span data-texthelp="Select Type" data-texthelpmobile="Type">Select Type</span> <i class="icon-chevron-sign-down">&nbsp;</i></a>
				<ul class="dropdown-menu">
					<li class="selected">
						<a data-postpath="part-model" data-pathtaken="modelSearch">Model #</a>
					</li>
					<li>
						<a data-postpath="part-number" data-pathtaken="partSearch">Part #</a>
					</li>
				</ul>
			</div><button id="searchModelsParts" class="new-btn-small new-btn-search">Search</button>
			<p>Can't locate your model number? <a href="javascript:void(0);" id="newFinderModel">Use our finder <i class="icon-angle-down">&nbsp;</i></a></p>
			<input name="pathTaken" id="pathTaken" type="hidden"/>
			<input name="prst" id="prst" type="hidden"/>
			<input name="shdMod" id="shdMod" type="hidden"/>
			<input name="shdPart" id="shdPart" type="hidden"/>
		</div>
	</div>
	<div id="searchRightShadow" class="search-shadow"></div>
</form>