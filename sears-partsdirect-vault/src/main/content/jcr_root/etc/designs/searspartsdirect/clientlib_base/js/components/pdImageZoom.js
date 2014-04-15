$(function () {

    if ($(".pdImgContainer").length === 1){

	var image = $(".pdImgContainer img");
	var full = false;
	var userMsg = $(".zoomer");
	var zoomOverlay = $(".zoomMsg");
	var startWid = image.width();
	var startHei = image.height();
	
	setImage = function(){
	
		if (full){
		image.animate({width: startWid+"px",height: startHei+"px"},3000);
        zoomOverlay.fadeTo(1000,0);
		full = false;
		} else {
            image.animate({maxWidth: (startWid*3) + "px",width: (startWid*3)+"px",height: (startHei*3)+"px"},3000,function(){
                zoomOverlay.css({"left" : (startWid*3) - (zoomOverlay.width() + 20) + "px"});
			zoomOverlay.fadeTo(3000,1);
		
		});
		full = true;
		
		}	
	}; 
	
	userMsg.click(function(e) {
		
		e.preventDefault();
		setImage();
			
	});	

	image.click(function() {
		
		setImage();
			
	});

        zoomOverlay.click(function(){

			setImage();

        });

    }
});