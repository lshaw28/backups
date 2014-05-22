function disableFieldValue(field){
    
	var panel = field.findParentByType('panel');    
    var text = panel.find('itemId', 'text1')[0];
    var desc = panel.find('itemId', 'desc1')[0];    
    var flag = field.getValue();
    
    if(flag=="true"){
        text.disable();
        desc.disable();
    }
    else{
        text.enable();
        desc.enable();
    }
}