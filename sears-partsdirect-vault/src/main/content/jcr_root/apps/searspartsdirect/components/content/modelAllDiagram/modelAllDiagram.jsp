<%@ include file="/apps/searspartsdirect/global.jsp"%>
<script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/modelSearchResults.js" ></script>

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String brandName = (request.getParameter("brandName") != null) ? request.getParameter("brandName") : "";
String modelDescription = (request.getParameter("modelDescription") != null) ? request.getParameter("modelDescription") : "";
%>

						<div id="searchRightShadow" class="search-shadow"></div>
						<a name="findMyModel"></a>
						<div class="modelFinder">
							<div class="modelFinderWrapper" style="height: 0px;">
								<div class="modelFinderCriteria">
									<h3>Choose your product type</h3>
									<div class="modelFinderProductSelect dropdown-reskin">
										<i class="icon-angle-down"></i>
										<select><option value="0">Select</option><option value="cooktop">Cooktops</option><option value="dishwasher">Dishwashers</option><option value="dryer">Dryers</option><option value="freezer">Freezers</option><option value="microwave">Microwaves</option><option value="wall oven">Ovens</option><option value="range">Ranges</option><option value="refrigerator">Refrigerators</option><option value="washer">Washers</option></select>
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
										<img class="modelFinderPlatesImage1" src="img/search-model-help1.png">
										<img class="modelFinderPlatesImage2" src="img/search-model-help2.png">
										<img class="modelFinderPlatesImage3" src="img/search-model-help3.png">
									</div>

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
				</div>

				<div class="headerPromo"><p class="visible-phone visible-tablet"><span class="icon-stack"><i class="icon-circle icon-stack-base"></i><i class="icon-tint icon-light"></i></span> Get <span class="alert-ship">FREE SHIPPING</span> on water filters when you sign up for automatic reorder. <a href="http://www.searspartsdirect.com/partsdirect/merchandiser/show.pd?description=Water%20Filters">Shop Water Filters</a></p>
					<p class="visible-desktop"><span class="icon-stack"><i class="icon-circle icon-stack-base"></i><i class="icon-tint icon-light"></i></span> Change your water filter every 3-6 months. Get <span class="alert-ship">FREE SHIPPING</span> with reorder. <a href="http://www.searspartsdirect.com/partsdirect/merchandiser/show.pd?description=Water%20Filters">Shop Water Filters</a></p></div>

				</div></div>

				<a name="backToTop"></a>

				<article id="content" data-templatename="RepairHelpHomepage"><div class="articleFix">
					<div class="row-fluid modelPageAllDiagram">
						<div class="span10 desktop-offset1">
							
							<div class="row-fluid">
								<div class="repairHelpHomeTitle">
									<div class="pageTitleHeader">
										<h1>Model # <%=modelNumber %> <%=brandName %> <%=modelDescription %><p class="pull-right">Model Info <i class="icon-chevron-down"></i></p></h1>
									</div>
								</div>
							</div>

							<div class="row-fluid">
								<div class="modelHeader">
									<ul>
										<li class="active">Shop Parts</li>
										<li>Manuals</li>
										<li>Repair Help</li>
										<li>Expert Q&#38;A</li>
									</ul>
								</div>
							</div>	

					<!-- ************* Model Page (All Diagram) ************* -->
							<div class="row-fluid">
								<div class="searchContainer span12">
									
									<div class="span6">
										<div class="modelNumberSearch">
											<div class="searchHeading">
												<p class="searchHeading">Search by part name</p>
											</div>
											<div class="modelNumberSearchInputs">
												<div class="form-inline">
													<div class="input-append">
													<input type="text" id="" name="" maxlength="42" data-inputhelp="ex. screw, gate, switch" data-inputhelpmobile="ex. screw, gate, switch" value="ex. screw, gate, switch">
													<span class="add-on"><button><i class="icon-search">&nbsp;</i></button></span>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="span6">
											<div class="modelNumberSearch">
											<p class="searchHeading">Popular searches:</p>
											<ul class="columns">
												<li><a href="#">Screw</a></li>
												<li><a href="#">Switch</a></li>
												<li><a href="#">Control panel</a></li>
												<li><a href="#">Turn key</a></li>
												<li><a href="#">Hob Nob</a></li>
												<li><a href="#">Broomstick</a></li>
											</ul>
										</div>
									</div>

								</div>
							</div>

							<div class="row-fluid">
								<p class="selectDiagram">Select a diagram to see associated parts</p>
							</div>

							
							
								<div class="row-fluid">
									<div class="span12">
										<section class=" pattern">
											<div class = "allDiagramContainer">
												<ul class = "grid" id="allDiagramContainer">

													
												</ul>
											</div>
										</section>
									</div>
								</div>
		
						<!-- ************* / Model Page (All Diagram) ************* -->

							<div class="boxed-callout">
								<h4>Top Accessories</h4>
								<div class="boxed-container span10">
									<div class="topAccessories-item first span2">
										<img src="img/coil_lint_brush.jpg" />
										<a href="#">Stainless Steel Magic</a>
									</div>
									<div class="topAccessories-item span2">
										<img src="img/coil_lint_brush.jpg" />
										<a href="#">Coil &#38; Lint Brush Combo</a>
									</div>
									<div class="topAccessories-item span2">
										<img src="img/coil_lint_brush.jpg" />
										<a href="#">Coil &#38; Lint Brush Combo</a>
									</div>
									<div class="topAccessories-item span2">
										<img src="img/coil_lint_brush.jpg" />
										<a href="#">Coil &#38; Lint Brush Combo</a>
									</div>
									<div class="topAccessories-item span2">
										<img src="img/coil_lint_brush.jpg" />
										<a href="#">Coil &#38; Lint Brush Combo</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</article>				
  				
			
				<script type="text/javascript" src="js/clientlib_base-ck.js"></script>

				<script type="text/javascript" src="js/components/modelHeader.js"></script>

				<script>allModelDiagram('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>');</script>