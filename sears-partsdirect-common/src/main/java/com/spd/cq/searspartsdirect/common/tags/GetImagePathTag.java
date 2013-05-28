package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.foundation.Image;

public class GetImagePathTag extends CQBaseTag {
	
	protected static Logger log = LoggerFactory.getLogger(GetImagePathTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		log.info("GetImagePathTag Start");
		Image image1 = new Image(resource, "image1");
		String desktopImage = image1.getFileReference();
		
		Image image2 = new Image(resource, "image2");
		String tabletImage = image2.getFileReference();
		
		Image image3 = new Image(resource, "image3");
		String mobileImage = image3.getFileReference();
		
		pageContext.setAttribute("desktopImagePath", desktopImage);
		pageContext.setAttribute("tabletImagePath", tabletImage);
		pageContext.setAttribute("mobileImagePath", mobileImage);
		log.info("GetImagePathTag End");
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
