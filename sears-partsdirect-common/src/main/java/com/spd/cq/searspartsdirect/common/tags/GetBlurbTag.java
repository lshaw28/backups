package com.spd.cq.searspartsdirect.common.tags;

import java.io.StringWriter;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Doctype;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.foundation.Image;

/**
 * Custom Tag to display an Image, markup included. Markup is based on the nature of the image, so must be included in Java
 * Path attribute directs the Image to a particular node in the content, otherwise defaults to currentNode
 * @author Joseph
 *
 */
@SuppressWarnings("serial")
public class GetBlurbTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory.getLogger(GetBlurbTag.class);

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			String domId = currentNode.getIdentifier();
			request.setAttribute("domId", domId);
			if(currentNode.hasProperty("domid")){domId = currentNode.getProperty("domid").getString();}
			request.setAttribute("layout", properties.get("layout", "default"));
			
			Image image = new Image(resource, "image");

		    if (image.hasContent() || WCMMode.fromRequest(request) == WCMMode.EDIT) {
		    	StringWriter imageWriter = new StringWriter();
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
		        		String csswidth = "";
		        String cssheight = "";
		        if(currentNode.hasNode("image")){
		        Node n = currentNode.getNode("image");
					if(n.hasProperty("width")){csswidth = "width: " + n.getProperty("width").getString() + "px;";}
		            if(n.hasProperty("height")){cssheight = "height: " + n.getProperty("height").getString() + "px;";}
		        }
		        image.draw(imageWriter);
		        request.setAttribute("imgsrc", image.getSrc());
		        request.setAttribute("img", imageWriter.toString());
		        request.setAttribute("csswidth", csswidth);
		        request.setAttribute("cssheight",cssheight);
		    }
		    request.setAttribute("title", properties.get("title",""));
		    request.setAttribute("subheader", properties.get("subhead",""));
		    request.setAttribute("subheader1", properties.get("subhead1",""));
		    request.setAttribute("linkto", properties.get("linkto","#"));
		}
		catch(Exception e) {
			log.error("Error during doStartTag() in GetBlurbTag.java: " + ExceptionUtils.getFullStackTrace(e));
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
