package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.foundation.Image;

/**
 * Custom Tag to display an Image, markup included. Markup is based on the nature of the image, so must be included in Java
 * Path attribute directs the Image to a particular node in the content, otherwise defaults to currentNode
 * @author Joseph
 *
 */
@SuppressWarnings("serial")
public class DisplayImageTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory.getLogger(DisplayImageTag.class);

	protected String path;
	protected boolean decorated = true;
	protected String altText;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		Resource imgResource = resource;
		if (path != null && (path.length() > 0 && path.charAt(0) == '/')) {
			imgResource = resourceResolver.resolve(path);
			path = null;
		}
		Image image = path == null ? new Image(imgResource) : new Image(imgResource,path);

		//drop target css class = dd prefix + name of the drop target in the edit config
		image.addCssClass(DropTarget.CSS_CLASS_PREFIX + "image");
		image.loadStyleData(currentStyle);
		image.setSelector(".img"); // use image script
		//image.setDoctype(Doctype.fromRequest(request));
		// add design information if not default (i.e. for reference paras)
		if (!currentDesign.equals(resourceDesign)) {
			image.setSuffix(currentDesign.getId());
		}
		if (altText != null) {
			image.setAlt(altText);
		}
		String divId = "cq-image-jsp-" + imgResource.getPath();
		try {
			if (decorated) out.write("<div id=\"" + divId + "\">");
			image.draw(out);
			if (decorated) out.write("</div>");
		}
		catch (Exception e) {
			log.error("drawing image, ",e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setDecorated(boolean decorated) {
		this.decorated = decorated;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}
}
