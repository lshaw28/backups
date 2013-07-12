<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getRelation single="true" assetType="productCategory" />
<spd:getGuideListing categoryPath="${productCategoryRelation.path}" />
<spd:tagsByPage tagType="subcategories"/>


<h2><cq:text property="title" placeholder="Headline" default="Headline" /></h2>

<%-- Most Popular Repair Guide View --%>
<div class="guideListing-popular-view">
	<c:forEach items="${guides}" var="entry" varStatus="mainStatus">
		<c:forEach items="${entry.value}" var="popularGuide" varStatus="status">
			<c:if test="${mainStatus.index == 0 && status.index == 0}">
				
				<div class="guideListing-popular-image">
					<div class="wrapper">
						<!--<spd:displayImage path="${popularGuide.imagePath}"/>-->
						<img src="/content/dam/Sears-J10567-SHS06-E%23CC001A.jpg" />
					</div>
				</div>
						
				<div class="wrench-symbol">
					<i class="icon-wrench"></i>
				</div>
				
				<div class="guideListing-popular-text">
					<div class="wrapper">
						<h3>${popularGuide.title}</h3>
						<p>
							Refrigerator help overview Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aliquam interdum pulvinar nibh. aecenas eget nunc in justo rhoncus.
						</p>
						<div class="container">
							<div class="pull-left repair-difficulty">
								<strong>Repair difficulty</strong>
								<div>
									<div class="difficulty-level-gauge">
										
									</div>
									<cq:text property="difficultyLevel" />
								</div>
							</div>
							<div class="pull-right time-required">
								<strong>Time required</strong>
								<div>
									<cq:text property="timeRequired" />
								</div>
								
							</div>
						</div>
						<a class="new-btn-large" href="${url}">
							<i class="icon-chevron-right"></i>
							<cq:text property="viewAllText" />
						</a>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</c:forEach>
</div>

<h4><cq:text property="allGuidestitle" /></h4>

<%-- @TODO Not in design... get requirements for visual
<h3><cq:text property="subTitle"/></h3>
--%>

<%-- Guide Listing View --%>
<div class="guideListing-listing">
	<c:forEach items="${guides}" var="row">
		<%-- Design does not show this 
		<spd:displayTagTitle tagId="${row.key}" />
		--%>
		
		<c:forEach var="guide" items="${row.value}">
			<spd:linkResolver value="${guide.url}" />
			
			<div class="item">
				<h4><a href="${url}">${guide.title}</a></h4>
				
				<div class="wrapper">
					<div class="image-wrapper">
						<div class="wrench-symbol">
							<i class="icon-wrench"></i>
						</div>
						<div class="image">
							<spd:displayImage path="${guide.imagePath}" />
						</div>
					</div>
						
					<p>
						Article description goes here. Aenean feugiat fringilla odio. Donec  lorem porttitor volutpat elit. Proin metus justo, accumsan vitae, egestas nec.
					</p>
				</div>
			</div>
			
		</c:forEach>
	</c:forEach>
</div>
