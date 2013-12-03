<%@ include file="/apps/searspartsdirect/global.jsp" %><%
%><form id="searchBarForm" method="post">
	<div id="searchContainer">
		<div id="searchLeftShadow" class="search-shadow"></div>
		<ul id="searchTabs">
			<li id="searchTab" class="header-search-tab active"> <a data-toggle="tab" data-target="#searchContent"><i class="icon-search">&nbsp;</i> Search</a></li>
			<li id="headerManualTab" class="header-search-tab"><a data-toggle="tab" data-target="#manualContent">Manuals & Repair Help</a></li>
		</ul>
		<!-- new dev starts here for PD2 -->
		<div id="searchContent" class="search-content-pane active">
			<input id="searchBarField" type="text" maxlength="100" tabindex="1" data-inputhelp="Enter model or part number" data-inputhelpmobile="Model/Part #" name="searchModPar"><span class="large">in</span><div class="btn-group ">
				<a class="btn btn-type" href="#" data-toggle="dropdown"><span id="searchTypeLabel" data-texthelp="Select Type" data-texthelpmobile="Type">Type</span> <i class="icon-chevron-sign-down">&nbsp;</i></a>
				<ul class="dropdown-menu">
					<li><a data-postpath="part-model" data-pathtaken="modelSearch" data-label="Model #">Model #</a></li>
					<li><a data-postpath="part-number" data-pathtaken="partSearch" data-label="Part #">Part #</a></li>
				</ul>
			</div><button id="searchModelsParts" class="new-btn new-btn-search">Search</button>
			<p class="modelFinderClose">Can't locate your model number? <a class="searchPanelFinder_js">Use our finder <i class="icon-chevron-down">&nbsp;</i></a></p>
			<p class="modelFinderOpen" style="display: none;"><a class="searchPanelFinder_js">Close the finder</a></p>
			<input name="pathTaken" id="pathTaken" type="hidden"/>
			<input name="prst" id="prst" type="hidden"/>
			<input name="shdMod" id="shdMod" type="hidden"/>
			<input name="shdPart" id="shdPart" type="hidden"/>
		</div>
		<div id="manualContent" class="search-content-pane">
			<ul>
				<li><span class="icon-stack pull-left"><i class="icon-circle icon-stack-base"></i><i class="<cq:text property="panelIcon1" placeholder="icon-file-alt" default="icon-file-alt" /> icon-light"></i></span><h4><a href="<cq:text property="url1" placeholder="${mainSitePath}/partsdirect/user-manuals" default="${mainSitePath}/partsdirect/user-manuals" />" target="<cq:text property="urlTarget1" placeholder="" default="" />"><cq:text property="header1" placeholder="Get Manuals" default="Get Manuals" /><br /><span class="hidden-phone"><cq:text property="subheader1" placeholder="Find &amp; Download" default="Find &amp; Download" /></span></a></h4></li>
				<li><span class="icon-stack pull-left"><i class="icon-circle icon-stack-base"></i><i class="<cq:text property="panelIcon2" placeholder="icon-wrench" default="icon-wrench" /> icon-light"></i></span><h4><a href="<cq:text property="url2" placeholder="${mainSitePath}/repair-help.html" default="${mainSitePath}/repair-help.html" />" target="<cq:text property="urlTarget2" placeholder="" default="" />"><cq:text property="header2" placeholder="Repair Help" default="Repair Help" /><br /><span class="hidden-phone"><cq:text property="subheader2" placeholder="Guides &amp; Care Tips" default="Guides &amp; Care Tips" /></span></a></h4></li>
				<li><span class="icon-stack pull-left"><i class="icon-circle icon-stack-base"></i><i class="<cq:text property="panelIcon3" placeholder="icon-file-alt" default="icon-file-alt" /> icon-light"></i></span><h4><a href="<cq:text property="url3" placeholder="${mainSitePath}/partsdirect/category/appliances" default="${mainSitePath}/partsdirect/category/appliances" />" target="<cq:text property="urlTarget3" placeholder="" default="" />"><cq:text property="header3" placeholder="Ask An Expert" default="Ask An Expert"/><br /><span class="hidden-phone"><cq:text property="subheader3" placeholder="Have Questions?" default="Have Questions?" /></span></a></h4></li>
			</ul>
		</div>
		<div id="searchRightShadow" class="search-shadow"></div>
		<a name="findMyModel"></a>
		<div class="modelFinder">
			<div class="modelFinderWrapper">
				<div class="modelFinderCriteria">
					<h3>Choose your product type</h3>
					<div class="modelFinderProductSelect dropdown-reskin">
						<i class="icon-angle-down"></i>
						<select></select>
					</div>
				</div>
				<div class="modelFinderPaneManager">
					<div class="item"><a href="#">Plate location</a></div>
					<div class="item"><a href="#">Common number model plates</a></div>
				</div>
				<div class="modelFinderOutput"></div>
				<div class="modelFinderHelper">
					<h3>Common model number plates</h3>
					<div class="modelFinderPlates">
						<img class="modelFinderPlatesImage1" src="/etc/designs/searspartsdirect/clientlib_base/img/search-model-help1.png" />
						<img class="modelFinderPlatesImage2" src="/etc/designs/searspartsdirect/clientlib_base/img/search-model-help2.png" />
						<img class="modelFinderPlatesImage3" src="/etc/designs/searspartsdirect/clientlib_base/img/search-model-help3.png" />
					</div>
					<%-- After a search, pane-manager triggers the display of this UI --%>
					<div class="modelFinderPlateFinder">
						<p><strong>View sample model numbers by selecting your product brand</strong></p>
						<div class="modelFinderPlateSelect dropdown-reskin">
							<i class="icon-angle-down"></i>
							<select></select>
						</div>
						<div class="modelFinderPlateOutput"></div>
					</div>
				</div>
				<div class="modelFinderCriteriaClose">
					<a class="searchPanelFinder_js">Close <i class="icon-angle-up"></i></a>
				</div>
			</div>
		</div>
	</div>
</form>