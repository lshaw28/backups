<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%	// @TODO: data attributes need to be populate with the path/URL to the image for each size
	// These images are either user-uploaded images or resized by Scene7 %>
	
	Responsive Image
<spd:getImagePathTag/>
Image1 Path  : ${desktopImagePath}
Image2 Path  : ${tabletImagePath}
Image3 Path  : ${mobileImagePath}

<image path="image1"/>
<div class="responsiveImage" data-desktopimage="${desktopImagePath}" data-tabletimage="${tabletImagePath}" data-mobileimage="${mobileImagePath}">&nbsp;</div>