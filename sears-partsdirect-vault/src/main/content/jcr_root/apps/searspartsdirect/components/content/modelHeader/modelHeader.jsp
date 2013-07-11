<%@ include file="/apps/searspartsdirect/global.jsp" %>
<spd:getUrlRelation />
<c:if test="${not empty brandRelation and not empty productCategoryRelation and not empty modelRelation}">
	<spd:getModelHeader brand="${brandRelation}" productCategory="${productCategoryRelation}" model="${modelRelation}" />
	<div class="row-fluid modelSection">
		<div class="span8">
			<h1>Model # ${modelRelation} ${brandRelation.title} ${productCategoryRelation.title}</h1>
		</div>
		<%-- 
		<div class="span2 offset2 modelInfo">Model Info <i class="icon-chevron-up"></i></div>
		<div class="span9 pull-left" id="modelSection">
			<div class="pull-left span3" > <a href="#."><img src="http://s7.sears.com/is/image/Sears/04678543000?hei=150&wid=150&op_sharpen=1&qlt=100" class="thumbnail" alt="refrigirator" title=""></a> </div>
			<div class="span3">
				<p><a href="#."><i class="icon-camera"></i> Upload a Photo</a></p>
				<p><a href="#."><i class="icon-zoom-in"></i> Enlarge Photo</a></p>
			</div>
			<div class="iownsection span5">
				<div class="span5">
					<button class="btn"><i class="icon-ok"></i> I own this</button>
				</div>
				<div class="span7">This model has been added to "My Models."</div>
			</div>
			<div class="span8">
				<h4>Lorem Ipsum Dolor</h4>
				<p>Lorem Ipsum Dolor Model Info lorem ipsum dolor wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit consequat. <a href="#.">Read more</a></p>
			</div>
		</div>
		--%>
		<c:if test="${not empty pseudoTabs}">
			<div class="span12">
				<ul class="nav nav-tabs">
					<c:forEach items="${pseudoTabs}" var="tab">
						<li<c:if test="${tab.linkText eq 'Repair Help'}"> class="active"</c:if>><a href="${tab.href}">${tab.linkText}</a>
					<%-- <li><a href="#tab1" >Shop Parts</a></li>
					<li><a href="#tab2" >Manuals(2)</a></li>
					<li class="active">Repair Help</li>
					<li><a href="#tab4" >Expert Q&A</a></li> --%>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
</c:if>
