<%@ page import="com.day.cq.commons.Doctype,com.day.cq.wcm.api.WCMMode,com.day.cq.wcm.api.components.DropTarget,com.day.cq.wcm.foundation.Image" %>
<%@include file="/libs/foundation/global.jsp"%>

<div class="row-fluid doubleCheckModelNumber">
<%
    Image image = new Image(resource, "image");

    if (image.hasContent() || WCMMode.fromRequest(request) == WCMMode.EDIT) {
        image.loadStyleData(currentStyle);
        // add design information if not default (i.e. for reference paras)
        if (!currentDesign.equals(resourceDesign)) {
            image.setSuffix(currentDesign.getId());
        }
        //drop target css class = dd prefix + name of the drop target in the edit config
        image.addCssClass(DropTarget.CSS_CLASS_PREFIX + "image");
        image.setSelector(".img");
        image.setDoctype(Doctype.fromRequest(request));

        String divId = "cq-textimage-jsp-" + resource.getPath();
        String imageHeight = image.get(image.getItemName(Image.PN_HEIGHT));
        // div around image for additional formatting
        %>

    <div class="span2" style="margin-top:10px;">
	<% image.draw(out); %>
    </div>

    <%@include file="/libs/foundation/components/image/tracking-js.jsp"%>
<%}%>


    <div class="span10">
		<cq:text property="text"/>
   </div>
</div>










