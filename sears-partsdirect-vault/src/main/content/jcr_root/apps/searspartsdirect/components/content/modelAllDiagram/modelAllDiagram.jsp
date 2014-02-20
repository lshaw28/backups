<%@ include file="/apps/searspartsdirect/global.jsp"%>
<cq:includeClientLib categories="apps.searspartsdirect,apps.searspartsdirect.base" />

<%
String modelNumber = (request.getParameter("modelNumber") != null) ? request.getParameter("modelNumber") : "";
String brandId = (request.getParameter("brandId") != null) ? request.getParameter("brandId") : "";
String categoryId = (request.getParameter("categoryId") != null) ? request.getParameter("categoryId") : "";
String brandName = (request.getParameter("brandName") != null) ? request.getParameter("brandName") : "";
String modelDescription = (request.getParameter("modelDescription") != null) ? request.getParameter("modelDescription") : "";

String topSellingImagePath="/etc/designs/searspartsdirect/clientlib_base/img/topParts.png";
if(currentNode.hasNode("image/file/jcr:content")){
	topSellingImagePath=currentNode.getNode("image/file/jcr:content").getProperty("jcr:data").getPath();
}
%>



				<article id="content" data-templatename="RepairHelpHomepage">
	<div class="articleFix">
		<div class="row-fluid modelPageAllDiagram">
			<div class="span10 desktop-offset1">

			<%@include file="/apps/searspartsdirect/components/content/headerPD/headerPD.jsp"%>

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
											<input type="text" id="searchPart" name="" maxlength="42"
												data-inputhelp="ex. screw, gate, switch"
												data-inputhelpmobile="ex. screw, gate, switch"
												value="ex. screw, gate, switch"/> <span
												class="add-on"><button id="searchByPartName" 
												onclick="showSearchByPartName('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>', '<%=brandName %>', '<%=modelDescription %>', $('#searchPart').val())">
													<i class="icon-search">&nbsp;</i>
												</button></span>
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
					<p class="selectDiagram">Select a diagram to see associated
						parts</p>
				</div>

				<div class="row-fluid">
					<div class="span12">
						<section class=" pattern">
							<div class="allDiagramContainer">
								<ul class="grid" id="allDiagramContainer">
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
							<img src="img/coil_lint_brush.jpg" /> <a href="#">Stainless
								Steel Magic</a>
						</div>
						<div class="topAccessories-item span2">
							<img src="img/coil_lint_brush.jpg" /> <a href="#">Coil &#38;
								Lint Brush Combo</a>
						</div>
						<div class="topAccessories-item span2">
							<img src="img/coil_lint_brush.jpg" /> <a href="#">Coil &#38;
								Lint Brush Combo</a>
						</div>
						<div class="topAccessories-item span2">
							<img src="img/coil_lint_brush.jpg" /> <a href="#">Coil &#38;
								Lint Brush Combo</a>
						</div>
						<div class="topAccessories-item span2">
							<img src="img/coil_lint_brush.jpg" /> <a href="#">Coil &#38;
								Lint Brush Combo</a>
						</div>
					</div>
				</div>
			</div>
		</div>
</article>				
  				
			
				<script type="text/javascript" src="js/clientlib_base-ck.js"></script>

				<script type="text/javascript" src="js/components/modelHeader.js"></script>

				<script>allModelDiagram('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>','<%=topSellingImagePath%>','<%=brandName%>','<%=modelDescription%>');</script>
				
				<script>
					$('#searchByPartName').bind('click', function (e) {
						e.preventDefault();
						if ($("#searchPart").length > 0) {
							var modelNumber = "<%=modelNumber%>";
							var brandId = "<%=brandId%>";
							var categoryId = "<%=categoryId%>";
							var brandName = "<%=brandName %>"; 
							var modelDescription = "<%=modelDescription %>";
														
							var description = $("#searchPart").val();
							
							window.location.href="/content/searspartsdirect/en/searchbypartname.html"
                			+ "?modelNumber="+modelNumber+"&brandId="+brandId+"&categoryId="+categoryId+"&description="+description+"&brandName="+brandName+"&modelDescription="+modelDescription;
						}
					});
				</script>