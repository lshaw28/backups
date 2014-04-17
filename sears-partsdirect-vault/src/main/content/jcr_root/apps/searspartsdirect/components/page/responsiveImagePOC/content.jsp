<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content" data-templatename="CommonPartspage"><div class="articleFix">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			
			<spd:respImagePoc />
            <div class="responsivePicture">
                <span data-min="0" data-max="360" data-src="${respImage}?wid=200"></span>
                <span data-min="361" data-max="720" data-src="${respImage}?wid=500" data-default="true"></span>
                <span data-min="721" data-max="1360" data-src="${respImage}?wid=700"></span>
            </div>

            <div class="responsivePicture">
                <span data-min="0" data-max="300" data-src="${respImage}?wid=100"></span>
                <span data-min="301" data-max="500" data-src="${respImage}?wid=300" data-default="true"></span>
                <span data-min="501" data-max="1360" data-src="${respImage}?wid=600"></span>
            </div>

            <div class="responsivePicture">
                <span data-min="0" data-max="400" data-src="${respImage}?wid=350"></span>
                <span data-min="401" data-max="820" data-src="${respImage}?wid=550" data-default="true"></span>
                <span data-min="821" data-max="1360" data-src="${respImage}?wid=800"></span>
            </div>
			
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