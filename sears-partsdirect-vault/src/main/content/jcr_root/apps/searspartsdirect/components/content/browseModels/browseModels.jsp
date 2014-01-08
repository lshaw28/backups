<%@include file="/libs/foundation/global.jsp"%>
<head>
<link rel="stylesheet" type="text/css" href="/etc/designs/searspartsdirect/clientlib_base/css/noModelsFound.css"/>
</head>
<script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/browseModels.js" ></script>


<div class="row-fluid lookUpBuckets">
	<div class="span6 lookUpBucket">
		<h2>Browse Models</h2>
		<p>Find parts by product type and brand</p>
		<div class="lookUpSelect">
			<select class="productType" id="productType">

			</select>
		</div>
		<div class="lookUpSelect">
			<select class="brand" id="brand">

			</select>
		</div>
		<a class="new-btn new-btn-square" href="#">Find Models</a>
	</div>
</div>

<script>
	 $(document).ready(function(){
    	fillDropdowns();
    });

</script>