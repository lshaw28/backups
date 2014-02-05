<%@include file="/libs/foundation/global.jsp"%>

<cq:includeClientLib js="apps.searspartsdirect,apps.searspartsdirect.base" />

<div class="row-fluid lookUpBuckets">
	<div class="span6 lookUpBucket">
		<h2>Let's Make Your Search Easier</h2>
		<p>Find parts by category, product type and brand</p>
        <div class="lookUpSelect">
          	<select class="categoryType" id="categoryType">

			</select>
		</div>
		<div class="lookUpSelect">
			<select class="productType" id="productType">

			</select>
		</div>
		<div class="lookUpSelect">
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
    	fillAllCategory();
    });

     $("#categoryType").change(function () {
    	selectedValue = $("#categoryType option:selected").text();
         if(selectedValue=="Category"){hideAll();}
         else{
             $("#productType").show();
             $("#productType").empty();
             fillProductType(selectedValue);
         }
     });

     $("#productType").change(function () {
    	selectedValue = $("#productType option:selected").text();
        categoryTypeSelected = $("#categoryType option:selected").text();
         if(selectedValue=="Product Type"){
             $("#brand").hide();
             $("#findModels").hide();
         }
         else{
              $("#brand").show();
             $("#findModels").show();
             $("#brand").empty();
             fillBrandType(categoryTypeSelected, selectedValue);
         }
     });

    function navigateTo(){
        var productTypeSelected= $("#productType option:selected").val();
        var brandTypeSelected= $("#brand option:selected").val();
        var url="http://www.searspartsdirect.com/partsdirect/";

        if(brandTypeSelected!="Select"){
			url=url+"brands-products/"+brandTypeSelected+"/"+productTypeSelected;
            window.location.href=url;
        }
        else{
			url=url+"product-types/"+productTypeSelected;
             window.location.href=url;
        }
    }




</script>