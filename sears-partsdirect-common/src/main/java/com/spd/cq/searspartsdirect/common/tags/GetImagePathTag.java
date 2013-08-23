package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
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
	public static final String SQUARE_IMAGE = Constants.ident("squareImage");

	private String resourcePath = null;

	@Override
	public int doStartTag() throws JspException {
		log.info("GetImagePathTag Start");
		String desktopImage = "";
		String tabletImage = "";
		String mobileImage = "";
		String squareImage = "";

		Resource resource = this.resource;
		if (resourcePath != null) {
			resource = resourceResolver.getResource(resourcePath+"/jcr:content");
			Node resourceNode = resource.adaptTo(Node.class);
			pageContext.setAttribute("displayWidth", propOrEmpty(resourceNode,"displayWidth"));
			pageContext.setAttribute("displayHeight",  propOrEmpty(resourceNode,"displayHeight"));
			pageContext.setAttribute("linkAlt",  propOrEmpty(resourceNode,"linkAlt"));
			pageContext.setAttribute("linkURL",  propOrEmpty(resourceNode,"linkURL"));
			pageContext.setAttribute("linkTarget",  propOrEmpty(resourceNode,"linkTarget"));
			pageContext.setAttribute("imageCaption",  propOrEmpty(resourceNode,"imageCaption"));
			pageContext.setAttribute("photoCredit",  propOrEmpty(resourceNode,"photoCredit"));
		}

		try {
			Image desktopImageObj = new Image(resource, DESKTOP_IMAGE);
			if (desktopImageObj != null && desktopImageObj.getHref() != null) {
				desktopImage = repairHref(desktopImageObj.getHref(), DESKTOP_IMAGE);
			}
		} catch (Exception desktopEx) {
			log.error("Desktop image error:", desktopEx);
		}
		try {
			Image tabletImageObj = new Image(resource, TABLET_IMAGE);
			if (tabletImageObj != null && tabletImageObj.getHref() != null) {
				tabletImage = repairHref(tabletImageObj.getHref(), TABLET_IMAGE);
			}
		} catch (Exception tabletEx) {
			log.error("Tablet image error:", tabletEx);
		}
		try {
			Image mobileImageObj = new Image(resource, MOBILE_IMAGE);
			if (mobileImageObj != null && mobileImageObj.getHref() != null) {
				mobileImage = repairHref(mobileImageObj.getHref(), MOBILE_IMAGE);
			}
		} catch (Exception mobileEx) {
			log.debug("Mobile image error:", mobileEx);
		}
		try {
			Image squareImageObj = new Image(resource, SQUARE_IMAGE);
			if (squareImageObj != null && squareImageObj.getHref() != null) {
				squareImage = repairHref(squareImageObj.getHref(), SQUARE_IMAGE);
			}
		} catch (Exception squareEx) {
			log.debug("Square image error:", squareEx);
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
		String newPath = originalPath;

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

	public void setResourcePath(String resourcePath) {
		this.resourcePath  = resourcePath;
	}

	private String propOrEmpty(Node node, String property) {
		String propValue = Constants.EMPTY;
		try {
			if (node.hasProperty(property)) {
				propValue = node.getProperty(property).getString();
				if (StringUtils.isBlank(propValue)) {
					propValue = Constants.EMPTY;
				}
			}
		} catch (Exception e) {
			log.warn("Retrieving "+property+" from "+node, e);
		}
		return propValue;
	}
}