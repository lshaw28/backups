<%@ include file="/apps/searspartsdirect/global.jsp"%>
<article id="content">
	<div class="row-fluid">
		<div class="span10 desktop-offset1">
			<%-- <cq:include path="/content/searspartsdirect/en/jcr:content/breadcrumbNavigation" resourceType="/apps/searspartsdirect/components/base/breadcrumbNavigation" /> --%>
			<cq:include path="/content/searspartsdirect/en/jcr:content/modelHeader" resourceType="/apps/searspartsdirect/components/content/modelHeader" />
			<div class="row-fluid">
				<div class="span12">
					<h1>
						<c:if test="${not empty productCategoryRelation}">
						<c:out value="${productCategoryRelation.title}" />
						&nbsp;Error Codes
						</c:if>
					</h1>
					<div class="errorCodeChecker white">
						<p>
							<span class="primary-content">
								<span class="icon-container">
									<i class="svg-icon-er"></i>
									<span class="divider">&nbsp;</span>
								</span>
								<cq:include path="errorCodesDesc" resourceType="/apps/searspartsdirect/components/content/plainText" />
							</span>
						</p>
					</div>
					<cq:include path="errorCodesList" resourceType="searspartsdirect/components/content/errorCodesList" />
				</div>
			</div>
		</div>
	</div>
</article>