<%@include file="/libs/foundation/global.jsp"%>
<head>
<link rel="stylesheet" type="text/css" href="/etc/designs/searspartsdirect/clientlib_base/css/noModelsFound.css"/>
</head>
<script type="text/javascript" src="/etc/designs/searspartsdirect/clientlib_base/js/browseModels.js" ></script>


<div class="row-fluid lookUpBuckets">
	<div class="span6 lookUpBucket">
		<h2>Let's Make Your Search Easier</h2>
		<p>Find parts by category, product type and brand</p>
        <div class="lookUpSelect">
            <p class="categoryType"><b>Category</b></p>
			<select class="categoryType" id="categoryType">
                <option value="select">Select</option>
				<option value="c1">1234</option>
                <option value="c2">123</option>
                <option value="c3">222</option>
			</select>
		</div>
		<div class="lookUpSelect">
            <p class="productType"><b>Product type</b></p>
			<select class="productType" id="productType">

			</select>
		</div>
		<div class="lookUpSelect">
            <p class="brand"><b>Brand</b></p>
			<select class="brand" id="brand">

			</select>
		</div>
		<a id="findModels" class="new-btn new-btn-square" href="#" onclick='navigateTo()'>Find Models</a>
	</div>
</div>
<script>

    var selectedValue=0;
    var categoryTypeSelected=0;

    $(document).ready(function(){
    	hideAll();
    });

     $("#categoryType").change(function () {
    	selectedValue = $("#categoryType option:selected").text();
         if(selectedValue=="Select"){hideAll();}
         else{
             $(".productType").show();
             $("#productType").empty();
             fillProductType(selectedValue);
         }
     });

     $("#productType").change(function () {
    	selectedValue = $("#productType option:selected").text();
        categoryTypeSelected = $("#categoryType option:selected").text();
         if(selectedValue=="Select"){
             $(".brand").hide();
             $("#findModels").hide();
         }
         else{
              $(".brand").show();
             $("#findModels").show();
             $("#brand").empty();
             fillBrandType(categoryTypeSelected, selectedValue, 'productType', 'brand');
         }
     });

    function navigateTo(){
        var brandTypeSelected= $("#brand option:selected").text();
        if(brandTypeSelected!="Select"){
            alert("navigate to Category+Product+Brand Type landing page");
        }
        else{
            alert("navigate to Category+Product Type landing page");
        }
    }




</script>