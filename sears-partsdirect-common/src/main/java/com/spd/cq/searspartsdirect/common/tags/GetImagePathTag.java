package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.foundation.Image;

public class GetImagePathTag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(GetImagePathTag.class);


	@Override
	public int doStartTag() throws JspException {
		Image image1 = new Image(resource, "image1");
		String image1Src = image1.getFileReference();
		
		Image image2 = new Image(resource, "image2");
		String image2Src = image2.getFileReference();
		
		Image image3 = new Image(resource, "image3");
		String image3Src = image3.getFileReference();
		
		pageContext.setAttribute("image1Path", image1Src);
		pageContext.setAttribute("image2Path", image2Src);
		pageContext.setAttribute("image3Path", image3Src);
		
		return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
