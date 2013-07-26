<%@ include file="/apps/searspartsdirect/global.jsp" %>
<form id="searchBarForm" method="post">
	<div id="searchContainer">
		<div id="searchLeftShadow" class="search-shadow"></div>
		<ul id="searchTabs">
			<li id="searchTab" class="header-search-tab active"> <a data-toggle="tab" data-target="#searchContent"><i class="icon-search">&nbsp;</i> Search</a></li>
			<li id="headerManualTab" class="header-search-tab"><a data-toggle="tab" data-target="#manualContent">Manuals & Repair Help</a></li>
		</ul>

		<div id="searchContent" class="search-content-pane active">
			<input id="searchBarField" type="text" maxlength="100" tabindex="1" data-inputhelp="Enter model or part number" data-inputhelpmobile="Model/Part #" name="searchModPar"><span class="large">in</span><div class="btn-group ">
				<a class="btn btn-type" href="#" data-toggle="dropdown"><span id="searchTypeLabel" data-texthelp="Select Type" data-texthelpmobile="Type">Select Type</span> <i class="icon-chevron-sign-down">&nbsp;</i></a>
				<ul class="dropdown-menu">
					<li>
						<a data-postpath="part-model" data-pathtaken="modelSearch" data-label="Model #">Model #</a>
					</li>
					<li>
						<a data-postpath="part-number" data-pathtaken="partSearch" data-label="Part #">Part #</a>
					</li>
				</ul>
			</div><button id="searchModelsParts" class="new-btn new-btn-search">Search</button>
			<p>Can't locate your model number? <a data-toggle="reveal" data-target="#findMyModel" id="newFinderModel">Use our finder <i class="icon-chevron-down">&nbsp;</i></a></p>
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
						<i class="icon-circle icon-stack-base"></i><%-- 
						--%><c:set var="panelIcon1"><cq:text property="panelIcon1" placeholder="icon-file-alt" /></c:set><%-- 
						--%><c:if test="${empty panelIcon1}"><c:set var="panelIcon1" value="icon-file-alt" /></c:if>
						<i class="${panelIcon1} icon-light"></i>
					</span><%-- 
					--%><c:set var="url1"><cq:text property="url1" placeholder="#" /></c:set><%-- 
					--%><c:if test="${empty url1}"><c:set var="url1" value="#" /></c:if><%-- 
					--%><c:set var="urlTarget1"><cq:text property="urlTarget1" placeholder="_blank" /></c:set><%-- 
					--%><c:if test="${empty urlTarget1}"><c:set var="urlTarget1" value="_blank" /></c:if><%-- 
					--%><c:set var="header1"><cq:text property="header1" placeholder="Get Manuals" /></c:set><%-- 
					--%><c:if test="${empty header1}"><c:set var="header1" value="Get Manuals" /></c:if><%-- 
					--%><c:set var="subheader1"><cq:text property="subheader1" placeholder="Find &amp; Download" /></c:set><%-- 
					--%><c:if test="${empty subheader1}"><c:set var="subheader1" value="Find &amp; Download" /></c:if>
					<h4><a href="${url1}" target="${urlTarget1}">${header1}<br /><span class="hidden-phone">${subheader1}</span></h4>
				</li>
				<li>
					<span class="icon-stack pull-left">
						<i class="icon-circle icon-stack-base"></i><%-- 
						--%><c:set var="panelIcon2"><cq:text property="panelIcon2" placeholder="icon-file-alt" /></c:set><%-- 
						--%><c:if test="${empty panelIcon2}"><c:set var="panelIcon2" value="icon-file-alt" /></c:if>
						<i class="${panelIcon2} icon-light"></i>
					</span><%-- 
					--%><c:set var="url2"><cq:text property="url2" placeholder="#" /></c:set><%-- 
					--%><c:if test="${empty url2}"><c:set var="url2" value="#" /></c:if><%-- 
					--%><c:set var="urlTarget2"><cq:text property="urlTarget2" placeholder="_blank" /></c:set><%-- 
					--%><c:if test="${empty urlTarget2}"><c:set var="urlTarget2" value="_blank" /></c:if><%-- 
					--%><c:set var="header2"><cq:text property="header2" placeholder="Lawn &amp; Garden" /></c:set><%-- 
					--%><c:if test="${empty header2}"><c:set var="header2" value="Lawn &amp; Garden" /></c:if><%-- 
					--%><c:set var="subheader2"><cq:text property="subheader2" placeholder="Repair &amp; Care Tips" /></c:set><%-- 
					--%><c:if test="${empty subheader2}"><c:set var="subheader2" value="Repair &amp; Care Tips" /></c:if>
					<h4><a href="${url2}" target="${urlTarget2}">${header2}<br /><span class="hidden-phone">${subheader2}</span></h4>
				</li>
				<li>
					<span class="icon-stack pull-left">
						<i class="icon-circle icon-stack-base"></i><%-- 
						--%><c:set var="panelIcon3"><cq:text property="panelIcon3" placeholder="icon-file-alt" /></c:set><%-- 
						--%><c:if test="${empty panelIcon3}"><c:set var="panelIcon3" value="icon-file-alt" /></c:if>
						<i class="${panelIcon3} icon-light"></i>
					</span><%-- 
					--%><c:set var="url3"><cq:text property="url3" placeholder="#" /></c:set><%-- 
					--%><c:if test="${empty url3}"><c:set var="url3" value="#" /></c:if><%-- 
					--%><c:set var="urlTarget3"><cq:text property="urlTarget3" placeholder="_blank" /></c:set><%-- 
					--%><c:if test="${empty urlTarget3}"><c:set var="urlTarget3" value="_blank" /></c:if><%-- 
					--%><c:set var="header3"><cq:text property="header3" placeholder="Ask An Expert" /></c:set><%-- 
					--%><c:if test="${empty header3}"><c:set var="header3" value="Ask An Expert" /></c:if><%-- 
					--%><c:set var="subheader3"><cq:text property="subheader3" placeholder="Have Questions?" /></c:set><%-- 
					--%><c:if test="${empty subheader3}"><c:set var="subheader3" value="Have Questions?" /></c:if>
					<h4><a href="${url3}" target="${urlTarget3}">${header3}<br /><span class="hidden-phone">${subheader3}</span></a></h4>
				</li>
			</ul>
		</div>
		<div id="searchRightShadow" class="search-shadow"></div>
	</div>
</form>