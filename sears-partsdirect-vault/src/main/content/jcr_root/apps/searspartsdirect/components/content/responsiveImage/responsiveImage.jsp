<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%	// @TODO: data attributes need to be populate with the path/URL to the image for each size
	// These images are either user-uploaded images or resized by Scene7 %>
	
	Responsive Image
<spd:getImagePathTag/>
Image1 Path  : ${image1Path}
Image2 Path  : ${image2Path}
Image3 Path  : ${image3Path}

<image path="image1"/>
<div class="responsiveImage" data-desktopimage="${image1Path}" data-tabletimage="${image2Path}" data-mobileimage="${image3Path}">&nbsp;</div>