package com.spd.cq.searspartsdirect.common.tags;

import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.wcm.foundation.Image;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

@SuppressWarnings("serial")
public class GetImagePathTag extends CQBaseTag {
	protected static Logger log = LoggerFactory.getLogger(GetImagePathTag.class);

	public static final String DESKTOP_IMAGE = Constants.ident("desktopImage");
	public static final String TABLET_IMAGE = Constants.ident("tabletImage");
	public static final String MOBILE_IMAGE = Constants.ident("mobileImage");
	
	@Override
	public int doStartTag() throws JspException {
		log.info("GetImagePathTag Start");
		String desktopImage = "";
		String tabletImage = "";
		String mobileImage = "";

		try {
			Image desktopImageObj = new Image(resource, DESKTOP_IMAGE);
			desktopImage = repairHref(desktopImageObj.getHref(), DESKTOP_IMAGE);
		} catch (Exception desktopEx) {
			log.debug("Desktop image error:", desktopEx.getMessage());
		}
		try {
			Image tabletImageObj = new Image(resource, TABLET_IMAGE);
			tabletImage = repairHref(tabletImageObj.getHref(), TABLET_IMAGE);
		} catch (Exception tabletEx) {
			log.debug("Tablet image error:", tabletEx.getMessage());
		}
		try {
			Image mobileImageObj = new Image(resource, MOBILE_IMAGE);
			mobileImage = repairHref(mobileImageObj.getHref(), MOBILE_IMAGE);
		} catch (Exception mobileEx) {
			log.debug("Mobile image error:", mobileEx.getMessage());
		}

		pageContext.setAttribute(DESKTOP_IMAGE, desktopImage);
		pageContext.setAttribute(TABLET_IMAGE, tabletImage);
		pageContext.setAttribute(MOBILE_IMAGE, mobileImage);
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
			newPath = originalPath.replace(targetName + "/file.gif", targetName + ".img.gif");
		}
		if (originalPath.indexOf(targetName + "/file.jpg")!= -1) {
			newPath = originalPath.replace(targetName + "/file.jpg", targetName + ".img.jpg");
		}

		return newPath;
	}
}