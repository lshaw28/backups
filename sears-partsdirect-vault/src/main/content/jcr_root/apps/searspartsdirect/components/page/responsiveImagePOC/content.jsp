<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="CommonPartspage"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			
			<spd:respImagePoc />
			<c:forEach var="respImage" items="${respImages}">
				<img src="${respImage}" />
			</c:forEach>
			
			<div class="row-fluid">
				<div class="span8">
					<cq:include path="pageTitleHeader" resourceType="searspartsdirect/components/content/pageTitleHeader" />
					<div class="new-span-quarter">
						<spd:displayImage path="${currentPage.path}${Constants.ASSETS_IMAGE_PATH}" decorated="false" />
					</div>
				</div>
				<div class="span3 offset1 pull-right">
					<div class="row-fluid">
						<div class="span12">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div></article>