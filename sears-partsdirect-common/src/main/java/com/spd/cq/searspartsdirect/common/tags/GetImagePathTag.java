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
		String desktopImage = "";
		String tabletImage = "";
		String mobileImage = "";

		try {
			Image desktopImageObj = new Image(resource, "desktopImage");
			desktopImage = repairHref(desktopImageObj.getHref(), "desktopImage");
		} catch (Exception desktopEx) {
			log.debug("Desktop image error:", desktopEx.getMessage());
		}
		try {
			Image tabletImageObj = new Image(resource, "tabletImage");
			tabletImage = repairHref(tabletImageObj.getHref(), "tabletImage");
		} catch (Exception tabletEx) {
			log.debug("Tablet image error:", tabletEx.getMessage());
		}
		try {
			Image mobileImageObj = new Image(resource, "mobileImage");
			mobileImage = repairHref(mobileImageObj.getHref(), "mobileImage");
		} catch (Exception mobileEx) {
			log.debug("Mobile image error:", mobileEx.getMessage());
		}

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

	public String repairHref(String originalPath, String targetName) {
		String newPath = "";

		if (originalPath.indexOf(targetName + "/file.png")!= -1) {
			newPath = originalPath.replace(targetName + "/file.png", targetName + ".img.png");
		}
		if (originalPath.indexOf(targetName + "/file.gif")!= -1) {
			newPath = originalPath.replace(targetName + "/file.gif", targetName + ".img.png");
		}
		if (originalPath.indexOf(targetName + "/file.jpg")!= -1) {
			newPath = originalPath.replace(targetName + "/file.jpg", targetName + ".img.jpg");
		}

		return newPath;
	}
}