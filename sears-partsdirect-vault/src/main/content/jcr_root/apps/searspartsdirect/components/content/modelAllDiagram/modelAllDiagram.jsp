<%@ include file="/apps/searspartsdirect/global.jsp"%>

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

                            </div>
						</div>
					</div>
				</article>				
  				
			
				<script type="text/javascript" src="js/clientlib_base-ck.js"></script>

				<script type="text/javascript" src="js/components/modelHeader.js"></script>

				<script>allModelDiagram('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>','<%=topSellingImagePath%>');</script>
				
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