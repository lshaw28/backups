<!-- 
All pages including this files should describe below parameters
modelNumber, brandId, categoryId, brandName,  modelDescription.
 -->
<div id="headerPD">
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1>
				Model #
				<%=modelNumber %>
				<%=brandName %>
				<%=modelDescription %>
				</h1>
		</div>
	</div>
</div>
<div><a onClick="checkCookie('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>')">I own This</a></div>
<div class="row-fluid">
	<div class="modelHeader">
		<ul id="dynamicTabs">
		</ul>
	</div>
</div>
</div>
<script>getDynamicTabs('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>');</script>