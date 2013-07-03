<%@ include file="/apps/searspartsdirect/global.jsp" %>
<form id="searchBarForm" method="post">
	<div id="searchLeftShadow" class="search-shadow"></div>
	<div id="searchContainer">
		<ul id="searchTabs">
			<li id="searchTab" class="header-search-tab active"> <a data-toggle="tab" data-target="#searchContent"><i class="icon-search">&nbsp;</i> Search</a></li>
			<li id="headerManualTab" class="header-search-tab"><a data-toggle="tab" data-target="#manualContent">Manuals & Repair Help</a></li>
		</ul>

		<div id="searchContent" class="search-content-pane active">
			<input id="searchBarField" type="text" maxlength="100" tabindex="1" data-inputhelp="Enter model or part number" data-inputhelpmobile="Model/Part #" name="searchModPar"><span class="large">in</span><div class="btn-group ">
				<a class="btn btn-type" href="#" data-toggle="dropdown"><span id="searchTypeLabel" data-texthelp="Select Type" data-texthelpmobile="Type">Select Type</span> <i class="icon-chevron-sign-down">&nbsp;</i></a>
				<ul class="dropdown-menu">
					<li>
						<a data-postpath="part-model" data-pathtaken="modelSearch" data-label="Model">Model #</a>
					</li>
					<li>
						<a data-postpath="part-number" data-pathtaken="partSearch" data-label="Part">Part #</a>
					</li>
				</ul>
			</div><button id="searchModelsParts" class="new-btn new-btn-search">Search</button>
			<p>Can't locate your model number? <a data-toggle="reveal" data-target="#findMyModel" id="newFinderModel">Use our finder <i class="icon-angle-down">&nbsp;</i></a></p>
			<input name="pathTaken" id="pathTaken" type="hidden"/>
			<input name="prst" id="prst" type="hidden"/>
			<input name="shdMod" id="shdMod" type="hidden"/>
			<input name="shdPart" id="shdPart" type="hidden"/>

			<div id="findMyModel">
				<p>This is the Find My Model area.</p>
			</div>
		</div>
		<div id="manualContent" class="search-content-pane">
			<ul>
				<li>
					<span class="icon-stack pull-left">
						<i class="icon-circle icon-stack-base"></i>
						<i class="icon-file-alt icon-light"></i>
					</span>
					<h4><a href="${mainSitePath}/partsdirect/user-manuals">Get Manuals<br /><span class="hidden-phone">Find &amp; Download</span></h4>
				</li>
				<li>
					<span class="icon-stack pull-left">
						<i class="icon-circle icon-stack-base"></i>
						<i class="icon-file-alt icon-light"></i>
					</span>
					<h4><a href="${mainSitePath}/partsdirect/lawn-garden-guides">Lawn &amp; Garden<br /><span class="hidden-phone">Repair &amp; Care Tips</span></h4>
				</li>
				<li>
					<span class="icon-stack pull-left">
						<i class="icon-circle icon-stack-base"></i>
						<i class="icon-file-alt icon-light"></i>
					</span>
					<h4><a href="${mainSitePath}/partsdirect/category/appliances">Ask An Expert<br /><span class="hidden-phone">Have Questions?</span></a></h4>
				</li>
			</ul>
		</div>
	</div>
	<div id="searchRightShadow" class="search-shadow"></div>
</form>