check = function(){
    return {
    	titleField: function(dialog){
        var componentTitle = dialog.find("itemId", "componentTitle")[0].getValue();
        var brand = dialog.find("itemId", "brand")[0].getValue();
        var productType = dialog.find("itemId", "productType")[0].getValue();
        if(componentTitle.length == 0 && brand.length == 0 && productType.length == 0){
        	alert("Either specify component title or brand/product type");
            return false;
        }
        return true;
    }
};
}();