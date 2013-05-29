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
		Image desktopImageObj = new Image(resource, "desktopImage");
		String desktopImage = desktopImageObj.getFileReference();

		Image tabletImageObj = new Image(resource, "tabletImage");
		String tabletImage = tabletImageObj.getFileReference();

		Image mobileImageObj = new Image(resource, "mobileImage");
		String mobileImage = mobileImageObj.getFileReference();

		pageContext.setAttribute("desktopImage", desktopImage);
		pageContext.setAttribute("tabletImage", tabletImage);
		pageContext.setAttribute("mobileImage", mobileImage);
		log.info("GetImagePathTag End");
		return SKIP_BODY;
	}

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}